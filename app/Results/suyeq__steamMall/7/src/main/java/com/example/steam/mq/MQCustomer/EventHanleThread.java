package com.example.steam.mq.MQCustomer;
 import com.example.steam.mq.eventhandle.EventHandle;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.MQKey;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import java.util;
import java.util.concurrent;
public class EventHanleThread implements Runnable{

 private Event event;

 private EventHandle eventHandle;

 private ApplicationContext applicationContext;

EventHanleThread(Event event, EventHandle eventHandle, ApplicationContext applicationContext) {
    this.event = event;
    this.eventHandle = eventHandle;
    this.applicationContext = applicationContext;
}
@Override
public void run(){
    eventHandle.eventHandle(event, applicationContext);
}


}