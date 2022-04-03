package com.poseidon.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CustomerVOController {

 private CustomerVO customervo;


@PutMapping
("/setCreatedOn")
public void setCreatedOn(@RequestParam(name = "createdOn") OffsetDateTime createdOn){
customervo.setCreatedOn(createdOn);
}


@PutMapping
("/setModifiedOn")
public void setModifiedOn(@RequestParam(name = "modifiedOn") OffsetDateTime modifiedOn){
customervo.setModifiedOn(modifiedOn);
}


@PutMapping
("/setModifiedBy")
public void setModifiedBy(@RequestParam(name = "modifiedBy") String modifiedBy){
customervo.setModifiedBy(modifiedBy);
}


}