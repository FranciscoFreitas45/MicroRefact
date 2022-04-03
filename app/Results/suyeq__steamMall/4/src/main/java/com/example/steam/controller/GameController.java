package com.example.steam.controller;
 import com.alibaba.fastjson.JSON;
import com.example.steam.service.GameService;
import com.example.steam.service.RecentGameService;
import com.example.steam.service.UserGameService;
import com.example.steam.utils.ResultMsg;
import com.example.steam.vo.GameDetail;
import com.example.steam.vo.SpecialGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.example.steam.Interface.UserGameService;
import com.example.steam.Interface.UserGameService;
import com.example.steam.Interface.RecentGameService;
@Controller
public class GameController {

 private Logger log;

@Autowired
 private GameService gameService;

@Autowired
 private UserGameService userGameService;

@Autowired
 private RecentGameService recentGameService;


@ResponseBody
@RequestMapping("/searchresult")
public String searchResult(String content){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesBySearchContent(content)));
}


@ResponseBody
@RequestMapping("/iscontains")
public String isContainsGame(String email,long gameId){
    return JSON.toJSONString(ResultMsg.SUCCESS(userGameService.isContains(email, gameId)));
}


@ResponseBody
@RequestMapping("/hotSell_index/{page}")
public String hotSell(long page){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findHotSell(page)));
}


@ResponseBody
@RequestMapping("upComing/classGame/{typeName}")
public String findGamesUpComingSumByType(String typeName){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesUpComingSumByType(typeName)));
}


@ResponseBody
@RequestMapping("/classGame/hotSell/{typeName}/{page}")
public String findGamesHotSellByType(String typeName,long page){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesHotSellByType(typeName, page)));
}


@ResponseBody
@RequestMapping("issued/classGame/{typeName}")
public String findGamesIssuedSumByType(String typeName){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesSumByType(typeName)));
}


@ResponseBody
@RequestMapping("/classGame/newRelease/{typeName}/{page}")
public String findGamesNewReleaseByType(String typeName,long page){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesNewReleaseByType(typeName, page)));
}


@ResponseBody
@RequestMapping("/upcoming/sum")
public String findUpComingGameSum(){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findUpComingGamesSum()));
}


@ResponseBody
@RequestMapping("/recentplaygame/{email}")
public String showRecentGame(String email){
    return JSON.toJSONString(ResultMsg.SUCCESS(userGameService.findThreeRecentGameVoListByEmail(email)));
}


@ResponseBody
@RequestMapping("/game/user/all/{email}")
public String showAllGamesByUserEmail(String email){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findAllGameShowByEmail(email)));
}


@ResponseBody
@RequestMapping("/newRelease_index/{page}")
public String newRelease(long page){
    long start = System.currentTimeMillis();
    List<GameDetail> list = gameService.findNewRelease(page);
    long end = System.currentTimeMillis();
    long result = end - start;
    log.error(result + "");
    return JSON.toJSONString(ResultMsg.SUCCESS(list));
}


@ResponseBody
@RequestMapping("/game/issued/{gameId}")
public String publishGame(long gameId){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.updateGameIssuedStatu(gameId)));
}


@ResponseBody
@RequestMapping("/classGame/{typeName}")
public String findGamesToClassCarouselBy(String typeName){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesToClassCarousel(typeName)));
}


@ResponseBody
@RequestMapping("/game/update")
public String addGame(long gameId,String newGameName,String newGameIntroduction,String newGameAbout,int newGamePrice,int newGameDiscount){
    long result = gameService.updateGame(gameId, newGameName, newGameIntroduction, newGameAbout, null, newGamePrice, newGameDiscount);
    return JSON.toJSONString(ResultMsg.SUCCESS(result));
}


@ResponseBody
@RequestMapping("/app/{id}")
public String oneGameDetail(long id){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGameById(id)));
}


@ResponseBody
@RequestMapping("/game/count/{email}")
public String gameCountByEmail(String email){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findContainGamesNum(email)));
}


@ResponseBody
@RequestMapping("/classGame/upComing/{typeName}/{page}")
public String findGamesUpComingByType(String typeName,long page){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesUpComingByType(typeName, page)));
}


@ResponseBody
@RequestMapping("/game/delete/{gameId}")
public String deleteGame(long gameId){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.deleteGame(gameId)));
}


@ResponseBody
@RequestMapping("/upComing_index/{page}")
public String upComing(long page){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findUpComing(page)));
}


@ResponseBody
@RequestMapping("/issued/sum")
public String findIssuedGameSum(){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findIssuedGamesSum()));
}


@ResponseBody
@RequestMapping("/specialCarousel")
public String specialCarousel(){
    List<SpecialGame> list = gameService.findSpecialGames();
    return JSON.toJSONString(ResultMsg.SUCCESS(list));
}


@ResponseBody
@RequestMapping("/feturedCarousel")
public String feturedCarousel(){
    return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesFetured()));
}


}