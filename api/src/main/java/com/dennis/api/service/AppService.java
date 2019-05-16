package com.dennis.api.service;

import com.dennis.common.result.Result;

import java.util.Map;

/**
 * Created by Dennis on 2019/4/6.
 */
public interface AppService {


    Result info(Integer appId);

    Result edit(Map params);

    Result list(Map params);

    Result delete(Integer appId);

    Result monitor(Integer appId);
}
