package com.zis.shop.repository;
 import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.shop.bean.Company;
public interface CompanyDao extends PagingAndSortingRepository<Company, Integer>{

 final String NORMAL = "normal";
	final String DELETE = "delete";

@Query(value = "SELECT cp FROM Company cp WHERE " + "cp.companyName LIKE %:companyName% AND cp.companyId <> 0 AND cp.status = '" + NORMAL + "'")
public Page<Company> findByLikeCompanyName(String companyName,Pageable page)
;

@Query(value = "SELECT cp FROM Company cp WHERE " + "cp.contacts = :contacts AND cp.companyId <> 0 AND cp.status = '" + NORMAL + "'")
public Page<Company> findByContacts(String contacts,Pageable page)
;

@Query(value = "FROM Company WHERE companyId <> 0 AND companyId = :companyId AND status = '" + NORMAL + "'")
public Company findByCompanyId(Integer companyId)
;

@Query(value = "FROM Company WHERE companyId <> 0 AND status = '" + NORMAL + "'")
public List<Company> findAllByStatusIsNormal()
;

@Query(value = "FROM Company WHERE companyId = :companyId AND status = '" + NORMAL + "'")
public Company findBySysCompanyId(Integer companyId)
;

@Query(value = "FROM Company WHERE companyName = :companyName AND companyId <> 0 AND status = '" + NORMAL + "'")
public Company findByCompanyName(String companyName)
;

@Query(value = "SELECT cp FROM Company cp WHERE cp.companyId <> 0 AND cp.status = '" + NORMAL + "'")
public Page<Company> findAllCompany(Pageable page)
;

public void setUpdateTime(Integer id,Date updateTime);

public void setStatus(Integer id,String status);

public void setAddress(Integer id,String address);

}