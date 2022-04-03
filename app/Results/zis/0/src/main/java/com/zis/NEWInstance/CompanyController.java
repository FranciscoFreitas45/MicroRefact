package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import com.zis.shop.repository.CompanyDao;
 import java.util.*;
@RestController
@CrossOrigin
public class CompanyController {

 private CompanyDao companydao;


@PutMapping
("/setUpdateTime/{id}")
public void setUpdateTime(@PathVariable(name = "id") Integer id,@RequestParam(name = "updateTime") Date updateTime){
 companydao.setUpdateTime(id,updateTime);
}


@PutMapping
("/setStatus/{id}")
public void setStatus(@PathVariable(name = "id") Integer id,@RequestParam(name = "status") String status){
 companydao.setStatus(id,status);
}


@PutMapping
("/setAddress/{id}")
public void setAddress(@PathVariable(name = "id") Integer id,@RequestParam(name = "address") String address){
 companydao.setAddress(id,address);
}


}