package com.ushahidi.swiftriver.core.Interface;
public interface DropDocumentRepository {

   public Object save(Object Object);
   public Object delete(Object Object);
   public List<DropDocument> findAll(List<String> ids);
   public Object find(Object Object);
   public Object deleteAll(Object Object);
}