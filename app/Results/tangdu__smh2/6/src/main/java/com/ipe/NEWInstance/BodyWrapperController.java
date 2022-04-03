package com.ipe.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BodyWrapperController {

 private BodyWrapper bodywrapper;


@PutMapping
("/setRows")
public void setRows(@RequestParam(name = "rows") Object rows){
bodywrapper.setRows(rows);
}


@PutMapping
("/setTotal")
public void setTotal(@RequestParam(name = "total") Long total){
bodywrapper.setTotal(total);
}


@PutMapping
("/setSuccess")
public void setSuccess(@RequestParam(name = "success") Boolean success){
bodywrapper.setSuccess(success);
}


}