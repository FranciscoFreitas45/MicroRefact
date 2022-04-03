package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ViewRowController {

 private ViewRow viewrow;

 private ViewRow viewrow;


@GetMapping
("/cast")
public ViewRow cast(@RequestParam(name = "row") IViewRow row){
  return viewrow.cast(row);
}


}