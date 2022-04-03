package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SegmentController {

 private Segment segment;

 private Segment segment;


@PutMapping
("/setDestinationLatitude")
public void setDestinationLatitude(@RequestParam(name = "destinationLatitude") Double destinationLatitude){
segment.setDestinationLatitude(destinationLatitude);
}


@PutMapping
("/setDestinationLongitude")
public void setDestinationLongitude(@RequestParam(name = "destinationLongitude") Double destinationLongitude){
segment.setDestinationLongitude(destinationLongitude);
}


@PutMapping
("/setOriginLatitude")
public void setOriginLatitude(@RequestParam(name = "originLatitude") Double originLatitude){
segment.setOriginLatitude(originLatitude);
}


@PutMapping
("/setOriginLongitude")
public void setOriginLongitude(@RequestParam(name = "originLongitude") Double originLongitude){
segment.setOriginLongitude(originLongitude);
}


@PutMapping
("/setTime")
public void setTime(@RequestParam(name = "time") Integer time){
segment.setTime(time);
}


}