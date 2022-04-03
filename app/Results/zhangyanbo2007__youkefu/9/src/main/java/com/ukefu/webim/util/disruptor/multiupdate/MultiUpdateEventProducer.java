package com.ukefu.webim.util.disruptor.multiupdate;
 import com.lmax.disruptor.RingBuffer;
import com.ukefu.util.event.MultiUpdateEvent;
import com.ukefu.util.event.UserDataEvent;
@SuppressWarnings("rawtypes")
public class MultiUpdateEventProducer {

 private  RingBuffer<UserDataEvent> ringBuffer;

public MultiUpdateEventProducer(RingBuffer<UserDataEvent> ringBuffer) {
    this.ringBuffer = ringBuffer;
}
public void onData(MultiUpdateEvent event){
    // Grab the next sequence
    long id = ringBuffer.next();
    try {
        UserDataEvent multiEventData = ringBuffer.get(id);
        multiEventData.setEvent(event);
    } finally {
        ringBuffer.publish(id);
    }
}


}