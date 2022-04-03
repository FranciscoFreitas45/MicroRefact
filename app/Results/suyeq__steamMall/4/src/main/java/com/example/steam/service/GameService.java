package com.example.steam.service;
 import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.dao.GameDao;
import com.example.steam.entity;
import com.example.steam.localstore.LocalStoreKey;
import com.example.steam.localstore.LocalStoreService;
import com.example.steam.redis.RedisPrefixKey;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.CommentKey;
import com.example.steam.redis.key.GameKey;
import com.example.steam.redis.key.UserKey;
import com.example.steam.utils;
import com.example.steam.vo.GameDetail;
import com.example.steam.vo.SimpleGameShowVo;
import com.example.steam.vo.SpecialGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import java.util;
import com.example.steam.Interface.ImageService;
import com.example.steam.Interface.RedisService;
import com.example.steam.Interface.LabelService;
import com.example.steam.Interface.LocalStoreService;
import com.example.steam.Interface.CommentService;
import com.example.steam.Interface.UserGameService;
import com.example.steam.Interface.UserService;
import com.example.steam.Interface.SystemNeedService;
import com.example.steam.DTO.RankScoreValue;
@Service
public class GameService implements InitializingBean{

 private Logger log;

 private  int INDEX_CAROUSEL_SIZE;

 private  int CLASS_CAROUSEL_SIZE;

