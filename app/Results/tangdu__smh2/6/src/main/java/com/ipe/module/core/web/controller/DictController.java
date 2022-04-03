package com.ipe.module.core.web.controller;
 import com.ipe.common.util.Logs;
import com.ipe.module.core.entity.Dict;
import com.ipe.module.core.service.DictService;
import com.ipe.module.core.web.util.BodyWrapper;
import com.ipe.module.core.web.util.RestRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/dict")
public class DictController extends AbstractController{

 private  Logger LOG;

@Autowired
 private  DictService dictService;


@Logs(opdesc = "添加字典库")
@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper add(Dict dict,RestRequest rest){
    try {
        dictService.save(dict);
        return success(dict);
    } catch (Exception e) {
        LOG.error("add error", e);
        return failure(e);
    }
}


@Logs(opdesc = "编辑字典库")
@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper edit(Dict dict,RestRequest rest){
    try {
        dictService.save(dict);
        return success(dict);
    } catch (Exception e) {
        LOG.error("edit error", e);
        return failure(e);
    }
}


@Logs(opdesc = "删除字典库")
@RequestMapping(value = { "/del" })
@ResponseBody
public BodyWrapper del(String[] ids,RestRequest rest){
    try {
        dictService.delete(ids);
        return success();
    } catch (Exception e) {
        LOG.error("del error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/list" })
@ResponseBody
public BodyWrapper list(Dict dict,RestRequest rest){
    try {
        Page<Dict> page = dictService.findEntity(rest);
        return success(page);
    } catch (Exception e) {
        LOG.error("query error", e);
        return failure(e);
    }
}


}