package com.cache;
 import lombok.Builder;
import lombok.Data;
import java.util;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
@Data
@Builder
public class Value {

 private  Object value;

@Builder.Default
 private  long expireTime;

 private  long latestAccessTime;


}