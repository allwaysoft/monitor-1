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
 */


@RestController
@RequestMapping("/app")
public class AppController {


    @Autowired
    private AppService appService;

    @Authorization
    @RequestMapping(value = "/add.action", method = RequestMethod.POST)
    public Result add(@RequestParam Map params) {

        if (MapUtil.containsKeys(params, "serverId", "nickname", "path", "regex")) {
            if (MapUtil.isNotEmptyStringValues(params, "serverId", "nickname", "path")) {
                return appService.add(params);
            }
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }

    @Authorization
    @RequestMapping(value = "/list.action", method = RequestMethod.POST)
    public Result list(@RequestParam Map params) {

        if (MapUtil.containsKeys(params, "serverId", "pageNum", "pageSize")){
            if (MapUtil.isNotEmptyStringValues(params, "serverId", "pageNum", "pageSize")) {
                MapUtil.pageFormat(params);
                return appService.list(params);
            }
        }
        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }

    @Authorization
    @RequestMapping(value = "/update.action", method = RequestMethod.POST)
    public Result update(@RequestParam Map params) {

        if (MapUtil.containsKeys(params, "appId")) {
            if (MapUtil.isNotEmptyStringValues(params, "appId")) {
                return appService.update(params);
            }
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }

    @Authorization
    @RequestMapping(value = "/delete.action", method = RequestMethod.POST)
    public Result delete(@RequestParam(value = "appId") Integer appId) {

        if (appId != null && appId > 0) {
            return appService.delete(appId);
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }


    @Authorization
    @RequestMapping(value = "/monitor.action", method = RequestMethod.POST)
    public Result monitor(@RequestParam(value = "appId") Integer appId) {

        if (appId != null && appId > 0) {
            return appService.monitor(appId);
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());
    }

}
