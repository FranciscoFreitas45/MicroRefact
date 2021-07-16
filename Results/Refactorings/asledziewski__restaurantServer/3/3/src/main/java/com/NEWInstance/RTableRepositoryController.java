package com.NEWInstance;

import com.pl.edu.wat.wcy.pz.restaurantServer.entity.RTable;
import com.pl.edu.wat.wcy.pz.restaurantServer.repository.RTableRepository;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin
public class RTableRepositoryController {

 private RTableRepository rtablerepository;


@GetMapping
("/findById")
public Optional<RTable> findById(@RequestParam(name = "Object") Long Object){
    System.out.println("--------" + rtablerepository.findById( Object));
  return rtablerepository.findById( Object);
}


}