 private  int RANK_SIZE;

@Autowired
 private GameDao gameDao;

@Autowired
 private ImageService imageService;

@Autowired
 private RedisService redisService;

@Autowired
 private LabelService labelService;

@Autowired
 private TypeService typeService;

@Autowired
 private LocalStoreService localStoreService;

@Autowired
 private ApplicationContext applicationContext;

@Autowired
 private CommentService commentService;

@Autowired
 private UserGameService userGameService;

@Autowired
 private UserService userService;

@Autowired
 private SystemNeedService systemNeedService;


public List<SpecialGame> findGamesToClassCarousel(String typeName){
    int count = 0;
    Set<GameDetail> gameDetailSet = localStoreService.get(LocalStoreKey.CLASS_CAROUSEL_KEY(), Set.class, typeName);
    if (gameDetailSet != null) {
        return new LinkedList<>(gameDetailSet);
    }
    int sum = redisService.get(GameKey.GAME_SUM, GameKey.GAME_SUM_KEY, int.class);
    // Random random=new Random();
    gameDetailSet = new HashSet<>();
    while (count < sum && gameDetailSet.size() < CLASS_CAROUSEL_SIZE) {
        // int seed=random.nextInt(sum)+1;
        int seed = RandomUtil.randomSeed(sum);
        GameDetail gameDetail = redisService.get(GameKey.GAME_ID, seed + "", GameDetail.class);
        if (gameDetail != null && typeService.isExists(gameDetail.getType(), typeName)) {
            gameDetailSet.add(gameDetail);
        }
        count++;
    }
    localStoreService.set(LocalStoreKey.CLASS_CAROUSEL_KEY(), gameDetailSet, typeName);
    return new LinkedList<>(gameDetailSet);
}


public GameDetail findGameById(long id){
    GameDetail gameDetail = redisService.get(GameKey.GAME_ID, id + "", GameDetail.class);
    if (gameDetail != null) {
        return gameDetail;
    }
    Game game = gameDao.findGameById(id);
    if (game == null) {
        return null;
    }
    gameDetail = new GameDetail();
    gameDetail.setId(game.getId());
    gameDetail.setGameName(game.getGameName());
    gameDetail.setGamePrice(game.getGamePrice());
    gameDetail.setDiscount(game.getDiscount());
    gameDetail.setGameIntroduction(game.getGameIntroduction());
    gameDetail.setGameAbout(game.getGameAbout());
    gameDetail.setIssuedDate(game.getIssuedDate());
    gameDetail.setSellNum(game.getSellNum());
    gameDetail.setIssuedStatu(game.getIssuedStatu());
    gameDetail.setPosterImage(imageService.findImageUrlById(game.getPosterImage()));
    List<String> imageUrlList = imageService.findGameImageUrlsByGameId(game.getId());
    gameDetail.setImageIntro1(imageUrlList.get(0));
    gameDetail.setImageIntro2(imageUrlList.get(1));
    gameDetail.setImageIntro3(imageUrlList.get(2));
    gameDetail.setImageIntro4(imageUrlList.get(3));
    gameDetail.setImageIntro5(imageUrlList.get(4));
    gameDetail.setLabel(labelService.findLabelNamesByGameId(game.getId()));
    gameDetail.setLowestSystem(game.getLowestSystem());
    gameDetail.setRecommendSystem(game.getRecommendSystem());
    gameDetail.setType(typeService.findTypeNameByGameId(game.getId()));
    redisService.set(GameKey.GAME_ID, id + "", gameDetail);
    return gameDetail;
}


public List<GameDetail> findGamesHotSellByType(String typeName,long page){
    long count = 0;
    long cursor = 0;
    List<GameDetail> gameDetailList = localStoreService.get(LocalStoreKey.HOT_SELL_CLASS_KEY(), List.class, typeName + page);
    if (gameDetailList != null) {
        return gameDetailList;
    }
    gameDetailList = new LinkedList<>();
    long sum = redisService.zcount(GameKey.RANK_SELLNUM, GameKey.GAME_RANK_SELLNUM);
    while (cursor < sum && gameDetailList.size() < RANK_SIZE) {
        Set<GameRank> rankTimeGame = redisService.zrange(GameKey.RANK_SELLNUM, GameKey.GAME_RANK_SELLNUM, cursor, cursor + 100, GameRank.class);
        Iterator<GameRank> iterator = rankTimeGame.iterator();
        count = iteratorClass(page, count, iterator, typeName, gameDetailList);
        cursor += 100;
    }
    localStoreService.set(LocalStoreKey.HOT_SELL_CLASS_KEY(), gameDetailList, typeName + page);
    return gameDetailList;
}


public List<SpecialGame> findSpecialGames(){
    Set<GameDetail> gameDetailSet = localStoreService.get(LocalStoreKey.SPECIAL_CAROUSEL_KEY(), Set.class, "0");
    if (gameDetailSet != null) {
        return new LinkedList<>(gameDetailSet);
    }
    int sum = redisService.get(GameKey.GAME_SUM, GameKey.GAME_SUM_KEY, int.class);
    // Random random=new Random();
    gameDetailSet = new HashSet<>();
    while (gameDetailSet.size() < INDEX_CAROUSEL_SIZE) {
        // int seed=random.nextInt(sum)+1;
        int seed = RandomUtil.randomSeed(sum);
        GameDetail gameDetail = redisService.get(GameKey.GAME_ID, seed + "", GameDetail.class);
        if (gameDetail != null && gameDetail.getDiscount() > 0) {
            gameDetailSet.add(gameDetail);
        }
    }
    localStoreService.set(LocalStoreKey.SPECIAL_CAROUSEL_KEY(), gameDetailSet, "0");
    return new LinkedList<>(gameDetailSet);
}


public List<GameDetail> findGamesBySearchContent(String content){
    List<Game> gameList = gameDao.findGamesBySearchContent(content);
    List<GameDetail> gameDetailList = new LinkedList<>();
    for (Game game : gameList) {
        GameDetail gameDetail = new GameDetail();
        gameDetail.setId(game.getId());
        gameDetail.setGameName(game.getGameName());
        gameDetail.setGamePrice(game.getGamePrice());
        gameDetail.setDiscount(game.getDiscount());
        gameDetail.setGameIntroduction(game.getGameIntroduction());
        gameDetail.setGameAbout(game.getGameAbout());
        gameDetail.setIssuedDate(game.getIssuedDate());
        gameDetail.setSellNum(game.getSellNum());
        gameDetail.setIssuedStatu(game.getIssuedStatu());
        gameDetail.setPosterImage(imageService.findImageUrlById(game.getPosterImage()));
        List<String> imageUrlList = imageService.findGameImageUrlsByGameId(game.getId());
        gameDetail.setImageIntro1(imageUrlList.get(0));
        gameDetail.setImageIntro2(imageUrlList.get(1));
        gameDetail.setImageIntro3(imageUrlList.get(2));
        gameDetail.setImageIntro4(imageUrlList.get(3));
        gameDetail.setImageIntro5(imageUrlList.get(4));
        gameDetail.setLabel(labelService.findLabelNamesByGameId(game.getId()));
        gameDetail.setLowestSystem(game.getLowestSystem());
        gameDetail.setRecommendSystem(game.getRecommendSystem());
        gameDetail.setType(typeService.findTypeNameByGameId(game.getId()));
        gameDetailList.add(gameDetail);
    }
    return gameDetailList;
}


public long iteratorClass(long page,long count,Iterator<GameRank> iterator,String typeName,List<GameDetail> list){
    while (iterator.hasNext()) {
        GameRank gameRank = iterator.next();
        if (typeService.isExists(gameRank.getType(), typeName) && list.size() < RANK_SIZE) {
            if (count >= page * RANK_SIZE && count < page * RANK_SIZE + RANK_SIZE) {
                GameDetail gameDetail = ((GameService) applicationContext.getBean("gameService")).findGameById(gameRank.getId());
                list.add(gameDetail);
            }
            count++;
        }
    }
    return count;
}


@Transactional(rollbackFor = Exception.class)
public int updateGamePosterImage(long gameId,long posterImage){
    Game game = ((GameService) applicationContext.getBean("gameService")).findOneGameById(gameId, DynamicDataSourceHolder.MASTER);
    GameDetail gameDetail = redisService.get(GameKey.GAME_ID, gameId + "", GameDetail.class);
    String imageUrl = imageService.findImageUrlById(posterImage);
    gameDetail.setPosterImage(imageUrl);
    redisService.set(GameKey.GAME_ID, gameId + "", gameDetail);
    game.setPosterImage(posterImage);
    return ((GameService) applicationContext.getBean("gameService")).updateGame(game);
}


public int updateGame(Game game){
    return gameDao.updateGame(game);
}


public long iteratorTraversing(long cursor,Iterator<GameRank> iterator,long page,List<GameDetail> list){
    while (iterator.hasNext()) {
        GameRank gameRank = iterator.next();
        if (cursor >= page * RANK_SIZE && cursor < page * RANK_SIZE + RANK_SIZE) {
            GameDetail gameDetail = ((GameService) applicationContext.getBean("gameService")).findGameById(gameRank.getId());
            list.add(gameDetail);
        }
        cursor++;
    }
    return cursor;
}


public List<GameDetail> findGamesNewReleaseByType(String typeName,long page){
    // GamePriorityQueue<GameDetail> priorityQueue=new GamePriorityQueue<>(new TimeComparator());
    long count = 0;
    long cursor = 0;
    List<GameDetail> gameDetailList = localStoreService.get(LocalStoreKey.NEW_RELEASE_CLASS_KEY(), List.class, typeName + page);
    if (gameDetailList != null) {
        return gameDetailList;
    }
    gameDetailList = new LinkedList<>();
    long sum = redisService.zcount(GameKey.RANK_TIME, GameKey.GAME_RANK_TIME);
    while (cursor < sum && gameDetailList.size() < RANK_SIZE) {
        Set<GameRank> rankTimeGame = redisService.zrange(GameKey.RANK_TIME, GameKey.GAME_RANK_TIME, cursor, cursor + 100, GameRank.class);
        Iterator<GameRank> iterator = rankTimeGame.iterator();
        count = iteratorClass(page, count, iterator, typeName, gameDetailList);
        cursor += 100;
    }
    localStoreService.set(LocalStoreKey.NEW_RELEASE_CLASS_KEY(), gameDetailList, typeName + page);
    return gameDetailList;
}


public long findUpComingGamesSum(){
    return redisService.zcount(GameKey.RANK_UPCOMING, GameKey.GAME_RANK_UPCOMING);
}


public List<GameDetail> findUpComing(long page){
    long cursor = 0;
    List<GameDetail> gameDetailList = localStoreService.get(LocalStoreKey.UP_COMING_INDEX_KEY(), List.class, page + "");
    if (gameDetailList != null) {
        return gameDetailList;
    }
    gameDetailList = new LinkedList<>();
    long sum = redisService.zcount(GameKey.RANK_UPCOMING, GameKey.GAME_RANK_UPCOMING);
    while (cursor < sum && gameDetailList.size() < RANK_SIZE) {
        Set<GameRank> rankUpGame = redisService.zrange(GameKey.RANK_UPCOMING, GameKey.GAME_RANK_UPCOMING, cursor, cursor + 100, GameRank.class);
        Iterator<GameRank> iterator = rankUpGame.iterator();
        cursor = iteratorTraversing(cursor, iterator, page, gameDetailList);
    }
    localStoreService.set(LocalStoreKey.UP_COMING_INDEX_KEY(), gameDetailList, page + "");
    return gameDetailList;
}


public List<Game> findAllGame(){
    return gameDao.findAllGame();
}


public List<GameDetail> findHotSell(long page){
    long cursor = 0;
    List<GameDetail> gameDetailList = localStoreService.get(LocalStoreKey.HOT_SELL_INDEX_KEY(), List.class, page + "");
    if (gameDetailList != null) {
        return gameDetailList;
    }
    gameDetailList = new LinkedList<>();
    long sum = redisService.zcount(GameKey.RANK_SELLNUM, GameKey.GAME_RANK_SELLNUM);
    while (cursor < sum && gameDetailList.size() < RANK_SIZE) {
        Set<GameRank> rankSellGame = redisService.zrange(GameKey.RANK_SELLNUM, GameKey.GAME_RANK_SELLNUM, cursor, cursor + 100, GameRank.class);
        Iterator<GameRank> iterator = rankSellGame.iterator();
        cursor = iteratorTraversing(cursor, iterator, page, gameDetailList);
    }
    localStoreService.set(LocalStoreKey.HOT_SELL_INDEX_KEY(), gameDetailList, page + "");
    return gameDetailList;
}


@Transactional(rollbackFor = Exception.class)
public int deleteGame(long gameId){
    GameDetail gameDetail = redisService.get(GameKey.GAME_ID, gameId + "", GameDetail.class);
    int result = gameDao.deleteGame(gameId);
    redisService.del(GameKey.GAME_ID, gameId + "");
    GameRank gameRank = new GameRank(gameDetail.getId(), gameDetail.getType());
    if (gameDetail.getIssuedStatu() != 1) {
        redisService.zrem(GameKey.RANK_UPCOMING, GameKey.GAME_RANK_UPCOMING, gameRank);
    } else {
        redisService.zrem(GameKey.RANK_SELLNUM, GameKey.GAME_RANK_SELLNUM, gameRank);
        redisService.zrem(GameKey.RANK_TIME, GameKey.GAME_RANK_TIME, gameRank);
    }
    /**
     * 删除分类以及标签关系
     */
    typeService.deleteGameTypeByGameId(gameId);
    labelService.deleteGameLabelByGameId(gameId);
    return result;
}


public List<GameDetail> findAllGameByEmail(String email){
    List<Long> gameIdList = userGameService.findGamesIdByEmail(email);
    List<String> newGameIdList = new LinkedList<>();
    for (Long id : gameIdList) {
        newGameIdList.add(id + "");
    }
    List<GameDetail> gameDetailList = redisService.getPipelineBatch(GameKey.GAME_ID, newGameIdList, GameDetail.class);
    return gameDetailList;
}


public long findGamesUpComingSumByType(String typeName){
    long cursor = 0;
    long count = 0;
    long sum = redisService.zcount(GameKey.RANK_UPCOMING, GameKey.GAME_RANK_UPCOMING);
    while (cursor < sum) {
        Set<GameRank> gameRanks = redisService.zrange(GameKey.RANK_UPCOMING, GameKey.GAME_RANK_UPCOMING, cursor, cursor + 100, GameRank.class);
        Iterator<GameRank> iterator = gameRanks.iterator();
        while (iterator.hasNext()) {
            GameRank gameRank = iterator.next();
            if (typeService.isExists(gameRank.getType(), typeName)) {
                count++;
            }
        }
        cursor += 100;
    }
    return count;
}


public Game findOneGameById(long id,String key){
    DynamicDataSourceHolder.putDataSource(key);
    return gameDao.findGameById(id);
}


public long findIssuedGamesSum(){
    return redisService.zcount(GameKey.RANK_TIME, GameKey.GAME_RANK_TIME);
}


public List<SimpleGameShowVo> findAllGameShowByEmail(String email){
    List<SimpleGameShowVo> simpleGameShowVoList = new LinkedList<>();
    List<UserGame> userGameList = userGameService.findUserGameListByEmail(email);
    List<GameDetail> gameDetailList = findAllGameByEmail(email);
    Map<Long, UserGame> map = new HashMap<>();
    for (UserGame userGame : userGameList) {
        map.put(userGame.getGameId(), userGame);
    }
    for (int i = 0; i < gameDetailList.size(); i++) {
        GameDetail gameDetail = gameDetailList.get(i);
        SimpleGameShowVo simpleGameShowVo = new SimpleGameShowVo(gameDetail.getId(), gameDetail.getGameName(), gameDetail.getPosterImage(), map.get(gameDetail.getId()).getPlayTime());
        simpleGameShowVoList.add(simpleGameShowVo);
    }
    return simpleGameShowVoList;
}


public int updateGameIssuedStatu(long gameId){
    Game game = ((GameService) applicationContext.getBean("gameService")).findOneGameById(gameId, DynamicDataSourceHolder.MASTER);
    GameDetail gameDetail = redisService.get(GameKey.GAME_ID, gameId + "", GameDetail.class);
    RankScoreValue<GameRank> rankRankScoreValue = new RankScoreValue<>();
    GameRank gameRank = new GameRank(gameDetail.getId(), gameDetail.getType());
    rankRankScoreValue.setValue(gameRank);
    if (game.getIssuedStatu() == Game.ISSUED_PUBLISH) {
        game.setIssuedStatu(Game.ISSUED_UNPUBLISH);
        gameDetail.setIssuedStatu(Game.ISSUED_UNPUBLISH);
        rankRankScoreValue.setScore(game.getIssuedDate().getTime());
        redisService.zrem(GameKey.RANK_TIME, GameKey.GAME_RANK_TIME, gameRank);
        redisService.zrem(GameKey.RANK_SELLNUM, GameKey.GAME_RANK_SELLNUM, gameRank);
        redisService.zadd(GameKey.RANK_UPCOMING, GameKey.GAME_RANK_UPCOMING, rankRankScoreValue);
    } else {
        game.setIssuedStatu(Game.ISSUED_PUBLISH);
        gameDetail.setIssuedStatu(Game.ISSUED_PUBLISH);
        game.setIssuedDate(new Date());
        gameDetail.setIssuedDate(game.getIssuedDate());
        rankRankScoreValue.setScore(game.getIssuedDate().getTime());
        redisService.zadd(GameKey.RANK_TIME, GameKey.GAME_RANK_TIME, rankRankScoreValue);
        redisService.zadd(GameKey.RANK_SELLNUM, GameKey.GAME_RANK_SELLNUM, rankRankScoreValue);
        redisService.zrem(GameKey.RANK_UPCOMING, GameKey.GAME_RANK_UPCOMING, gameRank);
    }
    redisService.set(GameKey.GAME_ID, gameId + "", gameDetail);
    return ((GameService) applicationContext.getBean("gameService")).updateGame(game);
}


public List<GameDetail> findNewRelease(long page){
    long cursor = 0;
    List<GameDetail> gameDetailList = localStoreService.get(LocalStoreKey.NEW_RELEASE_INDEX_KEY(), List.class, page + "");
    if (gameDetailList != null) {
        return gameDetailList;
    }
    gameDetailList = new LinkedList<>();
    long sum = redisService.zcount(GameKey.RANK_TIME, GameKey.GAME_RANK_TIME);
    while (cursor < sum && gameDetailList.size() < RANK_SIZE) {
        Set<GameRank> rankTimeGame = redisService.zrange(GameKey.RANK_TIME, GameKey.GAME_RANK_TIME, cursor, cursor + 100, GameRank.class);
        Iterator<GameRank> iterator = rankTimeGame.iterator();
        cursor = iteratorTraversing(cursor, iterator, page, gameDetailList);
    }
    localStoreService.set(LocalStoreKey.NEW_RELEASE_INDEX_KEY(), gameDetailList, page + "");
    return gameDetailList;
}


public long findGamesSumByType(String typeName){
    long cursor = 0;
    long count = 0;
    long sum = redisService.zcount(GameKey.RANK_TIME, GameKey.GAME_RANK_TIME);
    while (cursor < sum) {
        Set<GameRank> gameRanks = redisService.zrange(GameKey.RANK_TIME, GameKey.GAME_RANK_TIME, cursor, cursor + 100, GameRank.class);
        Iterator<GameRank> iterator = gameRanks.iterator();
        while (iterator.hasNext()) {
            GameRank gameRank = iterator.next();
            if (typeService.isExists(gameRank.getType(), typeName)) {
                count++;
            }
        }
        cursor += 100;
    }
    return count;
}


public List<SpecialGame> findGamesFetured(){
    Set<GameDetail> gameDetailSet = localStoreService.get(LocalStoreKey.FETURED_CAROUSEL_KEY(), Set.class, "0");
    if (gameDetailSet != null) {
        return new LinkedList<>(gameDetailSet);
    }
    int sum = redisService.get(GameKey.GAME_SUM, GameKey.GAME_SUM_KEY, int.class);
    // Random random=new Random();
    gameDetailSet = new HashSet<>();
    while (gameDetailSet.size() < INDEX_CAROUSEL_SIZE) {
        // int seed=random.nextInt(sum)+1;
        int seed = RandomUtil.randomSeed(sum);
        GameDetail gameDetail = redisService.get(GameKey.GAME_ID, seed + "", GameDetail.class);
        if (gameDetail != null) {
            gameDetailSet.add(gameDetail);
        }
    }
    localStoreService.set(LocalStoreKey.FETURED_CAROUSEL_KEY(), gameDetailSet, "0");
    return new LinkedList<>(gameDetailSet);
}


public int findMaxGameId(){
    return gameDao.findMaxId();
}


@Override
public void afterPropertiesSet(){
    int sum = ((GameService) applicationContext.getBean("gameService")).findGamesSum();
    sum = 30;
    redisService.set(GameKey.GAME_SUM, GameKey.GAME_SUM_KEY, sum);
    for (int i = 0; i < sum; i++) {
        GameDetail gameDetail = ((GameService) applicationContext.getBean("gameService")).findGameById(i + 1);
        if (gameDetail == null) {
            log.error("游戏为空");
            continue;
        }
        RankScoreValue<GameRank> rankTime = new RankScoreValue<>();
        RankScoreValue<GameRank> rankSellNum = new RankScoreValue<>();
        GameRank gameRank = new GameRank();
        gameRank.setId(gameDetail.getId());
        gameRank.setType(gameDetail.getType());
        rankSellNum.setScore(gameDetail.getSellNum());
        rankSellNum.setValue(gameRank);
        rankTime.setValue(gameRank);
        rankTime.setScore(gameDetail.getIssuedDate().getTime());
        redisService.set(GameKey.GAME_ID, gameDetail.getId() + "", gameDetail);
        if (gameDetail.getIssuedStatu() != 0) {
            redisService.zadd(GameKey.RANK_TIME, GameKey.GAME_RANK_TIME, rankTime);
            redisService.zadd(GameKey.RANK_SELLNUM, GameKey.GAME_RANK_SELLNUM, rankSellNum);
        }
        if (gameDetail.getIssuedStatu() != 1) {
            redisService.zadd(GameKey.RANK_UPCOMING, GameKey.GAME_RANK_UPCOMING, rankTime);
        }
    }
}


@Transactional(rollbackFor = Exception.class)
public long addGame(String newGameName,String newGameIntroduction,String newGameAbout,String newGameKind,int newGamePrice,int newGameDiscount,String newGameLowestCpu,String newGameLowestOs,String newGameLowestRam,String newGameLowestXianka,String newGameLowestNetwork,String newGameLowestDirectx,String newGameLowestRom,String newGameLowestShenka,String newGameGoodCpu,String newGameGoodOs,String newGameGoodRam,String newGameGoodXianka,String newGameGoodNetwork,String newGameGoodDirectx,String newGameGoodRom,String newGameGoodShenka){
    SystemNeed lowestSystemNeed = new SystemNeed(newGameLowestOs, newGameLowestCpu, newGameLowestRam, newGameLowestXianka, newGameLowestDirectx, newGameLowestNetwork, newGameLowestRom, newGameLowestShenka);
    SystemNeed goodSystemNeed = new SystemNeed(newGameGoodOs, newGameGoodCpu, newGameGoodRam, newGameGoodXianka, newGameGoodDirectx, newGameGoodNetwork, newGameGoodRom, newGameGoodShenka);
    long lowestSystemId = systemNeedService.addSystemNeed(lowestSystemNeed);
    long goodSystemId = systemNeedService.addSystemNeed(goodSystemNeed);
    Game game = new Game(newGameName, newGameIntroduction, newGameAbout, newGamePrice, lowestSystemId, goodSystemId, newGameDiscount);
    gameDao.addGame(game);
    Type type = typeService.findTypeByTypeName(newGameKind);
    typeService.addTypeToGame(new GameType(game.getId(), type.getId()));
    RankScoreValue<GameRank> rankTime = new RankScoreValue<>();
    List<String> typeList = new LinkedList<>();
    typeList.add(type.getTypeName());
    GameRank gameRank = new GameRank(game.getId(), typeList);
    rankTime.setValue(gameRank);
    rankTime.setScore(game.getIssuedDate().getTime());
    redisService.zadd(GameKey.RANK_UPCOMING, GameKey.GAME_RANK_UPCOMING, rankTime);
    return game.getId();
}


public long findContainGamesNum(String email){
    return userService.findByEmail(email).getBuyGames();
}


public int findGamesSum(){
    return gameDao.gamesSum();
}


public List<GameDetail> findGamesUpComingByType(String typeName,long page){
    long count = 0;
    long cursor = 0;
    List<GameDetail> gameDetailList = localStoreService.get(LocalStoreKey.UP_COMING_CLASS_KEY(), List.class, page + "");
    if (gameDetailList != null) {
        return gameDetailList;
    }
    gameDetailList = new LinkedList<>();
    long sum = redisService.zcount(GameKey.RANK_UPCOMING, GameKey.GAME_RANK_UPCOMING);
    while (cursor < sum && gameDetailList.size() < RANK_SIZE) {
        Set<GameRank> rankTimeGame = redisService.zrange(GameKey.RANK_UPCOMING, GameKey.GAME_RANK_UPCOMING, cursor, cursor + 100, GameRank.class);
        Iterator<GameRank> iterator = rankTimeGame.iterator();
        count = iteratorClass(page, count, iterator, typeName, gameDetailList);
        cursor += 100;
    }
    localStoreService.set(LocalStoreKey.UP_COMING_CLASS_KEY(), gameDetailList, typeName + page);
    return gameDetailList;
}


@Transactional(rollbackFor = Exception.class)
public int updateGameSellNum(long gameId){
    GameDetail gameDetail = redisService.get(GameKey.GAME_ID, gameId + "", GameDetail.class);
    gameDetail.setSellNum(gameDetail.getSellNum() + 1);
    redisService.set(GameKey.GAME_ID, gameId + "", gameDetail);
    Game game = ((GameService) applicationContext.getBean("gameService")).findOneGameById(gameId, DynamicDataSourceHolder.MASTER);
    game.setSellNum(game.getSellNum() + 1);
    GameRank gameRank = new GameRank(gameDetail.getId(), gameDetail.getType());
    redisService.zincr(GameKey.RANK_SELLNUM, GameKey.GAME_RANK_SELLNUM, gameRank);
    return ((GameService) applicationContext.getBean("gameService")).updateGame(game);
}


}