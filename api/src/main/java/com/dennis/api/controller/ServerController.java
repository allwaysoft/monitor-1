package com.dennis.api.controller;

import com.dennis.api.service.ServerService;
import com.dennis.common.annotation.Authorization;
import com.dennis.common.enums.ResultEnum;
import com.dennis.common.result.Result;
import com.dennis.common.result.ResultUtil;
import com.dennis.common.tools.MapUtil;
import com.dennis.dao.repository.ServerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Dennis on 2019/3/31.
 */


@RestController
@RequestMapping("/server")
public class ServerController {


    @Autowired
    private ServerService serverService;


    @Authorization
    @RequestMapping(value = "/add.action", method = RequestMethod.POST)
    public Result add(@RequestParam Map params) {

        if (MapUtil.containsKeys(params, "host", "username", "password", "port", "nickname")) {
            if (MapUtil.isNotEmptyStringValues(params, "host", "username", "password", "port", "nickname")) {
                return serverService.add(params);
            }
        }
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }


    @Authorization
    @RequestMapping(value = "/testConnect.action", method = RequestMethod.POST)
    public Result testConnect(@RequestParam Map params) {

        if (MapUtil.containsKeys(params, "host", "username", "password", "port")) {
            if (MapUtil.isNotEmptyStringValues(params, "host", "username", "password", "port")) {
                return serverService.testConnect(params);
            }
        }
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }


    @Authorization
    @RequestMapping(value = "/deployState.action", method = RequestMethod.POST)
    public Result deployState(@RequestParam(value = "serverId") Integer serverId) {

        if (serverId != null && serverId > 0)
            return serverService.deployState(serverId);

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }


    @Authorization
    @RequestMapping(value = "/deployFlume.action", method = RequestMethod.POST)
    public Result deployFlume(@RequestParam(value = "serverId") Integer serverId) {

        if (serverId != null && serverId > 0)
            return serverService.deployFlume(serverId);

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }


    @Authorization
    @RequestMapping(value = "/status.action", method = RequestMethod.POST)
    public Result status(@RequestParam(value = "serverId") Integer serverId) {

        if (serverId != null && serverId > 0)
            return serverService.status(serverId);
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }


    @Authorization
    @RequestMapping(value = "/list.action", method = RequestMethod.POST)
    public Result list() {
        return serverService.list();
    }


    @Authorization
    @RequestMapping(value = "/delete.action", method = RequestMethod.POST)
    public Result delete(@RequestParam(value = "serverId") Integer serverId) {

        if (serverId != null && serverId > 0) {
            return serverService.delete(serverId);
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }


    @Authorization
    @RequestMapping(value = "/update.action", method = RequestMethod.POST)
    public Result update(@RequestParam Map params) {

        if (MapUtil.containsKeys(params, "serverId", "host", "username", "password", "port", "nickname")) {
            if (MapUtil.isNotEmptyStringValues(params, "serverId", "host", "username", "password", "port", "nickname")) {
                return serverService.update(params);
            }
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }


}
