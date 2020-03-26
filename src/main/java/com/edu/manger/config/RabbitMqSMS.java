package com.edu.manger.config;

import com.aliyuncs.exceptions.ClientException;
import com.edu.manger.constants.Constant;
import com.edu.manger.utils.SmsUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ClassName: RabbitMqSMS
 * Description:
 * date: 2020/3/26 12:47
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@Component
@RabbitListener(queues = "sms") //监听sms队列
public class RabbitMqSMS {

    @Autowired
    private SmsUtils smsUtils;

    @RabbitHandler
    public void sendSms(Map<String,String> map){
        //获取手机号和验证码
        String mobile = map.get("mobile");
        String code = map.get("code");
        smsUtils.getVeriCode(code,mobile);



    }

}
