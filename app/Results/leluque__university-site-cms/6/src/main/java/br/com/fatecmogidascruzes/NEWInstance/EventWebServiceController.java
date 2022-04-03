package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EventWebServiceController {

 private EventWebService eventwebservice;


@GetMapping
("/getHomeEvents")
public List<EventHomeDTO> getHomeEvents(){
  return eventwebservice.getHomeEvents();
}


@GetMapping
("/abrirLink")
public Event abrirLink(@RequestParam(name = "hash") UUID hash){
  return eventwebservice.abrirLink(hash);
}


@GetMapping
("/getEventDetail")
public EventDetailDTO getEventDetail(@RequestParam(name = "hash") UUID hash){
  return eventwebservice.getEventDetail(hash);
}


@GetMapping
("/getEnabledEvents")
public List<EventHomeDTO> getEnabledEvents(){
  return eventwebservice.getEnabledEvents();
}


}