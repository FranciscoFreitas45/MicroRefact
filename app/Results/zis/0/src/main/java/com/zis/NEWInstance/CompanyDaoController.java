package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
  import com.zis.shop.repository.CompanyDao;
  import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.zis.shop.bean.Company;
import com.zis.shop.repository.CompanyDao;
@RestController
@CrossOrigin
public class CompanyDaoController {

 private CompanyDao companydao;


@GetMapping
("/findAllCompany")
public Page<Company> findAllCompany(@RequestParam(name = "page") Pageable page){
  return companydao.findAllCompany(page);
}


@GetMapping
("/findByContacts")
public Page<Company> findByContacts(@RequestParam(name = "contacts") String contacts,@RequestParam(name = "page") Pageable page){
  return companydao.findByContacts(contacts,page);
}


@GetMapping
("/findByLikeCompanyName")
public Page<Company> findByLikeCompanyName(@RequestParam(name = "companyName") String companyName,@RequestParam(name = "page") Pageable page){
  return companydao.findByLikeCompanyName(companyName,page);
}


@GetMapping
("/findByCompanyId")
public Company findByCompanyId(@RequestParam(name = "companyId") Integer companyId){
  return companydao.findByCompanyId(companyId);
}


@GetMapping
("/findByCompanyName")
public Company findByCompanyName(@RequestParam(name = "companyName") String companyName){
  return companydao.findByCompanyName(companyName);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return companydao.save(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return companydao.findAll(Object);
}


}