package com.ukefu.util.task;
 import com.lmax.disruptor.RingBuffer;
public class DSDataEventProducer {

 private  RingBuffer<DSDataEvent> ringBuffer;

public DSDataEventProducer(RingBuffer<DSDataEvent> ringBuffer) {
    this.ringBuffer = ringBuffer;
}
public void onData(DSData dsData){
    // Grab the next sequence
    long sequence = ringBuffer.next();
    try {
        // Get the entry in the Disruptor
        DSDataEvent event = ringBuffer.get(sequence);
        event.setDSData(dsData);
    } finally {
        ringBuffer.publish(sequence);
    }
}


}