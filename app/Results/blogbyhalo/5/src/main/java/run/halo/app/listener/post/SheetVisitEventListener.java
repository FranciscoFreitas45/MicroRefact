package run.halo.app.listener.post;
 import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import run.halo.app.event.post.SheetVisitEvent;
import run.halo.app.service.SheetService;
@Component
public class SheetVisitEventListener extends AbstractVisitEventListener{

protected SheetVisitEventListener(SheetService sheetService) {
    super(sheetService);
}
@Async
@EventListener
public void onSheetVisitEvent(SheetVisitEvent event){
    handleVisitEvent(event);
}


}