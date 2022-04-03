package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class NewsServiceController {

 private NewsService newsservice;


@GetMapping
("/getEnabledByHash")
public Object getEnabledByHash(@RequestParam(name = "Object") Object Object){
  return newsservice.getEnabledByHash(Object);
}


}