package org.live.common.utils;
 import java.util.UUID;
public class UUIDGenerator {


public String getUUID(){
    return UUID.randomUUID().toString().replace("-", "");
}


public String createSalt(){
    return getUUID();
}


}