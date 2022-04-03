package com.zammc.controller;
 import com.zammc.domain.restaurant.RestaurantPropertyEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.restaurant.RestaurantDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/restaurantDetail")
@Slf4j
public class RestaurantDetailController {

@Autowired
 private  RestaurantDetailService restaurantDetailService;


@RequestMapping(value = "/toAdd")
public ModelAndView toAdd(){
    return new ModelAndView("restaurant/restaurant-detail-add");
}


@RequestMapping(path = "/deleteRestaurantDetail")
public Message deleteRestaurantDetail(RestaurantPropertyEntity restaurantPropertyEntity){
    Message message = null;
    try {
        restaurantDetailService.deleteRestaurantDetail(restaurantPropertyEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "删除成功");
    } catch (Exception e) {
        log.error("RestaurantDetailController deleteRestaurantDetail restaurantDetailId -> {} Exception \n", restaurantPropertyEntity.getId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "删除失败");
    }
    return message;
}


@RequestMapping(path = "/updateRestaurantDetail")
public Message updateRestaurantDetail(RestaurantPropertyEntity restaurantPropertyEntity,HttpServletRequest request){
    Message message = null;
    return message;
}


@RequestMapping(value = "/addRestaurantDetail")
public Message addRestaurantDetail(RestaurantPropertyEntity restaurantPropertyEntity,HttpServletRequest request){
    Message message = null;
    try {
        message = restaurantDetailService.addRestaurantDetail(restaurantPropertyEntity, request);
    } catch (Exception e) {
        log.error("RestaurantDetailController addRestaurantDetail restaurantPropertyEntity -> {} Exception \n", restaurantPropertyEntity.toString(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "新增失败");
    }
    return message;
}


@RequestMapping(value = "/toEdit")
public ModelAndView toEdit(RestaurantPropertyEntity restaurantPropertyEntity){
    ModelAndView modelAndView = new ModelAndView("restaurant/restaurant-detail-edit");
    try {
        RestaurantPropertyEntity restaurantProperty = restaurantDetailService.queryRestaurantPropertyById(restaurantPropertyEntity);
        modelAndView.addObject("restaurantProperty", restaurantProperty);
    } catch (Exception e) {
        log.error("RestaurantDetailController toEdit restaurantPropertyId -> {} Exception \n", restaurantPropertyEntity.getId(), e);
    }
    return modelAndView;
}


@RequestMapping(value = "/queryRestaurantDetailPage")
public ModelAndView queryRestaurantDetailPage(RestaurantPropertyEntity restaurantPropertyEntity,PageBean pageBean){
    ModelAndView modelAndView = new ModelAndView("/restaurant/restaurant-detail-list");
    try {
        restaurantDetailService.queryRestaurantDetailPage(restaurantPropertyEntity, pageBean);
        modelAndView.addObject("restaurantProperty", restaurantPropertyEntity).addObject("page", pageBean);
    } catch (Exception e) {
        log.error("RestaurantDetailController queryRestaurantDetailPage Exception \n", e);
    }
    return modelAndView;
}


}