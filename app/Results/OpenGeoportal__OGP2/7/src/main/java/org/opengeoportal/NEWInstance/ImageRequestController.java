package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ImageRequestController {

 private ImageRequest imagerequest;

 private ImageRequest imagerequest;


@GetMapping
("/equals")
public boolean equals(@RequestParam(name = "o") Object o){
  return imagerequest.equals(o);
}


@PutMapping
("/setRequestId")
public void setRequestId(@RequestParam(name = "requestId") UUID requestId){
imagerequest.setRequestId(requestId);
}


@PutMapping
("/setSessionId")
public void setSessionId(@RequestParam(name = "sessionId") String sessionId){
imagerequest.setSessionId(sessionId);
}


}