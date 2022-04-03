package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JSONUserSessionChangesEventController {

 private JSONUserSessionChangesEvent jsonusersessionchangesevent;

 private JSONUserSessionChangesEvent jsonusersessionchangesevent;


@GetMapping
("/isEmpty")
public boolean isEmpty(){
  return jsonusersessionchangesevent.isEmpty();
}


}