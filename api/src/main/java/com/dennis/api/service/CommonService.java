package com.dennis.api.service;

import com.dennis.common.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Dennis on 2019/3/31.
 */

public interface CommonService {


    Result closeWebSocket(Integer code);

    Result upload(MultipartFile file) throws IOException;


}
