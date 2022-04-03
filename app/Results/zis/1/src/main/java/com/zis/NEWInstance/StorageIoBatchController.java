package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StorageIoBatchController {

 private StorageIoBatchDao storageiobatchdao;


@PutMapping
("/setAmount/{id}")
public void setAmount(@PathVariable(name = "id") Integer id,@RequestParam(name = "amount") Integer amount){
 storageiobatchdao.setAmount(id,amount);
}


}