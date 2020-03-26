package com.edu.manger.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.edu.manger.constants.Constant;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.aliyuncs.http.MethodType;


/**
 * ClassName: SmsUtils
 * Description:
 * date: 2020/3/25 21:14
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@Component
public class SmsUtils {

   @Autowired
    private  Environment env;

   @Autowired
   private RedisTemplate redisTemplate;

    /**
     * 返回手机验证码
     * @param phoneNumber
     * @param
     * @param
     * @return
     */
    public  String getVeriCode(String code,String phoneNumber){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",  env.getProperty("aliyun.sms.AccessKeyId"), env.getProperty("aliyun.sms.AccessKeySecret"));
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", Constant.SIGN_NAME);
        request.putQueryParameter("TemplateCode",Constant.TEMPLETE_CODE);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        CommonResponse response = null;
        try {
            response = client.getCommonResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return response.getData();

    }
}
