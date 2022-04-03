package com.zammc.controller;
 import com.zammc.domain.restaurant.RestaurantEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.restaurant.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/restaurant")
@Slf4j
public class RestaurantController {

@Autowired
 private  RestaurantService restaurantService;


@RequestMapping(value = "/queryRestaurantPage")
public ModelAndView queryRestaurantPage(PageBean pageBean){
    ModelAndView modelAndView = new ModelAndView("restaurant/restaurant-list");
    try {
        restaurantService.queryRestaurantPage(pageBean);
        modelAndView.addObject("page", pageBean);
    } catch (Exception e) {
        log.error("RestaurantController queryRestaurantPage Exception \n", e);
    }
    return modelAndView;
}


@RequestMapping(value = "/editRestaurantRest")
@ResponseBody
public Message editRestaurantRest(RestaurantEntity restaurantEntity){
    Message message = null;
    try {
        restaurantService.editRestaurantRest(restaurantEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "操作成功");
    } catch (Exception e) {
        log.error("RestaurantController editRestaurantRest restaurantId -> {} Exception \n", restaurantEntity.getRestaurantId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "操作失败");
    }
    return message;
}


@RequestMapping(value = "/toEdit")
public ModelAndView toEdit(RestaurantEntity restaurantEntity){
    ModelAndView modelAndView = new ModelAndView("restaurant/restaurant-edit");
    try {
        RestaurantEntity restaurant = restaurantService.queryRestaurantById(restaurantEntity);
        modelAndView.addObject("restaurant", restaurant);
    } catch (Exception e) {
        log.error("RestaurantController toEdit restaurantId -> {} Exception \n", restaurantEntity.getRestaurantId(), e);
    }
    return modelAndView;
}


@RequestMapping(value = "/editRestaurant")
@ResponseBody
public Message editRestaurant(RestaurantEntity restaurantEntity,HttpServletRequest request){
    Message message = null;
    try {
        message = restaurantService.editRestaurant(restaurantEntity, request);
    } catch (Exception e) {
        log.error("RestaurantController editRestaurant restaurant -> {} Exception \n", restaurantEntity.toString(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "编辑失败");
    }
    return message;
}


@RequestMapping(value = "/editRestaurantBusiness")
@ResponseBody
public Message editRestaurantBusiness(RestaurantEntity restaurantEntity){
    Message message = null;
    try {
        restaurantService.editRestaurantBusiness(restaurantEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "操作成功");
    } catch (Exception e) {
        log.error("RestaurantController editRestaurantBusiness restaurantId -> {} Exception \n", restaurantEntity.getRestaurantId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "操作失败");
    }
    return message;
}


}