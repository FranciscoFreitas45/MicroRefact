package com.fzshuai.Interface;
public interface BlogRepository {

   public Object findById(Object Object);
   public Object findAll(Object Object);
   public List<Blog> findTop(Pageable pageable);
   public Object save(Object Object);
   public Object deleteById(Object Object);
}