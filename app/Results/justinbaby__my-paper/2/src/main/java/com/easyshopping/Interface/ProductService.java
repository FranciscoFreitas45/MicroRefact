package com.easyshopping.Interface;
public interface ProductService {

   public Object find(Object Object);
   public Page<Product> findPage(Member member,Pageable pageable);
}