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
import org.springframework.web.bind.annotation.*;
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

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
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
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }


    /**
     * 登录 -- 账户密码 登录
     *
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

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }


    @Authorization
    @RequestMapping(value = "/info.action", method = RequestMethod.POST)
    public Result info() {
        return userService.info();
    }


    @Authorization
    @RequestMapping(value = "/noticeInfo.action", method = RequestMethod.POST)
    public Result noticeInfo() {
        return userService.noticeInfo();
    }

    @Authorization
    @RequestMapping(value = "/updateNotice.action", method = RequestMethod.POST)
    public Result updateNotice(@RequestParam Map params) {

        if (MapUtil.containsKeys(params, "isNotice", "email")) {
            return userService.updateNotice(params);
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }

    @Authorization
    @RequestMapping(value = "/logout.action", method = RequestMethod.POST)
    public Result logout() {
        return userService.logout();
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

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }


    /**
     * 重置密码
     *
     * @param params
     * @return
     */
    @Authorization
    @RequestMapping(value = "/resetPassword.action", method = RequestMethod.POST)
    public Result resetPassword(@RequestParam Map params) {

        if (MapUtil.containsKeys(params, "oldPass", "newPass")) {
            if (MapUtil.isNotEmptyStringValues(params, "oldPass", "newPass")){
                return userService.resetPassword(MapUtil.getString(params, "oldPass"), MapUtil.getString(params, "newPass"));
            }
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }


    /**
     * 修改个人资料
     * userId -- 用户ID
     * nickname -- 昵称
     * sex -- 性别
     *
     * @param params
     * @return
     */
    @Authorization
    @RequestMapping(value = "/updateInfo.action", method = RequestMethod.POST)
    public Result updateInfo(@RequestParam Map params) {
        return userService.update(params);
    }


}
