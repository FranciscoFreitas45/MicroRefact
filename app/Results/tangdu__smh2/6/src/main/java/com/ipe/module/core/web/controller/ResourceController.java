package com.ipe.module.core.web.controller;
 import com.ipe.module.core.entity.Resource;
import com.ipe.module.core.service.ResourceService;
import com.ipe.module.core.web.util.BodyWrapper;
import com.ipe.module.core.web.util.RestRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
@Controller
@RequestMapping("/resource")
public class ResourceController extends AbstractController{

 private  Logger LOGGER;

@Autowired
 private  ResourceService resourceService;


@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper add(Resource resource,RestRequest rest){
    try {
        resourceService.saveResource(resource);
        return success(resource);
    } catch (Exception e) {
        LOGGER.error("ERROR", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/getResources" })
@ResponseBody
public BodyWrapper getMenus(String pid){
    try {
        List<Resource> data = resourceService.getResources(pid);
        return success(data);
    } catch (Exception e) {
        LOGGER.error("ERROR", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper edit(Resource resource,RestRequest rest){
    try {
        Resource parent = resourceService.get(resource.getParent().getId());
        resource.setParent(parent);
        resource.setUpdatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        resourceService.save(resource);
        return success(resource);
    } catch (Exception e) {
        LOGGER.error("ERROR", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/del" })
@ResponseBody
public BodyWrapper del(String[] ids,RestRequest rest){
    try {
        resourceService.delete(ids);
        return success();
    } catch (Exception e) {
        LOGGER.error("ERROR", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/list" })
@ResponseBody
public BodyWrapper list(Resource resource,RestRequest rest){
    try {
        int startRow = rest.getStart();
        int endRow = rest.getLimit();
        Page<Resource> page = resourceService.findAll(null, new PageRequest(startRow, endRow));
        return success(page);
    } catch (Exception e) {
        LOGGER.error("ERROR", e);
        return failure(e);
    }
}


}