package com.dennis.api.controller;

import com.dennis.api.service.ServerService;
import com.dennis.common.annotation.Authorization;
import com.dennis.common.enums.ResultEnum;
import com.dennis.common.result.Result;
import com.dennis.common.result.ResultUtil;
import com.dennis.common.tools.MapUtil;
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
    @RequestMapping(value = "/save.action", method = RequestMethod.POST)
    public Result add(@RequestParam Map params) {

        if (MapUtil.containsKeys(params, "host", "username", "password", "port", "nickname")) {
            if (MapUtil.isNotEmptyStringValues(params, "host", "username", "password", "port", "nickname")) {
                return serverService.save(params);
            }
        }
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }


    @Authorization
    @RequestMapping(value = "/testConnect.action", method = RequestMethod.POST)
    public Result testConnect(@RequestParam Map params) {

        if (MapUtil.containsKeys(params, "host", "username", "password", "port")) {
            if (MapUtil.isNotEmptyStringValues(params, "host", "username", "password", "port")) {
                return serverService.testConnect(params);
            }
        }
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }


    @Authorization
    @RequestMapping(value = "/deploy.action", method = RequestMethod.POST)
    public Result deploy(@RequestParam Map params) {

        if (MapUtil.containsKeys(params, "host", "username", "password", "port")) {
            if (MapUtil.isNotEmptyStringValues(params, "host", "username", "password", "port")) {
                return serverService.deploy(params);
            }
        }
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }

    @Authorization
    @RequestMapping(value = "/runInfo.action", method = RequestMethod.POST)
    public Result date(@RequestParam Integer serverId){

        if (serverId != null && serverId > 0){
            return serverService.date(serverId);
        }
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }

    @Authorization
    @RequestMapping(value = "/status.action", method = RequestMethod.POST)
    public Result status(@RequestParam(value = "serverId") Integer serverId) {

        if (serverId != null && serverId > 0)
            return serverService.status(serverId);
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }


    @Authorization
    @RequestMapping(value = "/ls.action", method = RequestMethod.POST)
    public Result ls(@RequestParam Map params) {

        if (MapUtil.containsKeys(params, "serverId", "path"))
            if (MapUtil.isNotEmptyStringValues(params, "serverId", "path"))
                return serverService.ls(params);

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }


    @Authorization
    @RequestMapping(value = "/list.action", method = RequestMethod.POST)
    public Result list(@RequestParam Map params) {
        if (MapUtil.containsKeys(params, "pageNum","pageSize")){
            if (MapUtil.isNotEmptyStringValues(params, "pageNum","pageSize")){
                MapUtil.pageFormat(params);
                return serverService.list(params);
            }
        }
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }


    @Authorization
    @RequestMapping(value = "/selectList.action", method = RequestMethod.POST)
    public Result selectList(){
        return serverService.selectList();
    }

    @Authorization
    @RequestMapping(value = "/delete.action", method = RequestMethod.POST)
    public Result delete(@RequestParam(value = "serverId") Integer serverId) {

        if (serverId != null && serverId > 0) {
            return serverService.delete(serverId);
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }

}
