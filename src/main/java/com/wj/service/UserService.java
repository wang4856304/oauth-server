package com.wj.service;

import com.wj.entity.dto.UserDto;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * @author jun.wang
 * @title: UserService
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/8/6 15:52
 */
public interface UserService {


    OAuth2AccessToken login(UserDto userDto);
}
