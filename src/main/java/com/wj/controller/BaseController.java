package com.wj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wj.entity.Response;
import com.wj.utils.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Date;
import java.util.List;

/**
 * @author wangjun
 * @date 18-2-23 下午2:33
 * @description
 * @modified by
 */
public abstract class BaseController {
    private static Log logger = LogFactory.getLog(BaseController.class);


    public Object buildResponse(Response response) {
        response.setServerTime(DateUtil.formartDate(new Date(), DateUtil.YYYY_MM_DD_HH_MM_SS));
        logger.info("response:" + JSONObject.toJSONString(response));
        return response;
    }


    public Object buildValidErrorJson(BindingResult bindingResult) {
        Response response = new Response();
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            for (FieldError fieldError: fieldErrorList) {
                String errMsg = fieldError.getDefaultMessage();
                response.setCode("1001");
                response.setMsg(errMsg);
                break;
            }
        }
        response.setServerTime(DateUtil.formartDate(new Date(), DateUtil.YYYY_MM_DD_HH_MM_SS));
        logger.info("response:" + JSONObject.toJSONString(response));
        return response;
    }
}
