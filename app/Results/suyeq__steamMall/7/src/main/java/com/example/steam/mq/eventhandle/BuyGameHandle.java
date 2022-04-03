package com.example.steam.mq.eventhandle;
 import com.example.steam.entity.ShoppingCart;
import com.example.steam.entity.UserGame;
import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.service;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
import com.example.steam.DTO.ShoppingCart;
@Component
public class BuyGameHandle implements EventHandle{


@Override
public List<EventType> bindEventType(){
    return Arrays.asList(EventType.BUY_GAME);
}


@Override
public void eventHandle(Event event,ApplicationContext applicationContext){
    long userId = Long.parseLong((String) event.getEtrMsg().get(Event.USER_ID));
    String email = (String) event.getEtrMsg().get(Event.EMAIL);
    List<ShoppingCart> shoppingCartList = ((ShoppingCartService) applicationContext.getBean("shoppingCartService")).findShopCartByEmail(email);
    ((SpikeShopCartService) applicationContext.getBean("spikeShopCartService")).deleteSpikeShopCartByUserEmail(email);
    ((ShoppingCartService) applicationContext.getBean("shoppingCartService")).deleteAllGameInCartByUserEmail(email);
    for (ShoppingCart shoppingCart : shoppingCartList) {
        UserGame userGame = new UserGame(0L, email, shoppingCart.getGameId());
        ((UserGameService) applicationContext.getBean("userGameService")).addGameToUser(userGame);
        ((GameService) applicationContext.getBean("gameService")).updateGameSellNum(shoppingCart.getGameId());
        ((UserService) applicationContext.getBean("userService")).updateBuyGames(email);
    }
/**
 * 支付步骤
 */
}


}