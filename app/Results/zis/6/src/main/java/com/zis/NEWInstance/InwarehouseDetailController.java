package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class InwarehouseDetailController {

 private InwarehouseDetailDao inwarehousedetaildao;


@PutMapping
("/setAmount/{id}")
public void setAmount(@PathVariable(name = "id") Integer id,@RequestParam(name = "amount") Integer amount){
 inwarehousedetaildao.setAmount(id,amount);
}


}