package com.dennis.common.tools;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import net.sf.json.JSONObject;

/**
 * Created by Dennis on 2019/1/15.
 */


public class SMSUtils {


    private static final String ACCESS_KEY_ID = "LTAIQwZVQuwDBmIc";

    private static final String ACCESS_KEY_SECRET = "eOhfOpN0ALhMQutXgmweO28epSRDei";

    // 签名名称
    private static final String SIGN_NAME = "沃柯依";

    // 注册模板
    private static final String REGISTER_TEMPLET = "SMS_153795169";

    // 登录模板
    private static final String LOGIN_TEMPLET = "SMS_155855166";

    // 忘记密码模板
    private static final String FORGET_TEMPLET = "SMS_153885543";


    /**
     * 初始化 IAcsClient 对象
     *
     * @return
     * @throws ClientException
     */
    private static IAcsClient initClient() throws ClientException {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化ascClient需要的几个参数
        final String product = "Dysmsapi";// 短信API产品名称
        final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名
        // 初始化ascClient,暂时不支持多region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        return acsClient;
    }


    /**
     * 组装请求对象
     *
     * @param phone
     * @param code
     * @return
     */
    private static SendSmsRequest getRequest(String phone, String code, Integer type) {

        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(SIGN_NAME);
        //必填:短信模板-可在短信控制台中找到

        // 注册
        if (type == 1)
            request.setTemplateCode(REGISTER_TEMPLET);
        // 登录
        if (type == 2)
            request.setTemplateCode(LOGIN_TEMPLET);
        // 忘记密码
        if (type == 3)
            request.setTemplateCode(FORGET_TEMPLET);


        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        JSONObject object = new JSONObject();
        object.put("code",code);
        request.setTemplateParam(JSON.toJSONString(object));
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        return request;
    }


    /**
     * 发送验证码
     * @param phone
     * @param code
     * @param type
     * @throws ClientException
     */
    public static void sendSMSCode(String phone, String code, Integer type) throws ClientException {

        // 初始化client对象
        IAcsClient client = initClient();

        // 短信模板请求对象
        SendSmsRequest request = getRequest(phone, code, type);

        // 发送短信
        SendSmsResponse response = client.getAcsResponse(request);

        // 打印短信的消息
        System.out.println("_____________发送SMS_72780019短信收到的响应信息_______________");
        System.out.println("请求的ID:" + response.getRequestId());
        System.out.println("请求的状态码:" + response.getCode());
        System.out.println("请求的状态码描述:" + response.getMessage());
        System.out.println("请求的回执ID:" + response.getBizId());
    }
}
