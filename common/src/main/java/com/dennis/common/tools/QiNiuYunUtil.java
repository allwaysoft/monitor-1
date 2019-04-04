package com.dennis.common.tools;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.InputStream;

/**
 * Created by Dennis on 2018/11/26.
 */
public class QiNiuYunUtil {

    private static String accessKey = "tSkJrr7Q178UFxtjJAZW2-mcof68JFoP3TTUIwLe";

    private static String secretKey = "FRC-UbVR5AcXCWGXChzipDtbXO49wOq00LX6RIeD";

    private static String bucket = "huichexian";

    private static String path = "hcx.jswkyit.com";


    /**
     * file  二进制流
     * key 图片名称
     * @param file
     * @param key
     * @return
     */
    public static String uploadImg(InputStream file, String key) {

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
                String return_path = path + "/" + putRet.key;
                return return_path;
            } catch (QiniuException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
