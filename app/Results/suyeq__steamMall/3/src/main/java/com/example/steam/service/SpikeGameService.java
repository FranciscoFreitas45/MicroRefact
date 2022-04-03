package com.example.steam.service;
 import com.alibaba.fastjson.JSON;
import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.dao.SpikeGameDao;
import com.example.steam.entity.Game;
import com.example.steam.entity.SpikeGame;
import com.example.steam.entity.SpikeShopCart;
import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.mq.MQProducer;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.GameKey;
import com.example.steam.redis.key.SpikeGameKey;
import com.example.steam.redis.key.UserKey;
import com.example.steam.utils.ResultMsg;
import com.example.steam.utils.UUIDUtil;
import com.example.steam.vo.GameDetail;
import com.example.steam.vo.LoginUser;
import com.example.steam.vo.SpikeGameDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import com.example.steam.Interface.GameService;
import com.example.steam.Interface.ImageService;
import com.example.steam.Interface.RedisService;
import com.example.steam.DTO.LoginUser;
import com.example.steam.DTO.ResultMsg;
import com.example.steam.DTO.RedisService;
import com.example.steam.DTO.Game;
@Service
public class SpikeGameService {

 private  int MAX_SPIKETIMES_EVERY_MINUTE;

 private Logger log;

@Autowired
 private SpikeGameDao spikeGameDao;

@Autowired
 private GameService gameService;

@Autowired
 private ImageService imageService;

@Autowired
 private RedisService redisService;

@Autowired
 private MQProducer mqProducer;

@Autowired
 private SpikeGameService spikeGameService;

@Autowired
 private SpikeShopCartService spikeShopCartService;

@Autowired
 private ApplicationContext applicationContext;


public ResultMsg handleRandomPathAndLimitSpike(LoginUser loginUser){
    if (loginUser == null) {
        return ResultMsg.NO_LOGIN;
    }
    Integer spikeTimes = redisService.get(SpikeGameKey.SPIKE_TIMES, SpikeGameKey.SPIKE_TIMES_KEY + loginUser.getEmail(), Integer.class);
    spikeTimes = spikeTimes == null ? new Integer(0) : spikeTimes;
    if (spikeTimes > MAX_SPIKETIMES_EVERY_MINUTE) {
        return ResultMsg.SPIKE_LIMIT_ERROR;
    }
    String uuId = UUIDUtil.randomUUID();
    spikeTimes++;
    redisService.set(SpikeGameKey.SPIKE_TIMES, SpikeGameKey.SPIKE_TIMES_KEY + loginUser.getEmail(), spikeTimes);
    redisService.set(SpikeGameKey.RANDM_PATH, SpikeGameKey.RANDM_PATH_KEY + loginUser.getEmail(), uuId);
    return ResultMsg.SUCCESS(uuId);
}


public SpikeGame findOneGameByGameId(long gameId){
    return spikeGameDao.findOneByGameId(gameId);
}


public ResultMsg handleSpike(long spikeId,LoginUser loginUser,String path){
    if (loginUser == null) {
        return ResultMsg.NO_LOGIN;
    }
    String spikePath = redisService.get(SpikeGameKey.RANDM_PATH, SpikeGameKey.RANDM_PATH_KEY + loginUser.getEmail(), String.class);
    if (!spikePath.equals(path)) {
        return ResultMsg.SPIKE_PATH_ERROR;
    }
    long stock = redisService.decKey(SpikeGameKey.SPIKE_STOCK, SpikeGameKey.SPIKE_STOCK_KEY + spikeId);
    if (stock < 0) {
        return ResultMsg.STOCK_IS_NULL;
    }
    List<Long> containsGames = redisService.getList(UserKey.CONTAINS_GAMES, UserKey.CONTAINS_KEY + loginUser.getEmail(), Long.class);
    SpikeGameDetail spikeGameDetail = spikeGameService.findOneSpikeGameDetail(spikeId);
    if (containsGames.contains(spikeGameDetail.getGameId())) {
        return ResultMsg.GAME_HAD;
    }
    SpikeShopCart spikeShopCart = spikeShopCartService.findSpikeShopCart(loginUser.getEmail(), spikeId);
    if (spikeShopCart != null) {
        return ResultMsg.SPIKE_REPEAT;
    }
    spikeShopCart = new SpikeShopCart(loginUser.getEmail(), spikeId);
    mqProducer.productEvent(new Event(EventType.SPIKE_GAME).setEtrMsg(Event.SPIKE, RedisService.beanToString(spikeShopCart)));
    return ResultMsg.SUCCESS(spikeShopCart);
}


@Transactional(rollbackFor = Exception.class)
public long addSpikeGame(SpikeGame spikeGame){
    spikeGameDao.addSpikeGame(spikeGame);
    findOneSpikeGameDetail(spikeGame.getId());
    redisService.set(SpikeGameKey.SPIKE_STOCK, SpikeGameKey.SPIKE_STOCK_KEY + spikeGame.getId(), spikeGame.getStockCount());
    return spikeGame.getId();
}


public SpikeGame findOneGameBySpikeId(long spikeId,String dataSource){
    DynamicDataSourceHolder.putDataSource(dataSource);
    return spikeGameDao.findOneById(spikeId);
}


public List<SpikeGameDetail> findAllSpikeGameDetail(){
    List<SpikeGame> spikeGameList = spikeGameDao.findAllSpikeGame();
    List<SpikeGameDetail> spikeGameDetailList = new LinkedList<>();
    for (SpikeGame spikeGame : spikeGameList) {
        SpikeGameDetail spikeGameDetail = spikeGameToSpikeGameDetail(spikeGame);
        spikeGameDetailList.add(spikeGameDetail);
    }
    return spikeGameDetailList;
}


public long handleAddSpikeGame(long gameId,int spikePrice,int stockCount,Date startTime,Date endTime){
    Game game = gameService.findOneGameById(gameId, DynamicDataSourceHolder.MASTER);
    SpikeGame spikeGame = new SpikeGame();
    spikeGame.setGameId(gameId);
    spikeGame.setSpikePrice(spikePrice);
    spikeGame.setStartTime(startTime);
    spikeGame.setEndTime(endTime);
    spikeGame.setStockCount(stockCount);
    spikeGame.setGamePrice(game.getGamePrice());
    spikeGame.setPosterGame(game.getPosterImage());
    return ((SpikeGameService) applicationContext.getBean("spikeGameService")).addSpikeGame(spikeGame);
}


@Transactional(rollbackFor = Exception.class)
public int updateSpikeGameStockCount(long spikeId){
    SpikeGame spikeGame = ((SpikeGameService) applicationContext.getBean("spikeGameService")).findOneGameBySpikeId(spikeId, DynamicDataSourceHolder.MASTER);
    spikeGame.setStockCount(spikeGame.getStockCount() - 1);
    return ((SpikeGameService) applicationContext.getBean("spikeGameService")).updateOneSpikeGame(spikeGame);
}


public ResultMsg handlePollSpike(LoginUser loginUser,long userId,long spikeId){
    if (loginUser == null) {
        return ResultMsg.NO_LOGIN;
    }
    SpikeShopCart spikeShopCart = spikeShopCartService.findSpikeShopCart(loginUser.getEmail(), spikeId);
    if (spikeShopCart == null) {
        return ResultMsg.SPIKE_ING;
    }
    return ResultMsg.SPIKE_SUCCESS;
}


public int deleteSpikeGame(long spikeId){
    redisService.del(SpikeGameKey.SPIKE_ID, spikeId + "");
    redisService.del(SpikeGameKey.SPIKE_STOCK, SpikeGameKey.SPIKE_STOCK_KEY + spikeId);
    return spikeGameDao.deleteSpikeGame(spikeId);
}


public SpikeGameDetail spikeGameToSpikeGameDetail(SpikeGame spikeGame){
    SpikeGameDetail spikeGameDetail = new SpikeGameDetail();
    spikeGameDetail.setId(spikeGame.getId());
    spikeGameDetail.setGameId(spikeGame.getGameId());
    spikeGameDetail.setGamePrice(spikeGame.getGamePrice());
    spikeGameDetail.setSpikePrice(spikeGame.getSpikePrice());
    spikeGameDetail.setPosterImage(imageService.findImageUrlById(spikeGame.getPosterGame()));
    spikeGameDetail.setStartTime(spikeGame.getStartTime());
    spikeGameDetail.setEndTime(spikeGame.getEndTime());
    spikeGameDetail.setStockCount(spikeGame.getStockCount());
    return spikeGameDetail;
}


public int updateSpikeGameStockCoutAndStartTimeAndEndTimeAndSpikePrice(long spikeId,int stockCout,Date startTime,Date endTime,int spikePrice){
    SpikeGame spikeGame = spikeGameDao.findOneById(spikeId);
    SpikeGameDetail spikeGameDetail = ((SpikeGameService) applicationContext.getBean("spikeGameService")).findOneSpikeGameDetail(spikeId);
    spikeGameDetail.setStockCount(stockCout);
    spikeGameDetail.setStartTime(startTime);
    spikeGameDetail.setEndTime(endTime);
    spikeGameDetail.setSpikePrice(spikePrice);
    redisService.set(SpikeGameKey.SPIKE_ID, spikeId + "", spikeGameDetail);
    redisService.set(SpikeGameKey.SPIKE_STOCK, SpikeGameKey.SPIKE_STOCK_KEY + spikeId, stockCout);
    spikeGame.setStockCount(stockCout);
    spikeGame.setStartTime(startTime);
    spikeGame.setEndTime(endTime);
    spikeGame.setSpikePrice(spikePrice);
    return ((SpikeGameService) applicationContext.getBean("spikeGameService")).updateOneSpikeGame(spikeGame);
}


public SpikeGameDetail findOneSpikeGameDetail(long spikeId){
    SpikeGameDetail spikeGameDetail = redisService.get(SpikeGameKey.SPIKE_ID, spikeId + "", SpikeGameDetail.class);
    if (spikeGameDetail != null) {
        return spikeGameDetail;
    }
    SpikeGame spikeGame = spikeGameDao.findOneById(spikeId);
    spikeGameDetail = spikeGameToSpikeGameDetail(spikeGame);
    redisService.set(SpikeGameKey.SPIKE_ID, spikeId + "", spikeGameDetail);
    return spikeGameDetail;
}


public int updateOneSpikeGame(SpikeGame spikeGame){
    SpikeGameDetail spikeGameDetail = new SpikeGameDetail();
    spikeGameDetail.setId(spikeGame.getId());
    spikeGameDetail.setGameId(spikeGame.getGameId());
    spikeGameDetail.setGamePrice(spikeGame.getGamePrice());
    spikeGameDetail.setSpikePrice(spikeGame.getSpikePrice());
    spikeGameDetail.setPosterImage(imageService.findImageUrlById(spikeGame.getPosterGame(), DynamicDataSourceHolder.MASTER));
    spikeGameDetail.setStartTime(spikeGame.getStartTime());
    spikeGameDetail.setEndTime(spikeGame.getEndTime());
    spikeGameDetail.setStockCount(spikeGame.getStockCount());
    redisService.set(SpikeGameKey.SPIKE_ID, spikeGame.getId() + "", spikeGameDetail);
    return spikeGameDao.updateOneSpikeGame(spikeGame);
}


}