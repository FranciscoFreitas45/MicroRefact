package com.zis.Interface;
import org.springframework.data.domain.Pageable;
import com.zis.shop.bean.Company;
import com.zis.shop.dto.SaveOrUpdateCompanyDto;
import com.zis.DTO.ShopInfo;
import java.util.*;
import org.springframework.data.domain.Page;


public interface ShopService {

   public Page<Company> queryCompany(String companyName,String contacts,Pageable page);
   public Company findCompanyOne(Integer companyId);
   public void updateCompany(SaveOrUpdateCompanyDto dto);
   public void saveCompany(SaveOrUpdateCompanyDto dto);
   public List<ShopInfo> findCompanyShop(Integer companyId);
}