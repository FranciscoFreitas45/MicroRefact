package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProvinceDaoController {

 private ProvinceDao provincedao;


@GetMapping
("/findAllProvince")
public List<AddressDto> findAllProvince(){
  return provincedao.findAllProvince();
}


}