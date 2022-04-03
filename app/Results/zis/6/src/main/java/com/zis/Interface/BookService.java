package com.zis.Interface;
public interface BookService {

   public List<Bookinfo> findBySpecificationAll(Specification<Bookinfo> spec);
   public Page<Bookinfo> findAll(Pageable pageable);
}