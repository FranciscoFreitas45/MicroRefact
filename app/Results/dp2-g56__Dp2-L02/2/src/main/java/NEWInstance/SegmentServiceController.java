package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SegmentServiceController {

 private SegmentService segmentservice;


@GetMapping
("/createSegment")
public Segment createSegment(){
  return segmentservice.createSegment();
}


@GetMapping
("/getSegmentByParade")
public List<Segment> getSegmentByParade(@RequestParam(name = "paradeId") Integer paradeId){
  return segmentservice.getSegmentByParade(paradeId);
}


}