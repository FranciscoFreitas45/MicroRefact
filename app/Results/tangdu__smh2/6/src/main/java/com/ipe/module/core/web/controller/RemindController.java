package com.ipe.module.core.web.controller;
 import com.ipe.module.core.entity.Remind;
import com.ipe.module.core.service.RemindService;
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
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Controller
@RequestMapping("/remind")
public class RemindController extends AbstractController{

 private  Logger LOG;

@Autowired
 private  RemindService remindService;


@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper add(Remind remind,RestRequest rest){
    try {
        SystemRealm.UserInfo user = (SystemRealm.UserInfo) SecurityUtils.getSubject().getPrincipal();
        remind.setUserId(user.getUserId());
        remind.setCreatedDate(new Date());
        remindService.save(remind);
        return success(remind);
    } catch (Exception e) {
        LOG.error("add error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper edit(Remind remind,RestRequest rest){
    try {
        SystemRealm.UserInfo user = (SystemRealm.UserInfo) SecurityUtils.getSubject().getPrincipal();
        remind.setUserId(user.getUserId());
        remindService.save(remind);
        return success(remind);
    } catch (Exception e) {
        LOG.error("edit error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/del" })
@ResponseBody
public BodyWrapper del(String[] ids,RestRequest rest){
    try {
        remindService.delete(ids);
        return success();
    } catch (Exception e) {
        LOG.error("del error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/list" })
@ResponseBody
public BodyWrapper list(Remind remind,RestRequest rest){
    try {
        int startRow = rest.getStart();
        int endRow = rest.getLimit();
        Page<Remind> page = remindService.findAll(null, new PageRequest(startRow, endRow));
        return success(page);
    } catch (Exception e) {
        LOG.error("query error", e);
        return failure(e);
    }
}


}