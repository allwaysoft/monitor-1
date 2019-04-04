package com.dennis.api.controller;

import com.aliyuncs.exceptions.ClientException;
import com.dennis.api.service.UserService;
import com.dennis.common.annotation.Authorization;
import com.dennis.common.constants.UserConstant;
import com.dennis.common.enums.ResultEnum;
import com.dennis.common.result.Result;
import com.dennis.common.result.ResultUtil;
import com.dennis.common.tools.MapUtil;
import com.dennis.common.tools.RequestUtils;
import com.dennis.common.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Dennis on 2019/3/30.
 */

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    /**
     * 发送验证码
     * type    1: 注册 2：忘记密码
     *
     * @param account
     * @param type
     * @return
     */
    @RequestMapping(value = "/send.action", method = RequestMethod.POST)
    public Result sendCode(@RequestParam(value = "account") String account,
                           @RequestParam(value = "type") Integer type) throws ClientException {

        if (!StringUtil.isEmpty(account) && type >= 0) {

            if (StringUtil.isMobile(account)) {
                return userService.sendCode(account, type);
            }
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }


    /**
     * 注册
     * account   -- 账户（手机号）
     * password  -- 密码
     * code      -- 验证码
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/register.action", method = RequestMethod.POST)
    public Result register(@RequestParam Map params) {

        if (MapUtil.containsKeys(params, "account", "password", "code")) {

            if (MapUtil.isNotEmptyStringValues(params, "account", "password", "code")) {
                return userService.register(params);
            }
        }
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }


    /**
     * 登录 -- 账户密码 登录
     * @param account
     * @param password
     * @return
     */
    @RequestMapping(value = "/login.action", method = RequestMethod.POST)
    public Result login(@RequestParam(value = "account") String account,
                        @RequestParam(value = "password") String password) {

        if (!StringUtil.isEmpty(account) && !StringUtil.isEmpty(password)) {
            return userService.login(account, password);
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }


    /**
     * 登录 -- 手机号验证码 登录
     *
     * @param account
     * @param code
     * @return
     */
    @RequestMapping(value = "/loginForCode.action", method = RequestMethod.POST)
    public Result loginCheckCode(@RequestParam(value = "account") String account,
                                 @RequestParam(value = "code") String code) {
        if (!StringUtil.isEmpty(account) && !StringUtil.isEmpty(code)) {
            return userService.loginForCode(account, code);
        }
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }


    /**
     * 忘记密码 -- 修改密码
     *
     * @param account
     * @param code
     * @param password
     * @return
     */
    @RequestMapping(value = "/updatePassword.action", method = RequestMethod.POST)
    public Result updatePassword(@RequestParam(value = "account") String account,
                                 @RequestParam(value = "code") String code,
                                 @RequestParam(value = "password") String password) {

        if (!StringUtil.isEmpty(account) && !StringUtil.isEmpty(code) && !StringUtil.isEmpty(password)) {
            return userService.updatePassword(account, code, password);
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }



    /**
     * 修改头像
     * @param avatar
     * @return
     */
    @Authorization
    @RequestMapping(value = "/updateAvatar.action", method = RequestMethod.POST)
    public Result updateAvatar(@RequestParam(value = "avatar") String avatar) {

        if (!StringUtil.isEmpty(avatar)) {
            return userService.updateAvatar(avatar);
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }

}
