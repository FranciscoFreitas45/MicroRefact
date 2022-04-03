package com.example.steam.utils;
 import com.example.steam.redis.RedisService;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
public class UUIDUtil {


public String randomUUID(){
    return UUID.randomUUID().toString().replaceAll("-", "");
}


}