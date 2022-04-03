package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WebsocketSenderController {

 private WebsocketSender websocketsender;


@PutMapping
("/convertAndSend")
public void convertAndSend(@RequestParam(name = "destination") String destination,@RequestParam(name = "event") Object event){
websocketsender.convertAndSend(destination,event);
}


}