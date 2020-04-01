package com.edu.manger.controller;

import com.edu.manger.constants.Constant;
import com.edu.manger.constants.RestCode;
import com.edu.manger.constants.RestResponse;
import com.edu.manger.dto.ModifyPwdDto;
import com.edu.manger.entry.User;
import com.edu.manger.service.UserService;
import com.edu.manger.utils.PasswordMd5Utils;
import com.edu.manger.utils.SmsUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: LoginController
 * Description:
 * date: 2020/3/11 13:38
 *
 * @author xujin <br/>
 * @since JDK 1.8
 */
@Api(value = "修改密码控制器",description = "修改密码控制器")
@RestController
@CrossOrigin
public class ModifyPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SmsUtils smsUtils;

    @RequestMapping(value = "/modifyPwd",method = RequestMethod.POST)
    @ApiOperation("输入用户名密码登陆")
    public RestResponse<Object> login(@ApiParam("用户id") @RequestParam(value = "id",required = true) String  id,
                                      @ApiParam("新密码") @RequestParam(value = "newPassword",required = true) String newPassword,
                                      @ApiParam("手机号码") @RequestParam(value = "mobile",required = true) String mobile,
                                      @ApiParam("验证码") @RequestParam(value = "verifyCode",required = true) String verifyCode){

        //获取需要更改的用户
      User user =  userService.getUserById(Integer.valueOf(id));
      if (!user.getMobile().equals(mobile)){
          return  RestResponse.error(RestCode.MOBILE_ERROR);
      }
        //rabbmitMq   阿里云发送短信验证码
        //获取缓存中的验证码
         String verCode = (String) redisTemplate.opsForValue().get("code_" + mobile);
          if (StringUtils.isBlank(verCode)){
              return  RestResponse.error(RestCode.VERICODE_ISEMPTY);
          }
         if (!verifyCode.equals(verCode)){
             return  RestResponse.error(RestCode.VERICODE_ERROR);
         }

         User uesr = new User();
         user.setId(Integer.valueOf(id));
         user.setPassword(PasswordMd5Utils.passwordByMd5(newPassword));

         return userService.update(user);
    }

    @RequestMapping(value = "/sendCode",method = RequestMethod.POST)
    @ApiOperation("点击发送验证码")
    public RestResponse sendCode(@ApiParam("手机号码") @RequestParam(value = "mobile",required = true) String mobile){

        //生成验证码
        String code =  RandomStringUtils.randomNumeric(6);
        //redis放一份(有效时长2分钟)
        redisTemplate.opsForValue().set("code_"+mobile,code,2, TimeUnit.MINUTES);
        //向mq发一份，便于发送至用户
        Map<String,String> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("code",code);
        /* 此处发送应交由mq统一发送*/
        rabbitTemplate.convertAndSend("sms",map);
      String cd = (String)redisTemplate.opsForValue().get("code_"+mobile);
      if (StringUtils.isNotBlank(cd)){
          return RestResponse.success(RestCode.OK);
      }
      return RestResponse.error(RestCode.UNKNOW_ERROR);

    }




    @RequestMapping(value = "/modifyPwdInLogin",method = RequestMethod.POST)
    @ApiOperation("登录首页重置密码")
    public RestResponse<Object> modifyPwd(@ApiParam("dto对象") ModifyPwdDto modifyPwdDto){

         User user =   userService.findUserbyName(modifyPwdDto.getUsername());
         if (user == null){
            return RestResponse.error(RestCode.USER_NOT_EXISTED);
         }
        //检查手机号码是否是已绑定的
         if(!user.getMobile().equals(modifyPwdDto.getMobile())){
            return RestResponse.error(RestCode.MOBILE_ERROR);
         }
         //检查身份证是否正确
        if (!user.getIdCard().equals(modifyPwdDto.getIdCard())){
          return   RestResponse.error(RestCode.IDCARD_NOT_BIND);
        }
        //获取验证码
        String verCode = (String) redisTemplate.opsForValue().get("code_" + modifyPwdDto.getMobile());
        if (StringUtils.isBlank(verCode)){
            return  RestResponse.error(RestCode.VERICODE_ISEMPTY);
        }
        if (!modifyPwdDto.getCode().equals(verCode)){
            return  RestResponse.error(RestCode.VERICODE_ERROR);
        }

        User u = new User();
        u.setId(user.getId());
        u.setPassword(PasswordMd5Utils.passwordByMd5(modifyPwdDto.getPassword()));

       return userService.update(u);

    }



}
