package com.zis.Interface;
public interface BookService {

   public Page<Bookinfo> findAll(Pageable pageable);
}