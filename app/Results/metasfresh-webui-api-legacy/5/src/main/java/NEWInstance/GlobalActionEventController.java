package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GlobalActionEventController {

 private GlobalActionEvent globalactionevent;

 private GlobalActionEvent globalactionevent;


@GetMapping
("/toDisplayQRCodeProcessResult")
public ProcessExecutionResult.DisplayQRCode toDisplayQRCodeProcessResult(){
  return globalactionevent.toDisplayQRCodeProcessResult();
}


}