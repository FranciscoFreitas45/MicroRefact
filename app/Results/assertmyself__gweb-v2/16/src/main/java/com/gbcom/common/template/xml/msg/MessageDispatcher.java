package com.gbcom.common.template.xml.msg;
 import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import org.apache.log4j.Logger;
import com.gbcom.common.template.xml.msg.process.IMessageProcess;
public class MessageDispatcher {

 private  int THREAD_NUM;

 private  int BLOCK_QUEUE_MAX_SIZE;

 private  Logger LOG;

 private  BlockingQueue<MessageInfo> queue;

 private  ExecutorService executor;

 private  boolean isRunning;


public void stop(){
    executor.shutdownNow();
    isRunning = false;
    LOG.info("MessageDispatcher stop~~~~~~~~~~~~");
}


public boolean isRunning(){
    return isRunning;
}


public void start(){
    // 创建一个可根据需要创建新线程的线程池 不限制线程池大小
    // executor = Executors.newCachedThreadPool();
    queue = new LinkedBlockingDeque<MessageInfo>(BLOCK_QUEUE_MAX_SIZE);
    executor = Executors.newFixedThreadPool(THREAD_NUM);
    for (int i = 0; i < THREAD_NUM; i++) {
        executor.execute(new AlarmInnerDispatcher());
    }
    isRunning = true;
    LOG.info("MessageDispatcher start~~~~~~~");
}


public void onMessage(MessageInfo info){
    if (info == null) {
        return;
    }
    try {
        int id = info.getMessageID();
        IMessageProcess process = RouteInfoManager.getInstance().getMsgProcess(id);
        if (process == null) {
            LOG.error("Msg process  is not exist ;;return imme");
            return;
        }
        process.processMsg(info);
    } catch (Exception e) {
        LOG.error("Message Process exception (bussiness problem)", e);
    }
}


@Override
public void run(){
    while (!Thread.currentThread().isInterrupted()) {
        try {
            onMessage(queue.take());
            if (LOG.isDebugEnabled()) {
                LOG.debug("Thread--" + Thread.currentThread().getName() + " ;;; after process queue.size()=" + queue.size());
            }
        } catch (InterruptedException e) {
            // 线程中断,跑出循环
            LOG.error("MessageDispatcher thread interrupted", e);
            isRunning = false;
            break;
        } catch (Exception e) {
            // 抓到其他异常 继续循环
            e.printStackTrace();
            LOG.error("MessageDispatcher thread exception", e);
            continue;
        }
    }
}


public void addMessage(MessageInfo message){
    if (!isRunning) {
        LOG.error("MessageDispatcher is not running, the message below may not process");
    }
    LOG.info("addMessage to MessageDispatcher Queue, size = " + queue.size());
    try {
        if (queue.size() >= BLOCK_QUEUE_MAX_SIZE) {
            LOG.warn("MSG:: clear message queue , size = " + BLOCK_QUEUE_MAX_SIZE);
            LOG.warn("MSG:: this oper is very harm ,mayben some message can not be process ......");
            queue.clear();
        }
        queue.put(message);
    } catch (Exception e) {
        LOG.error("add message to queue interrupted", e);
    }
}


}