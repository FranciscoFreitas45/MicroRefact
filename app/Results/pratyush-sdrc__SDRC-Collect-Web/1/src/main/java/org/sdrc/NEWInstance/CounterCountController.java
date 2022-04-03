package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CounterCountController {

 private SpringDataCounterCountRepository springdatacountercountrepository;


@PutMapping
("/setNoOfCounter/{id}")
public void setNoOfCounter(@PathVariable(name = "id") Integer id,@RequestParam(name = "noOfCounter") Integer noOfCounter){
 springdatacountercountrepository.setNoOfCounter(id,noOfCounter);
}


}