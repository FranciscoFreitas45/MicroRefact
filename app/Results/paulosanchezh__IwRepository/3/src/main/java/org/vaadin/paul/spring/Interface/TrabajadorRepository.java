package org.vaadin.paul.spring.Interface;
public interface TrabajadorRepository {

   public Object save(Object Object);
   public List<Trabajador> findByTrabajadoresNulos();
}