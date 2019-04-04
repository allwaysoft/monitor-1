package com.dennis.api.service;

import com.dennis.common.result.Result;

import java.util.Map;

/**
 * Created by Dennis on 2019/3/31.
 */
public interface ServerService {


    Result add(Map params);

    Result testConnect(Map params);

    Result status(Integer serverId);

    Result deployState(Integer serverId);

    Result deployFlume(Integer serverId);

    Result list();

    Result delete(Integer serverId);

    Result update(Map params);

}
