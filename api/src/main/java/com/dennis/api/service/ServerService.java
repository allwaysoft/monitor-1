package com.dennis.api.service;

import com.dennis.common.result.Result;

import java.util.Map;

/**
 * Created by Dennis on 2019/3/31.
 */
public interface ServerService {


    Result save(Map params);

    Result testConnect(Map params);

    Result date(Integer serverId);

    Result status(Integer serverId);

    Result deploy(Map params);

    Result ls(Map params);

    Result list(Map params);

    Result selectList();


    Result delete(Integer serverId);



}
