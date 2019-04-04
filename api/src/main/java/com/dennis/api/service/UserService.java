package com.dennis.api.service;

import com.aliyuncs.exceptions.ClientException;
import com.dennis.common.result.Result;

import java.util.Map;

/**
 * Created by Dennis on 2019/3/30.
 */
public interface UserService {


    Result sendCode(String account, Integer type) throws ClientException;

    Result register(Map params);

    Result login(String account, String password);

    Result loginForCode(String account, String code);

    Result updatePassword(String account, String code, String password);

    Result updateAvatar(String avatar);
}
