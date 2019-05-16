package com.dennis.api.controller;

import com.dennis.api.service.CommonService;
import com.dennis.common.annotation.Authorization;
import com.dennis.common.enums.ResultEnum;
import com.dennis.common.result.Result;
import com.dennis.common.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Dennis on 2019/3/30.
 */


@RestController
@RequestMapping("common")
public class CommonController {


    @Autowired
    private CommonService commonService;


    /**
     * 关闭websocket连接
     * @param code
     * @return
     */
    @Authorization
    @RequestMapping(value = "/closeWebSocket.action", method = RequestMethod.POST)
    public Result closeWebSocket(@RequestParam(value = "code") Integer code){

        if (code != null && code > 0){
            return commonService.closeWebSocket(code);
        }

        return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT);
    }


    /**
     * 图片上传 -- 七牛云
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload.action", method = RequestMethod.POST)
    public Result upload(@RequestParam(value = "file") MultipartFile file) throws IOException {

        if (file != null)
            return commonService.upload(file);

        return ResultUtil.error(ResultEnum.FILE_NOT_EXIST);
    }
}
