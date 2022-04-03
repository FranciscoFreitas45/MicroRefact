package com.example.steam.redis.key;
 import com.example.steam.redis.BaseKey;
public class FileKey extends BaseKey{

 public  FileKey IMAGE_LIST;

public FileKey(int expiredTime) {
    super(expiredTime);
}
}