package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CounterCountRepositoryController {

 private CounterCountRepository countercountrepository;


@GetMapping
("/findTotalCount")
public CounterCount findTotalCount(){
  return countercountrepository.findTotalCount();
}


@PutMapping
("/save")
public void save(@RequestParam(name = "counterCount") CounterCount counterCount){
countercountrepository.save(counterCount);
}


}