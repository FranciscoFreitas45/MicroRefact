package com.fzshuai.Interface;
public interface TagRepository {

   public Object save(Object Object);
   public Object findById(Object Object);
   public Tag findByName(String name);
   public Object findAll(Object Object);
   public List<Tag> findTop(Pageable pageable);
   public Object findAllById(Object Object);
   public Object deleteById(Object Object);
}