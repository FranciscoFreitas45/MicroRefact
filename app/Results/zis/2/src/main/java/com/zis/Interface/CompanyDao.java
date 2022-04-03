package com.zis.Interface;
public interface CompanyDao {

   public Page<Company> findAllCompany(Pageable page);
   public Page<Company> findByContacts(String contacts,Pageable page);
   public Page<Company> findByLikeCompanyName(String companyName,Pageable page);
   public Company findByCompanyId(Integer companyId);
   public Company findByCompanyName(String companyName);
   public Object save(Object Object);
   public Object findAll(Object Object);
}