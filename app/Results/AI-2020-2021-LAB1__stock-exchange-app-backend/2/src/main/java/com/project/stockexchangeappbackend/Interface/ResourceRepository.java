package com.project.stockexchangeappbackend.Interface;
public interface ResourceRepository {

   public Page<Resource> findAll(Specification<Resource> var1,Pageable var2);
   public Optional<Resource> findByUserAndStock(User user,Stock stock);
   public Object delete(Object Object);
   public S save(S s);
}