package com.example.steam.redis.key;
 import com.example.steam.redis.BaseKey;
public class CommentKey extends BaseKey{

 public  String COMMENT_RANK_ZANNUM_KEY;

 public  String COMMENT_RANK_TIME_KEY;

 public  String COMMENT_SUM_KEY;

 public  CommentKey COMMENT_ID;

 public  CommentKey COMMENT_RANK_ZANNUM;

 public  CommentKey COMMENT_RANK_TIME;

 public  CommentKey COMMENT_SUM;

public CommentKey(int expiredTime) {
    super(expiredTime);
}
}