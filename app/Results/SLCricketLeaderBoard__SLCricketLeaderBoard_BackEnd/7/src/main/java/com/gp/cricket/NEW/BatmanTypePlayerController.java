package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.BatmanType;
@RestController
@CrossOrigin
public class BatmanTypePlayerController {

@Autowired
 private BatmanTypePlayerService batmantypeplayerservice;


@GetMapping
("/Player/{id}/BatmanType/getBatmanTypeId")
public BatmanType getBatmanTypeId(@PathVariable(name="id") Integer batmanTypeIdv2){
return batmantypeplayerservice.getBatmanTypeId(batmanTypeIdv2);
}


@PutMapping
("/Player/{id}/BatmanType/setBatmanTypeId")
public void setBatmanTypeId(@PathVariable(name="id") Integer batmanTypeIdv2,@RequestBody BatmanType batmanTypeId){
batmantypeplayerservice.setBatmanTypeId(batmanTypeIdv2,batmanTypeId);
}


}