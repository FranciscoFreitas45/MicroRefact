package com.zis.Interface;
public interface BookService {

   public Bookinfo findBookById(int id);
   public List<Bookinfo> findBySpecificationAll(Specification<Bookinfo> spec);
}