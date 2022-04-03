package run.halo.app.listener.post;
 import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import run.halo.app.event.post.PostVisitEvent;
import run.halo.app.service.PostService;
@Component
public class PostVisitEventListener extends AbstractVisitEventListener{

public PostVisitEventListener(PostService postService) {
    super(postService);
}
@Async
@EventListener
public void onPostVisitEvent(PostVisitEvent event){
    handleVisitEvent(event);
}


}