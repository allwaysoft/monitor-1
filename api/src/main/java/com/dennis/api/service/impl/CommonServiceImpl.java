package com.dennis.api.service.impl;

import com.dennis.api.service.CommonService;
import com.dennis.common.enums.ResultEnum;
import com.dennis.common.result.Result;
import com.dennis.common.result.ResultUtil;
import com.dennis.common.tools.MapUtil;
import com.dennis.common.tools.QiNiuYunUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by Dennis on 2019/3/31.
 */

@Service
public class CommonServiceImpl implements CommonService {



    @Override
    public Result upload(MultipartFile file) throws IOException {

        // 获取文件名
        String fileName = file.getOriginalFilename();

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        // 拼接文件名
        String name = UUID.randomUUID().toString().replace("-","") + suffixName;

        // 上传图片到七牛云
        String url = QiNiuYunUtil.uploadImg(file.getInputStream(), name);

        if (url == null)
            return ResultUtil.error(ResultEnum.UPLOAD_FAIL.getMsg());

        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS.getMsg(), MapUtil.resultMap("url","http://"+url));

    }
}
