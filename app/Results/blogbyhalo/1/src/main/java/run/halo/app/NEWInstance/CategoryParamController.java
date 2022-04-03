package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CategoryParamController {

 private CategoryParam categoryparam;

 private CategoryParam categoryparam;


@PutMapping
("/update")
public void update(@RequestParam(name = "category") Category category){
categoryparam.update(category);
}


}