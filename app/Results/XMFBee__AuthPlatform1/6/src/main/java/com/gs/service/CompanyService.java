package com.gs.service;
 import com.gs.bean.Company;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface CompanyService extends BaseService<String, Company>{


public int querycompanyWebsite(String companyWebsite,String companyId)
;

public Company queryPhone(String companyPricipalphone)
;

public int updLogo(String userId,String companyLogo)
;

public int querycompanyPricipalphone(String companyPricipalphone,String companyId)
;

public int querycompanyName(String companyName,String companyId)
;

public List<Company> queryByCompanyInfo()
;

}