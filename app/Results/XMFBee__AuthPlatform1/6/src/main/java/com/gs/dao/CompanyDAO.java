package com.gs.dao;
 import com.gs.bean.Company;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CompanyDAO extends BaseDAO<String, Company>{


public int querycompanyWebsite(String companyWebsite,String companyId)
;

public int countByBlurred(Company company,User user)
;

public Company queryPhone(String companyPricipalphone)
;

public int updLogo(String userId,String companyLogo)
;

public int querycompanyPricipalphone(String companyPricipalphone,String companyId)
;

public int querycompanyName(String companyName,String companyId)
;

public List<Company> blurredQuery(Pager pager,Company company)
;

public List<Company> queryByCompanyInfo()
;

}