package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.BallerType;
@RestController
@CrossOrigin
public class BallerTypePlayerController {

@Autowired
 private BallerTypePlayerService ballertypeplayerservice;


@GetMapping
("/Player/{id}/BallerType/getBallerTypeId")
public BallerType getBallerTypeId(@PathVariable(name="id") Integer ballerTypeIdv2){
return ballertypeplayerservice.getBallerTypeId(ballerTypeIdv2);
}


@PutMapping
("/Player/{id}/BallerType/setBallerTypeId")
public void setBallerTypeId(@PathVariable(name="id") Integer ballerTypeIdv2,@RequestBody BallerType ballerTypeId){
ballertypeplayerservice.setBallerTypeId(ballerTypeIdv2,ballerTypeId);
}


}