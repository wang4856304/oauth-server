package com.wj.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wj.dao.master.entity.User;
import com.wj.dao.master.repo.UserRepository;
import com.wj.entity.Response;
import com.wj.entity.dto.UserDto;
import com.wj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.util.Date;

/**
 * @author jun.wang
 * @title: UserController
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/4/15 15:56
 */

@RestController
@RequestMapping("/uaa")
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("tokenServices")
    private DefaultTokenServices tokenService;

    @GetMapping("/user")
    public Object user(@RequestParam("access_token") String token){
        JSONObject jsonObject = new JSONObject();
        OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(token);
        if (auth2Authentication == null) {
            jsonObject.put("code", 1001);
            jsonObject.put("msg", "token is not exists");
            logger.info("response:" + jsonObject);
            return jsonObject;
        }
        OAuth2AccessToken auth2AccessToken = tokenStore.readAccessToken(token);
        if (auth2AccessToken != null) {
            Date expireDate = auth2AccessToken.getExpiration();
            if (expireDate.before(new Date())) {
                jsonObject.put("code", 1002);
                jsonObject.put("msg", "token is expired");
                logger.info("response:" + jsonObject);
                return jsonObject;
            }
        }
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        if (tokenStore instanceof JwtTokenStore) {
            JSONObject userJson = new JSONObject();
            userJson.put("username", tokenStore.readAuthentication(token).getPrincipal());
            jsonObject.put("data", userJson);
        }
        else if (tokenStore instanceof JdbcTokenStore){
            jsonObject.put("data", tokenStore.readAuthentication(token).getPrincipal());
        }
        logger.info("response:" + jsonObject);
        return jsonObject;
    }

    @GetMapping("/checkToken")
    public ResponseEntity<JSONObject> checkToken(@RequestParam("access_token") String token) {
        JSONObject jsonObject = new JSONObject();
        OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(token);
        if (auth2Authentication == null) {
            throw new RuntimeException("token is not exists");
        }
        OAuth2AccessToken auth2AccessToken = tokenStore.readAccessToken(token);//过期不抛异常
        if (auth2AccessToken != null) {
            if (auth2AccessToken.isExpired()) {
                throw new InvalidTokenException("token is expired");
            }
        }
        else {
            throw new InvalidTokenException("token is invalid");
        }
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        logger.info("response:" + jsonObject);
        return ResponseEntity.ok().body(jsonObject);
    }

    @GetMapping("/loginOut")
    public ResponseEntity<JSONObject> loginOut(HttpServletRequest request, @RequestParam("access_token") String accessToken) {
        checkToken(accessToken);
        tokenService.revokeToken(accessToken);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        return ResponseEntity.ok().body(jsonObject);
    }

    @GetMapping("/test")
    public String test(){
        return "success";
    }

    @GetMapping("/addUser")
    public Object addUser(@RequestParam String name, @RequestParam String password) {
        User user = new User();
        user.setUserName(name);
        user.setPassword(password);
        user.setStatus(1);
        userRepository.save(user);
        return "success";
    }

    @PostMapping("/login")
    public Object login(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return buildValidErrorJson(bindingResult);
        }
        Response response = new Response();
        response.setData(userService.login(userDto));
        return buildSuccessResponse(response);
    }


    public static void main(String args[]) throws Exception {
        File file = new File("D:\\lll.txt");
        InputStream stream = new FileInputStream(file);
        BufferedReader  reader = new BufferedReader(new InputStreamReader(stream, "utf-8"));
        String tempString = null;
        StringBuffer sb = new StringBuffer();
        while ((tempString = reader.readLine()) != null) {
            sb.append(tempString);
        }
        JSONObject json = JSONObject.parseObject(sb.toString());
        JSONArray measurementsArr = json.getJSONArray("measurements");
        String str = measurementsArr.toJSONString();
        for (int i = 0; i < measurementsArr.size(); i++) {
            JSONObject measurementsJson = measurementsArr.getJSONObject(i);
            if (measurementsJson.containsKey("meta")) {
                String meta = measurementsJson.getString("meta");
                if (meta != null&&meta.length() != 0) {
                    meta = meta.replaceAll("\\\"", "\\\\\\\"");
                    measurementsJson.put("meta", meta);
                }
            }
        }
        JSONArray opSetfieldvalsArr = json.getJSONArray("operations");
        for (int i = 0; i < opSetfieldvalsArr.size(); i++) {
            JSONObject opSetfieldvalsJson = opSetfieldvalsArr.getJSONObject(i);
            if (opSetfieldvalsJson.containsKey("opSetfieldvals")) {
                String opSetfieldvals = opSetfieldvalsJson.getString("opSetfieldvals");
                if (opSetfieldvals != null&&opSetfieldvals.length() != 0) {
                    opSetfieldvals = opSetfieldvals.replaceAll("\\\"", "\\\\\\\"");
                    opSetfieldvalsJson.put("opSetfieldvals", opSetfieldvals);
                }
            }
        }

        JSONArray rulesArr = json.getJSONArray("rules");
        for (int i = 0; i < rulesArr.size(); i++) {
            JSONObject rulesJson = rulesArr.getJSONObject(i);
            if (rulesJson.containsKey("calcParams")) {
                String calcParams = rulesJson.getString("calcParams");
                if (calcParams != null&&calcParams.length() != 0) {
                    calcParams = calcParams.replaceAll("\\\"", "\\\\\\\"");
                    rulesJson.put("calcParams", calcParams);
                }
            }
            if (rulesJson.containsKey("conditionParams")) {
                String conditionParams = rulesJson.getString("conditionParams");
                if (conditionParams != null&&conditionParams.length() != 0) {
                    conditionParams = conditionParams.replaceAll("\\\"", "\\\\\\\"");
                    rulesJson.put("conditionParams", conditionParams);
                }
            }
        }

        String result = json.toJSONString();
        System.out.println(result);
    }
}
