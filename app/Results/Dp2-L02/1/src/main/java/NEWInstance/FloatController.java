package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FloatController {

 private Float float;

 private Float float;


@PutMapping
("/setPictures")
public void setPictures(@RequestParam(name = "pictures") List<String> pictures){
float.setPictures(pictures);
}


@PutMapping
("/setDescription")
public void setDescription(@RequestParam(name = "description") String description){
float.setDescription(description);
}


@PutMapping
("/setTitle")
public void setTitle(@RequestParam(name = "title") String title){
float.setTitle(title);
}


}