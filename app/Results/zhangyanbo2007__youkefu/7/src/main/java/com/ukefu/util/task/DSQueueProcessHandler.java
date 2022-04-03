package com.ukefu.util.task;
 import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import com.lmax.disruptor.dsl.Disruptor;
public class DSQueueProcessHandler {

 private  Disruptor<DSDataEvent> disruptor;

 private  Executor executor;

 private  DSQueueProcessHandler dsQueneProcess;

 private  DSDataEventProducer producer;


@SuppressWarnings({ "unchecked", "deprecation" })
public void process(){
    disruptor = new Disruptor<DSDataEvent>(new DSDataEventFactory(), 1024, executor);
    disruptor.handleEventsWith(new DSDataEventHandler());
    disruptor.start();
    producer = new DSDataEventProducer(disruptor.getRingBuffer());
}


public DSData doTask(DSData dsData){
    producer.onData(dsData);
    return dsData;
}


public DSQueueProcessHandler getInstance(){
    return dsQueneProcess;
}


}