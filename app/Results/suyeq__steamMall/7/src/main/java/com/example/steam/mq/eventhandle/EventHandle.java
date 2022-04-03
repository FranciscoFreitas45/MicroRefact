package com.example.steam.mq.eventhandle;
 import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import org.springframework.context.ApplicationContext;
import java.util.List;
public interface EventHandle {


public List<EventType> bindEventType()
;

public void eventHandle(Event event,ApplicationContext applicationContext)
;

}