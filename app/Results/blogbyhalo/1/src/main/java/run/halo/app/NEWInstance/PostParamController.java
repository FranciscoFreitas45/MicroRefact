package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PostParamController {

 private PostParam postparam;

 private PostParam postparam;


@PutMapping
("/update")
public void update(@RequestParam(name = "post") Post post){
postparam.update(post);
}


}