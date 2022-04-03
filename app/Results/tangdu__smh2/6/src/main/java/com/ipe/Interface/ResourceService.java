package com.ipe.Interface;
public interface ResourceService {

   public Object findAll(Object Object);
   public Object get(Object Object);
   public Object save(Object Object);
   public Resource saveResource(Resource resource);
   public Object delete(Object Object);
   public List<Resource> getResources(String pid);
}