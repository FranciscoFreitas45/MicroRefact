package com.zis.Interface;
public interface ShopService {

   public List<Company> queryAllCompany();
   public void stockChangeToShopUPdateItem(Integer companyId,Integer bookId,Integer amount);
}