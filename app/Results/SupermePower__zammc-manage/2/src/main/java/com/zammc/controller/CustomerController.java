package com.zammc.controller;
 import com.zammc.domain.user.UserInfoEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.customer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

@Autowired
 private  CustomerService customerService;


@RequestMapping(value = "/deleteCustomer")
@ResponseBody
public Message deleteCustomer(UserInfoEntity userInfo){
    Message message = null;
    try {
        customerService.deleteCustomer(userInfo);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "删除成功");
    } catch (Exception e) {
        log.error("CustomerController deleteCustomer userId -> {} Exception \n", userInfo.getUserId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "删除失败");
    }
    return message;
}


@RequestMapping(value = "/queryCustomerPage")
public ModelAndView queryCustomerPage(UserInfoEntity userInfo,PageBean pageBean){
    ModelAndView modelAndView = new ModelAndView("customer/customer-list");
    try {
        customerService.queryCustomerPage(userInfo, pageBean);
        modelAndView.addObject("customer", userInfo).addObject("page", pageBean);
    } catch (Exception e) {
        log.error("CustomerController queryCustomerPage Exception \n", e);
    }
    return modelAndView;
}


}