package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MQProducerController {

 private MQProducer mqproducer;


@PutMapping
("/productEvent")
public void productEvent(@RequestParam(name = "event") Event event){
mqproducer.productEvent(event);
}


}