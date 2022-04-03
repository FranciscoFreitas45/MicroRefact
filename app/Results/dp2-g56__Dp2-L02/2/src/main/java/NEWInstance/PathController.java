package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PathController {

 private Path path;

 private Path path;


@PutMapping
("/setSegments")
public void setSegments(@RequestParam(name = "segments") List<Segment> segments){
path.setSegments(segments);
}


}