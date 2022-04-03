package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EventServiceController {

 private EventService eventservice;


@GetMapping
("/getEnabled")
public Object getEnabled(@RequestParam(name = "Object") Object Object){
  return eventservice.getEnabled(Object);
}


@GetMapping
("/getByHash")
public Object getByHash(@RequestParam(name = "Object") Object Object){
  return eventservice.getByHash(Object);
}


@GetMapping
("/getEnabledByHash")
public Object getEnabledByHash(@RequestParam(name = "Object") Object Object){
  return eventservice.getEnabledByHash(Object);
}


}