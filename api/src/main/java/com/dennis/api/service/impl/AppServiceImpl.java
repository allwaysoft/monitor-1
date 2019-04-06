package com.dennis.api.service.impl;

import com.dennis.api.service.AppService;
import com.dennis.common.enums.ResultEnum;
import com.dennis.common.result.Result;
import com.dennis.common.result.ResultUtil;
import com.dennis.common.tools.DateUtil;
import com.dennis.common.tools.MapUtil;
import com.dennis.dao.entity.App;
import com.dennis.dao.entity.Server;
import com.dennis.dao.repository.AppMapper;
import com.dennis.dao.repository.ServerMapper;
import com.sun.deploy.association.utility.AppAssociationReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Dennis on 2019/4/6.
 */

@Service
public class AppServiceImpl implements AppService {


    @Autowired
    private AppMapper appMapper;

    @Autowired
    private ServerMapper serverMapper;


    @Override
    @Transactional
    public Result add(Map params) {

        App app = new App();
        app.setServerId(MapUtil.getInt(params, "serverId"));
        app.setNickname(MapUtil.getString(params,"nickname"));
        app.setPath(MapUtil.getString(params, "path"));
        app.setRegex(MapUtil.getString(params, "regex"));
        app.setIsDelete(0);
        app.setCreateTime(DateUtil.getCurrentDate());
        app.setIsMonitor(0);

        appMapper.insertSelective(app);
        return ResultUtil.success(ResultEnum.REQUEST_SUCCESS.getMsg());
    }

    @Override
    public Result list(Map params) {

        Integer serverId = MapUtil.getInt(params, "serverId");
        Server server = serverMapper.selectByPrimaryKey(serverId);
        if (server == null)
            return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT.getMsg());

        List<App> apps = appMapper.selectByServerId(params);
        Integer total = appMapper.selectByServerCount(params);

        return ResultUtil.success(ResultEnum.REQUEST_SUCCESS.getMsg(), MapUtil.pageMap(apps, total));
    }

    @Override
    public Result update(Map params) {

        App app = appMapper.selectByPrimaryKey(MapUtil.getInt(params, "appId"));

        if (params.containsKey("serverId"))
            app.setServerId(MapUtil.getInt(params, "serverId"));
        if (params.containsKey("nickname"))
            app.setNickname(MapUtil.getString(params,"nickname"));
        if (params.containsKey("path"))
            app.setPath(MapUtil.getString(params,"path"));
        if (params.containsKey("regex"))
            app.setRegex(MapUtil.getString(params,"regex"));

        appMapper.updateByPrimaryKeySelective(app);

        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS.getMsg());
    }

    @Override
    public Result delete(Integer appId) {

        App app = appMapper.selectByPrimaryKey(appId);
        app.setIsDelete(1);

        appMapper.updateByPrimaryKeySelective(app);

        return ResultUtil.success(ResultEnum.REQUEST_SUCCESS.getMsg());
    }

    @Override
    public Result monitor(Integer appId) {

        App app = appMapper.selectByPrimaryKey(appId);
        app.setIsMonitor(1);
        appMapper.updateByPrimaryKeySelective(app);

        return ResultUtil.success(ResultEnum.REQUEST_SUCCESS.getMsg());
    }
}
