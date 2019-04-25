package com.wj.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
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
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private TokenStore tokenStore;

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
        jsonObject.put("data", tokenStore.readAuthentication(token).getPrincipal());
        logger.info("response:" + jsonObject);
        return jsonObject;
    }

    @GetMapping("/test")
    public String test(){
        return "success";
    }


    public static void main(String args[]) throws Exception {

        JSONObject jsonObject = JSONObject.parseObject("{\n" +
                "            \"actionId\": \"2\",\n" +
                "            \"actionName\": \"触发报警\",\n" +
                "            \"calcParams\": \"[\\\"\\\"]\",\n" +
                "            \"conditionParams\": \"[\\\"D2_HK_FAULT.pv==\\\\\\\"True\\\\\\\"\\\"]\",\n" +
                "            \"createBy\": \"ssgrtnykjyxgs8\",\n" +
                "            \"createDate\": \"2019-03-08 00:18:22\",\n" +
                "            \"isAlarmpersistent\": 1,\n" +
                "            \"modelId\": \"T2M1U0NSQ38W8\",\n" +
                "            \"ruleDesc\": \"2#冷干机过载\",\n" +
                "            \"ruleId\": \"2QYPU59PZ1S8\",\n" +
                "            \"ruleName\": \"D2_HK_FAULT\",\n" +
                "            \"targetName\": \"D2_HK_FAULT\",\n" +
                "            \"targetid\": \"2QRHC5U47XC8\",\n" +
                "            \"tenantId\": \"4I1CQ08\",\n" +
                "            \"updateBy\": \"ssgrtnykjyxgs8\",\n" +
                "            \"updateDate\": \"2019-03-08 00:18:22\"\n" +
                "        }");

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
