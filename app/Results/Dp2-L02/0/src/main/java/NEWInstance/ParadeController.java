package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ParadeController {

 private Parade parade;

 private Parade parade;


@PutMapping
("/setPath")
public void setPath(@RequestParam(name = "path") Path path){
parade.setPath(path);
}


@PutMapping
("/setRequests")
public void setRequests(@RequestParam(name = "requests") List<Request> requests){
parade.setRequests(requests);
}


}