package com.dennis.api.controller;

import com.dennis.api.service.AppService;
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
 * 应用日志
 */


@RestController
@RequestMapping("/app")
public class AppController {


    @Autowired
    private AppService appService;

    @Authorization
    @RequestMapping(value = "/info.action", method = RequestMethod.POST)
    public Result info(@RequestParam(value = "appId") Integer appId){

        if (appId != null && appId > 0){
            return appService.info(appId);
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }

    @Authorization
    @RequestMapping(value = "/edit.action", method = RequestMethod.POST)
    public Result add(@RequestParam Map params) {

        if (MapUtil.containsKeys(params, "serverId", "logName", "path")) {
            if (MapUtil.isNotEmptyStringValues(params, "serverId", "logName", "path")) {
                return appService.edit(params);
            }
        }
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }

    @Authorization
    @RequestMapping(value = "/list.action", method = RequestMethod.POST)
    public Result list(@RequestParam Map params) {

        if (MapUtil.containsKeys(params, "pageNum", "pageSize")) {
            if (MapUtil.isNotEmptyStringValues(params, "pageNum", "pageSize")) {
                MapUtil.pageFormat(params);
                return appService.list(params);
            }
        }
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }


    @Authorization
    @RequestMapping(value = "/delete.action", method = RequestMethod.POST)
    public Result delete(@RequestParam(value = "appId") Integer appId) {
        if (appId != null && appId > 0)
            return appService.delete(appId);
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }


    @Authorization
    @RequestMapping(value = "/monitor.action", method = RequestMethod.POST)
    public Result monitor(@RequestParam(value = "appId") Integer appId) {

        if (appId != null && appId > 0) {
            return appService.monitor(appId);
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }

}
