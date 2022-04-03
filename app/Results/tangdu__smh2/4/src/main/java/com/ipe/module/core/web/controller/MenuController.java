package com.ipe.module.core.web.controller;
 import com.ipe.module.core.entity.Menu;
import com.ipe.module.core.service.MenuService;
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
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
@Controller
@RequestMapping("/menu")
public class MenuController extends AbstractController{

 private  Logger LOG;

@Autowired
 private  MenuService menuService;


@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper add(Menu menu,RestRequest rest){
    try {
        menuService.saveMenu(menu);
        return success(menu);
    } catch (Exception e) {
        LOG.error("Exception {}", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/getMenus" })
@ResponseBody
public BodyWrapper getMenus(String pid){
    try {
        List<Menu> data = menuService.getMenus(pid);
        return success(data);
    } catch (Exception e) {
        LOG.error("Exception {}", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper edit(Menu menu,RestRequest rest){
    try {
        menu.setParent(menuService.get(menu.getParent().getId()));
        menu.setUpdatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        menuService.save(menu);
        return success(menu);
    } catch (Exception e) {
        LOG.error("Exception {}", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/getRoleMenus" })
@ResponseBody
public BodyWrapper getRoleMenus(){
    try {
        SystemRealm.UserInfo userInfo = (SystemRealm.UserInfo) SecurityUtils.getSubject().getPrincipal();
        String result = menuService.getUserMenu(userInfo.getUserId());
        return success(result);
    } catch (Exception e) {
        LOG.error("Exception {}", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/update" })
@ResponseBody
public BodyWrapper update(String[] ids,String pid){
    try {
        menuService.updateMenus(ids, pid);
        return success();
    } catch (Exception e) {
        LOG.error("Exception {}", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/del" })
@ResponseBody
public BodyWrapper del(String[] ids,RestRequest rest){
    try {
        menuService.delete(ids);
        return success();
    } catch (Exception e) {
        LOG.error("Exception {}", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/list" })
@ResponseBody
public BodyWrapper list(Menu menu,RestRequest rest){
    try {
        int startRow = rest.getStart();
        int endRow = rest.getLimit();
        Page<Menu> page = menuService.findAll(null, new PageRequest(startRow, endRow));
        return success(page);
    } catch (Exception e) {
        LOG.error("Exception {}", e);
        return failure(e);
    }
}


}