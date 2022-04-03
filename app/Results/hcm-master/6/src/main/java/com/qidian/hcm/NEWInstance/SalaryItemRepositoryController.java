package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SalaryItemRepositoryController {

 private SalaryItemRepository salaryitemrepository;


@GetMapping
("/findByCode")
public Optional<SalaryItem> findByCode(@RequestParam(name = "code") String code){
  return salaryitemrepository.findByCode(code);
}


}