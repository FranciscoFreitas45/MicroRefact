import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class CommonController {

 private Common common;


@GetMapping
("/sideNavMap")
public ModelMap sideNavMap(){
  return common.sideNavMap();
}


}