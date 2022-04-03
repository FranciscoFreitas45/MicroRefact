package org.live.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.entity.Anchor;
@RestController
@CrossOrigin
public class AnchorLiveRoomController {

@Autowired
 private AnchorLiveRoomService anchorliveroomservice;


@GetMapping
("/LiveRoom/{id}/Anchor/getAnchor")
public Anchor getAnchor(@PathVariable(name="id") String idFK1I){
return anchorliveroomservice.getAnchor(idFK1I);
}


@PutMapping
("/LiveRoom/{id}/Anchor/setAnchor")
public void setAnchor(@PathVariable(name="id") String idFK1I,@RequestBody Anchor anchor){
anchorliveroomservice.setAnchor(idFK1I,anchor);
}


}