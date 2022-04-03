package com.poseidon.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CompanyTermsServiceController {

 private CompanyTermsService companytermsservice;


@GetMapping
("/listCompanyTerms")
public Optional<CompanyTermsVO> listCompanyTerms(){
  return companytermsservice.listCompanyTerms();
}


}