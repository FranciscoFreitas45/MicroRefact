package org.vaadin.paul.spring.Interface;
public interface CentroRepository {

   public Object findAll(Object Object);
   public Object save(Object Object);
   public Centro findByName(String nombre,int id);
   public Object delete(Object Object);
   public Centro findBynombre(String nombre);
}