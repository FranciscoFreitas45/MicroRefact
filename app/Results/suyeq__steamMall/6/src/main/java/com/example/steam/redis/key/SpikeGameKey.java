package com.example.steam.redis.key;
 import com.example.steam.redis.BaseKey;
public class SpikeGameKey extends BaseKey{

 public  String SPIKE_STOCK_KEY;

 public  String RANDM_PATH_KEY;

 public  String SPIKE_TIMES_KEY;

 private  int MAX_EXSIS_tIME;

 public  SpikeGameKey SPIKE_ID;

 public  SpikeGameKey SPIKE_STOCK;

 public  SpikeGameKey RANDM_PATH;

 public  SpikeGameKey SPIKE_TIMES;

private SpikeGameKey(int expiredTime) {
    super(expiredTime);
}
}