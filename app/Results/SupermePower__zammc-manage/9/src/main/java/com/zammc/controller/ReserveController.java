package com.zammc.controller;
 import com.zammc.domain.reserve.ReserveEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.reserve.ReserveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/reserve")
@Slf4j
public class ReserveController {

@Autowired
 private  ReserveService reserveService;


@RequestMapping(value = "/queryReservePage")
public ModelAndView queryReservePage(ReserveEntity reserveEntity,PageBean pageBean){
    ModelAndView modelAndView = new ModelAndView("reserve/reserve-list");
    try {
        reserveService.queryReservePage(reserveEntity, pageBean);
        modelAndView.addObject("reserve", reserveEntity).addObject("page", pageBean);
    } catch (Exception e) {
        log.error("ReserveController queryReservePage Exception \n", e);
    }
    return modelAndView;
}


@RequestMapping(value = "/deleteReserve")
@ResponseBody
public Message deleteReserve(ReserveEntity reserveEntity){
    Message message = null;
    try {
        reserveService.deleteReserve(reserveEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "删除成功");
    } catch (Exception e) {
        log.error("ReserveController deleteReserve reserveId -> {} Exception \n", reserveEntity.getReserveId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "删除失败");
    }
    return message;
}


}