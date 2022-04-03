package com.cocay.sicecd.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LoggingController {

 private Logging logging;


@PutMapping
("/setTrace")
public void setTrace(@RequestParam(name = "action") String action,@RequestParam(name = "comments") String comments){
logging.setTrace(action,comments);
}


@PutMapping
("/logtrace")
public void logtrace(@RequestParam(name = "action") String action,@RequestParam(name = "comentario") String comentario){
logging.logtrace(action,comentario);
}


}