package com.dennis.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.dennis.api.service.ServerService;
import com.dennis.api.ssh.SSHTemplate;
import com.dennis.api.ssh.ServerUtil;
import com.dennis.common.constants.CommandConstant;
import com.dennis.common.enums.ResultEnum;
import com.dennis.common.result.Result;
import com.dennis.common.result.ResultUtil;
import com.dennis.common.tools.DateUtil;
import com.dennis.common.tools.MapUtil;
import com.dennis.common.tools.RequestUtils;
import com.dennis.dao.entity.Server;
import com.dennis.dao.repository.ServerMapper;
import com.jcraft.jsch.ChannelSftp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

        Map results = SSHTemplate.batchExeC(server, commands);
        Map result = new HashMap();

        result.put("cpu",MapUtil.getString(results,CommandConstant.IOSTAT_CPU_COMMAND).trim());
        ServerUtil.memoryFormat(result, MapUtil.getString(results, CommandConstant.FREE_MEMORY_COMMAND));
        ServerUtil.diskFormat(result, MapUtil.getString(results, CommandConstant.DISK_DF_COMMAND));

        System.out.println(JSON.toJSONString(results));

        return ResultUtil.success(ResultEnum.REQUEST_SUCCESS.getMsg(), result);
    }

    @Override
    public Result deploy(Map params) {

        Server server = new Server();
        server.setUsername(MapUtil.getString(params, "username"));
        server.setHost(MapUtil.getString(params, "host"));
        server.setPassword(MapUtil.getString(params, "password"));
        server.setPort(MapUtil.getString(params, "port"));

        SSHTemplate.upload(server, CommandConstant.SPEED_TEST_PATH, CommandConstant.DEPLOY_PATH, CommandConstant.SPEED_TEST);

        // 检测是否按照 sysstat\expect
        String resultStat = SSHTemplate.exec(server, CommandConstant.CHECK_SYSSTAT);
        if (resultStat != null)
            if (Integer.valueOf(resultStat.trim()) == 0)
                SSHTemplate.exec(server, CommandConstant.SYSSTAT_INSTALL);

        String resultExpect = SSHTemplate.exec(server, CommandConstant.CHECK_EXPECT);
        if (resultExpect != null)
            if (Integer.valueOf(resultExpect.trim()) == 0)
                SSHTemplate.exec(server, CommandConstant.EXPECT_INSTALL);

        // 上传 flume
        SSHTemplate.upload(server, CommandConstant.FLUME_PATH, CommandConstant.DEPLOY_PATH, CommandConstant.FLUME);

        // 更新 目标主机 部署状态
        List<Server> servers = serverMapper.selectByHost(server.getHost());
        servers.forEach(item -> {
            item.setIsDeploy(1);
            serverMapper.updateByPrimaryKeySelective(item);
        });

        return ResultUtil.success(ResultEnum.REQUEST_SUCCESS.getMsg());
    }

    @Override
    public Result ls(Map params) {

        Server server = serverMapper.selectByPrimaryKey(MapUtil.getInt(params, "serverId"));

        String path = MapUtil.getString(params, "path");
        Vector<ChannelSftp.LsEntry> vector = SSHTemplate.listFiles(server,path);

        for (ChannelSftp.LsEntry entry : vector){
            System.out.println(entry.getFilename());
            System.out.println(entry.getAttrs().isDir());
            System.out.println(entry.getLongname());
        }

        return null;
    }

    @Override
    public Result list(Map params) {

        params.put("userId",RequestUtils.getPkId());
        List<Map> servers = serverMapper.selectByUserId(params);
        Integer total = serverMapper.selectByUserCount(params);

        return ResultUtil.success(ResultEnum.REQUEST_SUCCESS.getMsg(), MapUtil.pageMap(servers,total));
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

        Server server = serverMapper.selectByPrimaryKey(MapUtil.getInt(params, "serverId"));
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
