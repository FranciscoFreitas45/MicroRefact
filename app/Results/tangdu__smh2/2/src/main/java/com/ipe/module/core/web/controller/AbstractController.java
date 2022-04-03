package com.ipe.module.core.web.controller;
 import com.alibaba.fastjson.JSON;
import com.ipe.common.web.BaseController;
import com.ipe.module.core.web.util.BodyWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
public class AbstractController extends BaseController{

 private  Logger LOGGER;

 private  BodyWrapper bodyWrapper;


public BodyWrapper success(Page<?> page){
    getWrapper();
    bodyWrapper.setRows(page.getContent());
    bodyWrapper.setTotal(page.getTotalElements());
    return bodyWrapper;
}


public BodyWrapper failure(Exception error){
    getWrapper();
    bodyWrapper.setSuccess(false);
    bodyWrapper.setRows(error.getMessage());
    return bodyWrapper;
}


public void renderSuccess(Object info,HttpServletResponse response){
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html");
    if (info != null) {
        Map<String, Object> data = getMap();
        data.put("success", true);
        data.put("data", info);
        try {
            response.getOutputStream().print(JSON.toJSONString(data));
            response.getOutputStream().close();
        } catch (IOException e) {
            LOGGER.error("Exception {}", e);
        }
    }
}


public void renderFailure(Object info,HttpServletResponse response){
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html");
    if (info != null) {
        Map<String, Object> data = getMap();
        data.put("success", false);
        data.put("data", info);
        try {
            response.getOutputStream().print(JSON.toJSONString(data));
            response.getOutputStream().close();
        } catch (IOException e) {
            LOGGER.error("Exception {}", e);
        }
    }
}


public BodyWrapper getWrapper(){
    bodyWrapper = new BodyWrapper();
    return bodyWrapper;
}


public void render(Map info,HttpServletResponse response){
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html");
    if (info != null) {
        try {
            response.getOutputStream().print(JSON.toJSONString(info));
            response.getOutputStream().close();
        } catch (IOException e) {
            LOGGER.error("Exception {}", e);
        }
    }
}


}