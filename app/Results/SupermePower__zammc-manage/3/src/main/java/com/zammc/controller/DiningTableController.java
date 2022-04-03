package com.zammc.controller;
 import com.zammc.domain.table.DiningTableEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.dining.DiningTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/diningTable")
@Slf4j
public class DiningTableController {

@Autowired
 private  DiningTableService diningTableService;


@RequestMapping(value = "/toAdd")
public ModelAndView toAdd(){
    return new ModelAndView("dining/dining-table-add");
}


@RequestMapping(value = "/deleteDiningTable")
@ResponseBody
public Message deleteDiningTable(DiningTableEntity diningTableEntity){
    Message message = null;
    try {
        diningTableService.deleteDiningTable(diningTableEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "删除成功");
    } catch (Exception e) {
        log.error("DiningTableController deleteDiningTable tableId -> {} Exception \n", diningTableEntity.getTableId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "删除失败");
    }
    return message;
}


@RequestMapping(value = "addDiningTable")
@ResponseBody
public Message addDiningTable(DiningTableEntity diningTableEntity){
    Message message = null;
    try {
        message = diningTableService.addDiningTable(diningTableEntity);
    } catch (Exception e) {
        log.error("DiningTableController addDiningTable diningTable -> {} Exception \n", diningTableEntity.toString(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "新增失败");
    }
    return message;
}


@RequestMapping(value = "/toEdit")
public ModelAndView toEdit(DiningTableEntity diningTableEntity){
    ModelAndView modelAndView = new ModelAndView("dining/dining-table-edit");
    try {
        DiningTableEntity diningTable = diningTableService.queryDiningTableById(diningTableEntity);
        modelAndView.addObject("diningTable", diningTable);
    } catch (Exception e) {
        log.error("DiningTableController toEdit tableId -> {} Exception \n", diningTableEntity.getTableId(), e);
    }
    return modelAndView;
}


@RequestMapping(value = "/queryDiningTablePage")
public ModelAndView queryDiningTablePage(DiningTableEntity diningTableEntity,PageBean pageBean){
    ModelAndView modelAndView = new ModelAndView("dining/dining-table-list");
    try {
        diningTableService.queryDiningTablePage(diningTableEntity, pageBean);
        modelAndView.addObject("page", pageBean).addObject("diningTable", diningTableEntity);
    } catch (Exception e) {
        log.error("DiningTableController queryDiningTablePage Exception \n", e);
    }
    return modelAndView;
}


@RequestMapping(value = "/editDiningTable")
@ResponseBody
public Message editDiningTable(DiningTableEntity diningTableEntity){
    Message message = null;
    try {
        message = diningTableService.editDiningTable(diningTableEntity);
    } catch (Exception e) {
        log.error("DiningTableController editDiningTable diningTable -> {} Exception \n", diningTableEntity.toString(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "编辑失败");
    }
    return message;
}


}