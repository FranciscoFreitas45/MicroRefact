package com.example.steam.mq;
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
import com.example.steam.Interface.RedisService;
@Service
public class MQCustomer implements DisposableBean,InitializingBean{

 private Logger log;

@Autowired
 private RedisService redisService;

@Autowired
 private ApplicationContext applicationContext;

 private ThreadPoolExecutor threadPoolExecutor;

 private Map<EventType,List<EventHandle>> eventMap;

 private Event event;

 private EventHandle eventHandle;

 private ApplicationContext applicationContext;


@Override
public void afterPropertiesSet(){
    int coreThread = Runtime.getRuntime().availableProcessors();
    threadPoolExecutor = new ThreadPoolExecutor(coreThread, coreThread * 2, 3, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    Map<String, EventHandle> eventHandleMap = applicationContext.getBeansOfType(EventHandle.class);
    for (Map.Entry<String, EventHandle> entry : eventHandleMap.entrySet()) {
        List<EventType> typeList = entry.getValue().bindEventType();
        for (int i = 0; i < typeList.size(); i++) {
            List<EventHandle> handleList = eventMap.get(typeList.get(i));
            if (handleList == null) {
                handleList = new LinkedList<>();
            }
            if (!handleList.contains(entry.getValue())) {
                handleList.add(entry.getValue());
            }
            eventMap.put(typeList.get(i), handleList);
        }
    }
    // System.out.println(eventMap.toString());
    threadPoolExecutor.execute(new Runnable() {

        @Override
        public void run() {
            while (true) {
                Event event = redisService.rpop(MQKey.MQ, Event.EVENT_KEY, Event.class);
                if (event == null) {
                    continue;
                }
                if (!eventMap.keySet().contains(event.getEventType())) {
                    log.error("未知的事件类型");
                    continue;
                }
                for (EventHandle eventHandle : eventMap.get(event.getEventType())) {
                    threadPoolExecutor.execute(new EventHanleThread(event, eventHandle, applicationContext));
                }
                /**
                 * 每隔500毫秒取一次
                 */
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.error("I/O异常");
                    Thread.currentThread().interrupt();
                }
            }
        }
    });
}


@Override
public void destroy(){
    threadPoolExecutor.shutdown();
}


@Override
public void run(){
    eventHandle.eventHandle(event, applicationContext);
}


}