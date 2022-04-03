package com.example.steam.redis.key;
 import com.example.steam.redis.BaseKey;
public class GameKey extends BaseKey{

 private  int FUTURED_KEY_TIME;

 public  String FUTURED_KEY;

 public  String SPECIAL_INDEX_KEY;

 private  int SPECIAL_KEY_TIME;

 public  String NEW_RELEASE_INDEX_KEY;

 private  int NEW_RELEASE_INDEX_KEY_TIME;

 public  String HOT_SELL_INDEX_KEY;

 private  int HOT_SELL_INDEX_KEY_TIME;

 public  String UP_COMING_INDEX_KEY;

 private  int UP_COMING_INDEX_KEY_TIME;

 public  String GAME_SUM_KEY;

 public  String GAME_RANK_TIME;

 public  String GAME_RANK_SELLNUM;

 public  String GAME_RANK_UPCOMING;

 public  GameKey GAME_ID;

 public  GameKey FETURED_GAME;

 public  GameKey SPECIAL_INDEX_GAME;

 public  GameKey NEW_RELEASE_INDEX_GAME;

 public  GameKey HOT_SELL_INDEX_GAME;

 public  GameKey UP_COMING_INDEX_GAME;

 public  GameKey GAME_SUM;

 public  GameKey RANK_TIME;

 public  GameKey RANK_SELLNUM;

 public  GameKey RANK_UPCOMING;

private GameKey(int expiredTime) {
    super(expiredTime);
}
}