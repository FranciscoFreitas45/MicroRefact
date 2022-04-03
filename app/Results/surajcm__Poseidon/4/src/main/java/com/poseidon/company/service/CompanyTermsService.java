package com.poseidon.company.service;
 import com.poseidon.company.dao.CompanyTermsDAO;
import com.poseidon.company.domain.CompanyTermsVO;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class CompanyTermsService {

 private  CompanyTermsDAO companyTermsDAO;

public CompanyTermsService(final CompanyTermsDAO companyTermsDAO) {
    this.companyTermsDAO = companyTermsDAO;
}
public Optional<CompanyTermsVO> updateCompanyDetails(CompanyTermsVO companyTermsVO){
    return companyTermsDAO.updateCompanyDetails(companyTermsVO);
}


public Optional<CompanyTermsVO> listCompanyTerms(){
    return companyTermsDAO.listCompanyTerms();
}


}