package com.dennis.api.service.impl;

import com.dennis.api.async.SSHAsync;
import com.dennis.api.service.AppService;
import com.dennis.api.ssh.SSHTemplate;
import com.dennis.api.websocket.WebSocket;
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
    private WebSocket webSocket;

    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private SSHAsync sshAsync;


    @Override
    public Result info(Integer appId) {
        Map info = appMapper.selectInfoByPrimaryKey(appId);
        return ResultUtil.success(ResultEnum.REQUEST_SUCCESS.getMsg(), info);
    }

    @Override
    @Transactional
    public Result edit(Map params) {


        App app = null;

        if (params.containsKey("pkId")) {
            app = appMapper.selectByPrimaryKey(MapUtil.getInt(params, "pkId"));
        } else {
            app = new App();
            app.setIsDelete(0);
            app.setCreateTime(DateUtil.getCurrentDate());
            app.setIsMonitor(0);
        }

        app.setServerId(MapUtil.getInt(params, "serverId"));
        app.setNickname(MapUtil.getString(params, "logName"));
        app.setPath(MapUtil.getString(params, "path"));

        if (params.containsKey("pkId"))
            appMapper.updateByPrimaryKeySelective(app);
        else
            appMapper.insertSelective(app);

        return ResultUtil.success(ResultEnum.REQUEST_SUCCESS.getMsg());
    }

    @Override
    public Result list(Map params) {

        List<App> apps = appMapper.selectByServerId(params);
        Integer total = appMapper.selectByServerCount(params);

        return ResultUtil.success(ResultEnum.REQUEST_SUCCESS.getMsg(), MapUtil.pageMap(apps, total));
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
        if (app != null) {

            Server server = serverMapper.selectByPrimaryKey(app.getServerId());
            if (server != null) {
                sshAsync.execTail(app, server, webSocket);
                return ResultUtil.success(ResultEnum.REQUEST_SUCCESS);
            }
        }

        return ResultUtil.error(ResultEnum.PATH_ERROR);
    }
}
