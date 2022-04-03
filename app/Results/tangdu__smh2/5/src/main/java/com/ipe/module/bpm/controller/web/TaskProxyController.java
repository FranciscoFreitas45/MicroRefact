package com.ipe.module.bpm.controller.web;
 import com.ipe.module.bpm.entity.TaskProxy;
import com.ipe.module.bpm.service.TaskProxyService;
import com.ipe.module.core.service.UserService;
import com.ipe.module.core.web.controller.AbstractController;
import com.ipe.module.core.web.security.SystemRealm;
import com.ipe.module.core.web.util.BodyWrapper;
import com.ipe.module.core.web.util.RestRequest;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
@Controller
@RequestMapping("/taskProxy")
public class TaskProxyController extends AbstractController{

 private  Logger LOG;

@Autowired
 private  TaskProxyService taskProxyService;

@Autowired
 private  UserService userService;


@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper add(TaskProxy taskProxy,String userToId){
    try {
        SystemRealm.UserInfo user = (SystemRealm.UserInfo) SecurityUtils.getSubject().getPrincipal();
        if (user.getUserId().equals(userToId)) {
            throw new Exception("代理人不能同于委托人!");
        }
        taskProxy.setUserForm(userService.get(user.getUserId()));
        taskProxy.setUserTo(userService.get(userToId));
        taskProxy.setCreatedDate(new Date());
        taskProxy.setStatus("1");
        taskProxyService.save(taskProxy);
        return success(taskProxy);
    } catch (Exception e) {
        LOG.error("add error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper edit(TaskProxy taskProxy,String userToId){
    try {
        SystemRealm.UserInfo user = (SystemRealm.UserInfo) SecurityUtils.getSubject().getPrincipal();
        if (user.getUserId().equals(userToId)) {
            throw new Exception("代理人不能同于委托人!");
        }
        taskProxy.setUserForm(userService.get(user.getUserId()));
        taskProxy.setUserTo(userService.get(userToId));
        taskProxy.setStatus("1");
        taskProxyService.save(taskProxy);
        return success(taskProxy);
    } catch (Exception e) {
        LOG.error("edit error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/udpateStatus" })
@ResponseBody
public BodyWrapper udpateStatus(String[] ids){
    try {
        taskProxyService.udpateStatus(ids);
        return success();
    } catch (Exception e) {
        LOG.error("del error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/del" })
@ResponseBody
public BodyWrapper del(String[] ids,RestRequest rest){
    try {
        taskProxyService.delete(ids);
        return success();
    } catch (Exception e) {
        LOG.error("del error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/list" })
@ResponseBody
public BodyWrapper list(TaskProxy taskProxy,RestRequest rest){
    try {
        int startRow = rest.getStart();
        int endRow = rest.getLimit();
        Page<TaskProxy> page = taskProxyService.findAll(null, new PageRequest(startRow, endRow));
        return success(page);
    } catch (Exception e) {
        LOG.error("query error", e);
        return failure(e);
    }
}


}