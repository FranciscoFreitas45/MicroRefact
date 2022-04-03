package com.example.steam.controller;
 import com.alibaba.fastjson.JSON;
import com.example.steam.entity.SpikeShopCart;
import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.mq.MQProducer;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.SpikeGameKey;
import com.example.steam.service.SpikeGameService;
import com.example.steam.service.SpikeShopCartService;
import com.example.steam.utils.ResultMsg;
import com.example.steam.vo.LoginUser;
import com.example.steam.vo.SpikeGameDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation;
import java.util.Date;
import com.example.steam.Interface.RedisService;
@Controller
public class SpikeGameController implements InitializingBean{

 private Logger log;

@Autowired
 private MQProducer mqProducer;

@Autowired
 private RedisService redisService;

@Autowired
 private SpikeGameService spikeGameService;

@Autowired
 private SpikeShopCartService spikeShopCartService;


@ResponseBody
@RequestMapping("/spike/{spikeId}")
public String spikePath(long spikeId,LoginUser loginUser){
    return JSON.toJSONString(spikeGameService.handleRandomPathAndLimitSpike(loginUser));
}


@Override
public void afterPropertiesSet(){
    redisService.set(SpikeGameKey.SPIKE_STOCK, SpikeGameKey.SPIKE_STOCK_KEY + 1, 10);
}


@ResponseBody
@RequestMapping("/spike/add")
public String addSpikeGame(long gameId,int spikePrice,long startTime,long endTime,int stockCount){
    return JSON.toJSONString(ResultMsg.SUCCESS(spikeGameService.handleAddSpikeGame(gameId, spikePrice, stockCount, new Date(startTime), new Date(endTime))));
}


@ResponseBody
@RequestMapping("/spikegame")
public String findOneSpikeGame(){
    return JSON.toJSONString(ResultMsg.SUCCESS(spikeGameService.findOneSpikeGameDetail(1L)));
}


@ResponseBody
@RequestMapping("/spike/delete/{spikeId}")
public String deleteSpikeGame(long spikeId){
    return JSON.toJSONString(ResultMsg.SUCCESS(spikeGameService.deleteSpikeGame(spikeId)));
}


@ResponseBody
@RequestMapping("/spike/update")
public String updateSpikeGame(long spikeId,int stockCout,int spikePrice,long startTime,long endTime){
    return JSON.toJSONString(ResultMsg.SUCCESS(spikeGameService.updateSpikeGameStockCoutAndStartTimeAndEndTimeAndSpikePrice(spikeId, stockCout, new Date(startTime), new Date(endTime), spikePrice)));
}


@ResponseBody
@RequestMapping("/spike/result")
public String pollingSpikeStatu(LoginUser loginUser,long spikeId,long userId){
    return JSON.toJSONString(spikeGameService.handlePollSpike(loginUser, userId, spikeId));
}


@ResponseBody
@RequestMapping("/dospike/{path}/{spikeId}")
public String spike(long spikeId,String path,LoginUser loginUser){
    log.error("path:" + path + "spikeid:" + spikeId);
    return JSON.toJSONString(spikeGameService.handleSpike(spikeId, loginUser, path));
}


}