package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JsonResponseController {

 private JsonResponse jsonresponse;

 private JsonResponse jsonresponse;


@PutMapping
("/setStatus")
public void setStatus(@RequestParam(name = "status") ResponseStatus status){
jsonresponse.setStatus(status);
}


@GetMapping
("/toString")
public String toString(){
  return jsonresponse.toString();
}


@PutMapping
("/setResponse")
public void setResponse(@RequestParam(name = "response") ResultStatusInfo response){
jsonresponse.setResponse(response);
}


@PutMapping
("/setResult")
public void setResult(@RequestParam(name = "result") Object result){
jsonresponse.setResult(result);
}


}