package com.ukefu.webim.util.disruptor;
 import com.lmax.disruptor.RingBuffer;
import com.ukefu.util.event.UserEvent;
import com.ukefu.util.event.UserDataEvent;
public class UserDataEventProducer {

 private  RingBuffer<UserDataEvent> ringBuffer;

public UserDataEventProducer(RingBuffer<UserDataEvent> ringBuffer) {
    this.ringBuffer = ringBuffer;
}
public void onData(UserEvent userEvent){
    // Grab the next sequence
    long id = ringBuffer.next();
    try {
        UserDataEvent event = ringBuffer.get(id);
        event.setEvent(userEvent);
    } finally {
        ringBuffer.publish(id);
    }
}


}