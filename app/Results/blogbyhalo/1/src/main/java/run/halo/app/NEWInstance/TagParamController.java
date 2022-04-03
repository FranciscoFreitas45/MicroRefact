package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TagParamController {

 private TagParam tagparam;

 private TagParam tagparam;


@PutMapping
("/update")
public void update(@RequestParam(name = "tag") Tag tag){
tagparam.update(tag);
}


}