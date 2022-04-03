package com.example.steam.mq.eventhandle;
 import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.entity.SpikeGame;
import com.example.steam.entity.SpikeShopCart;
import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.SpikeGameKey;
import com.example.steam.service.ShoppingCartService;
import com.example.steam.service.SpikeGameService;
import com.example.steam.service.SpikeShopCartService;
import com.example.steam.service.UserGameService;
import com.example.steam.vo.SpikeGameDetail;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
import com.example.steam.DTO.Event;
@Component
public class SpikeGameHandle implements EventHandle{


@Override
public List<EventType> bindEventType(){
    return Arrays.asList(EventType.SPIKE_GAME);
}


@Override
public void eventHandle(Event event,ApplicationContext applicationContext){
    String value = (String) event.getEtrMsg().get(Event.SPIKE);
    SpikeShopCart spikeShopCart = RedisService.stringToBean(value, SpikeShopCart.class);
    SpikeGame spikeGame = ((SpikeGameService) applicationContext.getBean("spikeGameService")).findOneGameBySpikeId(spikeShopCart.getSpikeGameId(), DynamicDataSourceHolder.SLAVE);
    boolean isContains = ((UserGameService) applicationContext.getBean("userGameService")).isContains(spikeShopCart.getEmail(), spikeGame.getGameId());
    if (!isContains) {
        ((SpikeGameService) applicationContext.getBean("spikeGameService")).updateSpikeGameStockCount(spikeShopCart.getSpikeGameId());
        ((SpikeShopCartService) applicationContext.getBean("spikeShopCartService")).addSpikeShopCart(spikeShopCart);
        ((ShoppingCartService) applicationContext.getBean("shoppingCartService")).addOneCart(spikeShopCart.getEmail(), spikeGame.getGameId(), spikeGame.getSpikePrice());
    }
}


}