package com.gbcom.common.template.xml.msg.MessageDispatcher;
 import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import org.apache.log4j.Logger;
import com.gbcom.common.template.xml.msg.process.IMessageProcess;
public class AlarmInnerDispatcher implements Runnable{


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


}