package com.dennis.api.service.impl;

import com.dennis.api.service.ServerService;
import com.dennis.api.ssh.SSHTemplate;
import com.dennis.common.constants.CommandConstant;
import com.dennis.common.enums.ResultEnum;
import com.dennis.common.result.Result;
import com.dennis.common.result.ResultUtil;
import com.dennis.common.tools.DateUtil;
import com.dennis.common.tools.MapUtil;
import com.dennis.common.tools.RequestUtils;
import com.dennis.dao.entity.Server;
import com.dennis.dao.repository.ServerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Dennis on 2019/3/31.
 */


@Service
public class ServerServiceImpl implements ServerService {


    @Autowired
    private ServerMapper serverMapper;


    @Override
    @Transactional
    public Result add(Map params) {

        Server server = new Server();
        server.setUserId(RequestUtils.getPkId());
        server.setHost(MapUtil.getString(params, "host"));
        server.setUsername(MapUtil.getString(params, "username"));
        server.setPassword(MapUtil.getString(params, "password"));
        server.setPort(MapUtil.getString(params, "port"));
        server.setNickname(MapUtil.getString(params, "nickname"));
        server.setIsDelete(0);
        server.setIsDeploy(0);
        server.setCreateTime(DateUtil.getCurrentDate());
        server.setUpdateTime(DateUtil.getCurrentDate());

        serverMapper.insertSelective(server);

        return ResultUtil.success(ResultEnum.REQUEST_SUCCESS.getMsg());
    }

    @Override
    public Result testConnect(Map params) {

        if (SSHTemplate.testConnect(params) == true)
            return ResultUtil.success(ResultEnum.REQUEST_SUCCESS.getMsg());
        else
            return ResultUtil.error(ResultEnum.REQUEST_FAIL.getMsg());
    }

    @Override
    public Result status(Integer serverId) {

        Server server = serverMapper.selectByPrimaryKey(serverId);

        List<String> commands = new ArrayList<>();
        commands.add(CommandConstant.IOSTAT_CPU_COMMAND);
        commands.add(CommandConstant.FREE_MEMORY_COMMAND);
        commands.add(CommandConstant.DISK_DF_COMMAND);


        return null;
    }

    @Override
    public Result deployState(Integer serverId) {
        return null;
    }

    @Override
    public Result deployFlume(Integer serverId) {
        return null;
    }

    @Override
    public Result list() {

        Integer userId = RequestUtils.getPkId();
        List<Map> servers = serverMapper.selectByUserId(userId);

        return ResultUtil.success(ResultEnum.REQUEST_SUCCESS.getMsg(), servers);
    }

    @Override
    @Transactional
    public Result delete(Integer serverId) {

        Server server = serverMapper.selectByPrimaryKey(serverId);
        server.setIsDelete(1);
        serverMapper.updateByPrimaryKeySelective(server);

        return ResultUtil.success(ResultEnum.REQUEST_SUCCESS.getMsg());
    }

    @Override
    @Transactional
    public Result update(Map params) {

        Server server = serverMapper.selectByPrimaryKey(MapUtil.getInt(params,"serverId"));
        server.setUpdateTime(DateUtil.getCurrentDate());
        server.setHost(MapUtil.getString(params, "host"));
        server.setUsername(MapUtil.getString(params, "username"));
        server.setPassword(MapUtil.getString(params, "password"));
        server.setNickname(MapUtil.getString(params, "nickname"));
        server.setPort(MapUtil.getString(params, "port"));

        serverMapper.updateByPrimaryKeySelective(server);

        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS.getMsg());
    }
}
