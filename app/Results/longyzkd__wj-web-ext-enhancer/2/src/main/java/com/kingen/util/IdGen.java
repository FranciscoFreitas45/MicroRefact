package com.kingen.util;
 import java.io.Serializable;
import java.security.SecureRandom;
import java.util.UUID;
import org.activiti.engine.impl.cfg.IdGenerator;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
@Service
@Lazy(false)
public class IdGen implements SessionIdGenerator,IdGenerator{

 private  SecureRandom random;


public String randomBase62(int length){
    byte[] randomBytes = new byte[length];
    random.nextBytes(randomBytes);
    return Encodes.encodeBase62(randomBytes);
}


@Override
public String getNextId(){
    return IdGen.uuid();
}


@Override
public Serializable generateId(Session session){
    return IdGen.uuid();
}


public void main(String[] args){
    System.out.println(IdGen.uuid());
    System.out.println(IdGen.uuid().length());
    System.out.println(new IdGen().getNextId());
    for (int i = 0; i < 1000; i++) {
        System.out.println(IdGen.randomLong() + "  " + IdGen.randomBase62(5));
    }
}


public String uuid(){
    return UUID.randomUUID().toString().replaceAll("-", "");
}


public long randomLong(){
    return Math.abs(random.nextLong());
}


}