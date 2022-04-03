package com.example.steam.controller;
 import com.alibaba.fastjson.JSON;
import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.mq.MQProducer;
import com.example.steam.service.ShoppingCartService;
import com.example.steam.utils.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class ShoppingCartController {

 private Logger logger;

@Autowired
 private ShoppingCartService shoppingCartService;

@Autowired
 private MQProducer mqProducer;


@RequestMapping("/cart/removeall/{email}")
@ResponseBody
public String deleteAllGameIncartByUserId(String email){
    return JSON.toJSONString(ResultMsg.SUCCESS(shoppingCartService.deleteAllGameInCartByUserEmail(email)));
}


@RequestMapping("/cart/{email}")
@ResponseBody
public String findCartByUserId(String email){
    return JSON.toJSONString(ResultMsg.SUCCESS(shoppingCartService.findCartByUserEmail(email)));
}


@RequestMapping("/cart/add")
@ResponseBody
public String addCart(String email,long gameId){
    logger.info(email + " " + gameId);
    return JSON.toJSONString(ResultMsg.SUCCESS(shoppingCartService.addOneCart(email, gameId, Integer.MAX_VALUE)));
// return "success";
}


@RequestMapping("/cart/remove/{id}")
@ResponseBody
public String deleteGameInCartById(long id){
    return JSON.toJSONString(ResultMsg.SUCCESS(shoppingCartService.deleteOneGameInCartById(id)));
}


@ResponseBody
@RequestMapping("/buygame")
public String finalBuyGame(long userId,String email){
    mqProducer.productEvent(new Event(EventType.BUY_GAME).setEtrMsg(Event.EMAIL, email).setEtrMsg(Event.USER_ID, userId + ""));
    return JSON.toJSONString(ResultMsg.SUCCESS);
}


@ResponseBody
@RequestMapping("/cart/iscontain")
public String isContainsCart(String email,long gameId){
    return JSON.toJSONString(ResultMsg.SUCCESS(shoppingCartService.isContainsShopCart(email, gameId)));
}


}