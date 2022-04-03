package com.example.steam.service;
 import com.example.steam.config.DynamicDataSource;
import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.dao.ShoppingCartDao;
import com.example.steam.entity;
import com.example.steam.mq.MQProducer;
import com.example.steam.vo.ShoppingCartDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.LinkedList;
import java.util.List;
import com.example.steam.Interface.GameService;
import com.example.steam.Interface.ImageService;
import com.example.steam.Interface.UserGameService;
@Service
public class ShoppingCartService {

@Autowired
 private ShoppingCartDao shoppingCartDao;

@Autowired
 private GameService gameService;

@Autowired
 private ImageService imageService;

@Autowired
 private SpikeGameService spikeGameService;

@Autowired
 private SpikeShopCartService spikeShopCartService;

@Autowired
 private ApplicationContext applicationContext;

@Autowired
 private UserGameService userGameService;

 private Logger log;


@Transactional(rollbackFor = Exception.class)
public Integer addOneCart(String email,long gameId,int gamePrice){
    ShoppingCart shoppingCart = new ShoppingCart();
    Game game = gameService.findOneGameById(gameId, DynamicDataSourceHolder.MASTER);
    shoppingCart.setGameId(gameId);
    shoppingCart.setGameName(game.getGameName());
    int finalPrice = game.getDiscount() == 0 ? game.getGamePrice() : (int) Math.ceil((double) game.getDiscount() / 100 * game.getGamePrice());
    finalPrice = finalPrice > gamePrice ? gamePrice : finalPrice;
    shoppingCart.setGamePrice(finalPrice);
    shoppingCart.setPosterImage(game.getPosterImage());
    shoppingCart.setEmail(email);
    return shoppingCartDao.addOneCart(shoppingCart);
}


public int deleteOneGameInCartById(long id){
    return shoppingCartDao.deleteOneGameById(id);
}


public List<ShoppingCartDetail> findAllCart(){
    List<ShoppingCart> shoppingCartList = shoppingCartDao.findAllCart();
    return shopCartToDetail(shoppingCartList);
}


public boolean isContainsShopCart(String email,long gameId){
    List<ShoppingCart> shoppingCartList = ((ShoppingCartService) applicationContext.getBean("shoppingCartService")).findShopCartByEmail(email);
    if (shoppingCartList != null) {
        log.error(shoppingCartList.size() + "");
    }
    for (ShoppingCart shoppingCart : shoppingCartList) {
        if (shoppingCart.getGameId() == gameId) {
            return true;
        }
    }
    return false;
}


public int deleteAllGameInCartByUserEmail(String email){
    return shoppingCartDao.deleteAllGameByUserEmail(email);
}


public List<ShoppingCartDetail> findCartByUserEmail(String email){
    List<ShoppingCart> cartList = shoppingCartDao.findCartByUserEmail(email);
    return shopCartToDetail(cartList);
}


public List<ShoppingCartDetail> shopCartToDetail(List<ShoppingCart> shoppingCartList){
    List<ShoppingCartDetail> shoppingCartDetailList = new LinkedList<>();
    for (int i = 0; i < shoppingCartList.size(); i++) {
        ShoppingCartDetail shoppingCartDetail = new ShoppingCartDetail();
        ShoppingCart shoppingCart = shoppingCartList.get(i);
        shoppingCartDetail.setId(shoppingCart.getId());
        shoppingCartDetail.setEmail(shoppingCart.getEmail());
        shoppingCartDetail.setGamePoster(imageService.findImageUrlById(shoppingCart.getPosterImage()));
        shoppingCartDetail.setGameId(shoppingCart.getGameId());
        shoppingCartDetail.setGameName(shoppingCart.getGameName());
        shoppingCartDetail.setGamePrice(shoppingCart.getGamePrice());
        shoppingCartDetailList.add(shoppingCartDetail);
    }
    return shoppingCartDetailList;
}


public List<ShoppingCart> findShopCartByEmail(String email){
    return shoppingCartDao.findCartByUserEmail(email);
}


}