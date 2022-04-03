package com.kingen.web;
 import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kingen.bean.User;
import com.kingen.service.account.AccountService;
import com.kingen.util.Digests;
import com.kingen.util.Encodes;
import com.kingen.util.Json;
import com.kingen.util.JsonResultBuilder;
import Interface.AccountService;
import DTO.Json;
import DTO.User;
@Controller
@RequestMapping(value = "/validate")
public class ValidateController extends CommonController{

 private  Logger logger;

@Autowired
 private  AccountService service;


@RequestMapping(value = "checkunique", method = RequestMethod.POST)
@ResponseBody
public Object checkunique(String beanClazz,String property,String val,String rawValue,String action){
    try {
        boolean exist = CollectionUtils.isEmpty(service.getEntityBy(beanClazz, property, val, rawValue, action)) ? false : true;
        // return ExtUtils.mapValidate(exist);
        return JsonResultBuilder.success(true).data(exist).json();
    // throw new Exception();
    } catch (Exception e) {
        // TODO 做成过滤器
        logger.error(e.getMessage());
        e.printStackTrace();
        // return ExtUtils.mapError("系统错误");
        return JsonResultBuilder.success(false).msg("系统错误").json();
    }
}


@RequestMapping(value = "checkpwd", method = RequestMethod.POST)
public void checkpwd(String val,HttpServletResponse response){
    Json json = new Json();
    try {
        User u = getCurrentUser();
        // User u = new User();
        // u.setPassword("2d27b0a003da078b848cb29d98f07fd8e8cff0ec");
        // u.setSalt("5a4911aafc1f2e3a");
        byte[] hashPassword = Digests.sha1(val.getBytes(), Encodes.decodeHex(u.getSalt()), AccountService.HASH_INTERATIONS);
        String encrypted = Encodes.encodeHex(hashPassword);
        json.setSuccess(u.getPassword().equals(encrypted));
        writeJson(response, json);
    } catch (Exception e) {
        // TODO 做成过滤器
        logger.error(e.getMessage());
        e.printStackTrace();
        json.setMsg("系统错误");
        writeJson(response, json);
    }
}


}