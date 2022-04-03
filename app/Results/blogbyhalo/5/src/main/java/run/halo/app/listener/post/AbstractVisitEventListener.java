package run.halo.app.listener.post;
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
import run.halo.app.Interface.BasePostService;
@Slf4j
public class AbstractVisitEventListener {

 private  Map<Integer,BlockingQueue<Integer>> visitQueueMap;

 private  Map<Integer,PostVisitTask> visitTaskMap;

 private  BasePostService basePostService;

 private  ExecutorService executor;

 private  Integer id;

protected AbstractVisitEventListener(BasePostService basePostService) {
    this.basePostService = basePostService;
    int initCapacity = 8;
    long count = basePostService.count();
    if (count < initCapacity) {
        initCapacity = (int) count;
    }
    visitQueueMap = new ConcurrentHashMap<>(initCapacity << 1);
    visitTaskMap = new ConcurrentHashMap<>(initCapacity << 1);
    this.executor = Executors.newCachedThreadPool();
}
public void handleVisitEvent(AbstractVisitEvent event){
    Assert.notNull(event, "Visit event must not be null");
    // Get post id
    Integer id = event.getId();
    log.debug("Received a visit event, post id: [{}]", id);
    // Get post visit queue
    BlockingQueue<Integer> postVisitQueue = visitQueueMap.computeIfAbsent(id, this::createEmptyQueue);
    visitTaskMap.computeIfAbsent(id, this::createPostVisitTask);
    // Put a visit for the post
    postVisitQueue.put(id);
}


public BlockingQueue<Integer> createEmptyQueue(Integer postId){
    // Create a new queue
    return new LinkedBlockingQueue<>();
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


public PostVisitTask createPostVisitTask(Integer postId){
    // Create new post visit task
    PostVisitTask postVisitTask = new PostVisitTask(postId);
    // Start a post visit task
    executor.execute(postVisitTask);
    log.debug("Created a new post visit task for post id: [{}]", postId);
    return postVisitTask;
}


}