package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SearchController {

 private Search search;

 private Search search;


@PutMapping
("/setOrderQuery")
public void setOrderQuery(@RequestParam(name = "orderQuery") String orderQuery){
search.setOrderQuery(orderQuery);
}


}