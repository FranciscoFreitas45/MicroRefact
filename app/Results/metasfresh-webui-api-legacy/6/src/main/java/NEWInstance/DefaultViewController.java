package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DefaultViewController {

 private DefaultView defaultview;

 private DefaultView defaultview;


@GetMapping
("/cast")
public DefaultView cast(@RequestParam(name = "view") IView view){
  return defaultview.cast(view);
}


}