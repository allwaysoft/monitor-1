package com.dennis.api.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.dennis.api.redis.CodeRedisDao;
import com.dennis.api.redis.TokenRedisDao;
import com.dennis.api.service.UserService;
import com.dennis.common.constants.ApiConstant;
import com.dennis.common.constants.UserConstant;
import com.dennis.common.enums.ResultEnum;
import com.dennis.common.result.Result;
import com.dennis.common.result.ResultUtil;
import com.dennis.common.tools.*;
import com.dennis.dao.entity.User;
import com.dennis.dao.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Dennis on 2019/3/30.
 */


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private CodeRedisDao codeRedisDao;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenRedisDao tokenRedisDao;


    @Override
    public Result sendCode(String account, Integer type) throws ClientException {

        // type ==》  1：注册 2：登录 3：忘记密码
        String code = SMSCodeGenerator.generate();

        if (type == 1) {
            // 注册
            User user = userMapper.selectByAccount(account);
            if (user != null)
                return ResultUtil.error(ResultEnum.ACCOUNT_EXIST.getMsg());

            // 发送验证码
            SMSUtils.sendSMSCode(account, code, type);

        } else if (type == 3) {
            // 登录
            User user = userMapper.selectByAccount(account);
            if (user == null)
                return ResultUtil.error(ResultEnum.USER_NOT_FOUND.getMsg());
            if (user.getIsEnable() == 1)
                return ResultUtil.error(ResultEnum.USER_BAN.getMsg());

            // 发送验证码
            SMSUtils.sendSMSCode(account, code, type);
        } else if (type == 3) {
            // 忘记密码
            User user = userMapper.selectByAccount(account);
            if (user == null)
                return ResultUtil.error(ResultEnum.USER_NOT_FOUND.getMsg());

            // 发送验证码
            SMSUtils.sendSMSCode(account, code, type);
        } else {
            return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
        }

        // 持久化 Redis
        codeRedisDao.saveUserCode(type, account, code);

        return ResultUtil.success("发送成功", null);
    }

    @Override
    @Transactional
    public Result register(Map params) {

        String account = MapUtil.getString(params, "account");

        // 验证码 校验
        String code = codeRedisDao.getUserCode(1, account);
        if (code == null || !code.equals(MapUtil.getString(params, "code")))
            return ResultUtil.error(ResultEnum.VERIFICATION_CODE_ERROR.getMsg());

        // 账号校验
        User checkUser = userMapper.selectByAccount(account);
        if (checkUser != null)
            return ResultUtil.error(ResultEnum.ACCOUNT_EXIST.getMsg());

        User user = new User();
        user.setAccount(account);
        user.setNickname("新用户_" + UUID.randomUUID().toString().substring(0, 6));
        user.setAvatar(UserConstant.DEFAULT_AVATAR);
        user.setIsDelete(0);
        user.setIsEnable(0);
        user.setCreateTime(DateUtil.getCurrentDate());
        user.setUpdateTime(DateUtil.getCurrentDate());

        // 密码加密
        String salt = EncryptUtils.createSalt();
        String password = MapUtil.getString(params, "password");
        String encryptPass = EncryptUtils.encrypt(password, salt);
        user.setSalt(salt);
        user.setPassword(encryptPass);

        userMapper.insertSelective(user);

        return ResultUtil.success(ResultEnum.REGISTER_SUCCESS.getMsg());
    }

    @Override
    public Result login(String account, String password) {

        User checkUser = userMapper.selectByAccount(account);
        if (checkUser == null)
            return ResultUtil.error(ResultEnum.USER_NOT_FOUND.getMsg());

        if (!checkUser.getPassword().equals(EncryptUtils.encrypt(password, checkUser.getSalt())))
            return ResultUtil.error(ResultEnum.USER_PASSWORD_NOT_MATCH.getMsg());

        if (checkUser.getIsEnable() == 1)
            return ResultUtil.error(ResultEnum.USER_BAN.getMsg());

        Map info = persistentRedis(checkUser);

        return ResultUtil.success(ResultEnum.LOGIN_SUCCESS.getMsg(), info);
    }

    @Override
    public Result loginForCode(String account, String code) {

        // 获取 Redis 验证码
        String checkCode = codeRedisDao.getUserCode(2, account);

        // 账户校验
        User checkUser = userMapper.selectByAccount(account);
        if (checkUser == null)
            return ResultUtil.error(ResultEnum.USER_NOT_FOUND.getMsg());

        // 验证码校验
        if (!checkCode.equals(code))
            return ResultUtil.error(ResultEnum.VERIFICATION_CODE_ERROR.getMsg());

        // 状态校验
        if (checkUser.getIsEnable() == 1)
            return ResultUtil.error(ResultEnum.USER_BAN.getMsg());

        // 生成 token、持久化 Redis
        Map map = persistentRedis(checkUser);

        return ResultUtil.success("登录成功", map);

    }

    @Override
    @Transactional
    public Result updatePassword(String account, String code, String password) {

        // 账户校验
        User check = userMapper.selectByAccount(account);
        if (check == null)
            return ResultUtil.error(ResultEnum.USER_NOT_FOUND.getMsg());

        // 验证码校验
        String checkCode = codeRedisDao.getUserCode(3, account);
        if (!checkCode.equals(code))
            return ResultUtil.error(ResultEnum.VERIFICATION_CODE_ERROR.getMsg());

        // 修改密码
        String pass = EncryptUtils.encrypt(password, check.getSalt());
        check.setPassword(pass);
        check.setUpdateTime(DateUtil.getCurrentDate());

        userMapper.updateByPrimaryKeySelective(check);

        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS.getMsg());
    }

    @Override
    @Transactional
    public Result updateAvatar(String avatar) {

        User user = userMapper.selectByPrimaryKey(RequestUtils.getPkId());
        if (user == null)
            return ResultUtil.error(ResultEnum.USER_NOT_FOUND.getMsg());

        user.setAvatar(avatar);
        user.setUpdateTime(DateUtil.getCurrentDate());
        userMapper.updateByPrimaryKeySelective(user);

        // 更新客户端用户信息
        Map info = userMapper.selectByLoginInfo(user.getAccount());

        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS.getMsg(), info);
    }


    /**
     * 生成Token、持久化Redis
     * @param checkUser
     * @return
     */
    public Map persistentRedis(User checkUser) {

        // Token
        Map domain = new HashMap();
        domain.put("account", checkUser.getAccount());
        domain.put(UserConstant.PKID, checkUser.getPkId());
        domain.put("time", System.currentTimeMillis());
        String token = JWTUtils.generateToken(ApiConstant.USER_JWT_PREFIX, domain);

        // 持久化 Redis
        tokenRedisDao.saveToken(checkUser.getPkId().toString(), token);

        // 返回数据
        Map info = userMapper.selectByLoginInfo(checkUser.getAccount());
        Map map = new HashMap();
        map.put("user", info);
        map.put("token", token);

        return map;
    }
}
