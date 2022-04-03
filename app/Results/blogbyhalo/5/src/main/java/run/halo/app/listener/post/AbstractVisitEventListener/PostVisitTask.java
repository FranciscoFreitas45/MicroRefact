package run.halo.app.listener.post.AbstractVisitEventListener;
 import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import run.halo.app.event.post.AbstractVisitEvent;
import run.halo.app.service.base.BasePostService;
public class PostVisitTask implements Runnable{

 private  Integer id;

private PostVisitTask(Integer id) {
    this.id = id;
}
@Override
public void run(){
    while (!Thread.currentThread().isInterrupted()) {
        try {
            BlockingQueue<Integer> postVisitQueue = visitQueueMap.get(id);
            Integer postId = postVisitQueue.take();
            log.debug("Took a new visit for post id: [{}]", postId);
            // Increase the visit
            basePostService.increaseVisit(postId);
            log.debug("Increased visits for post id: [{}]", postId);
        } catch (InterruptedException e) {
            log.debug("Post visit task: " + Thread.currentThread().getName() + " was interrupted", e);
        // Ignore this exception
        }
    }
    log.debug("Thread: [{}] has been interrupted", Thread.currentThread().getName());
}


}