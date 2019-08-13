package com.wj.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wj.entity.dto.UserDto;
import com.wj.service.UserService;
import com.wj.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jun.wang
 * @title: UserServiceImpl
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/8/6 15:53
 */

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public OAuth2AccessToken login(UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        Map<String, Object> param = new HashMap<>();
        param.put("username", username);
        param.put("password", password);
        param.put("grant_type", "password");
        param.put("client_id", "client_2");
        param.put("client_secret", "123456");

        try {
            String result = HttpClientUtil.httpPostRequest("http://localhost:9920/oauth/token", param);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.containsKey("error")) {
                logger.error(result);
                throw new RuntimeException("login fail");
            }
            System.out.println(result);
            return DefaultOAuth2AccessToken.valueOf(JSONObject.parseObject(result, Map.class)) ;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
