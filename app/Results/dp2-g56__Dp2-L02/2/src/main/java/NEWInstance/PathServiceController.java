package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PathServiceController {

 private PathService pathservice;


@GetMapping
("/create")
public Path create(){
  return pathservice.create();
}


@GetMapping
("/save")
public Path save(@RequestParam(name = "path") Path path){
  return pathservice.save(path);
}


@PutMapping
("/delete")
public void delete(@RequestParam(name = "path") Path path){
pathservice.delete(path);
}


}