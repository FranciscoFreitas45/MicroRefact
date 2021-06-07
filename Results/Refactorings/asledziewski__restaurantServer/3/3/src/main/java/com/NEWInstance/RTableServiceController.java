package com.NEWInstance;

import com.pl.edu.wat.wcy.pz.restaurantServer.entity.RTable;
import com.pl.edu.wat.wcy.pz.restaurantServer.service.RTableService;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin
public class RTableServiceController {

 private RTableService rtableservice;


@GetMapping
("/getRTableById")
public Optional<RTable> getRTableById(@RequestParam(name = "id") Long id){
  return rtableservice.getRTableById(id);
}


@GetMapping
("/getRTablesBySize")
public List<RTable> getRTablesBySize(@RequestParam(name = "size") int size){
  return rtableservice.getRTablesBySize(size);
}


}