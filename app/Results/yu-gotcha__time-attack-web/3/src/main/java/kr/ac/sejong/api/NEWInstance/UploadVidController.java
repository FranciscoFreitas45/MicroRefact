package kr.ac.sejong.api.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UploadVidController {

 private UploadVid uploadvid;


@GetMapping
("/setUpVidName")
public Object setUpVidName(@RequestParam(name = "Object") Object Object){
  return uploadvid.setUpVidName(Object);
}


@GetMapping
("/setUpVidSavedName")
public Object setUpVidSavedName(@RequestParam(name = "Object") Object Object){
  return uploadvid.setUpVidSavedName(Object);
}


@GetMapping
("/setUpVidPath")
public Object setUpVidPath(@RequestParam(name = "Object") Object Object){
  return uploadvid.setUpVidPath(Object);
}


@GetMapping
("/setVidUpUser")
public Object setVidUpUser(@RequestParam(name = "Object") Object Object){
  return uploadvid.setVidUpUser(Object);
}


}