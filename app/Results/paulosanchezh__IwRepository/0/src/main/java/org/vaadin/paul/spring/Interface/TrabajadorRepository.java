package org.vaadin.paul.spring.Interface;
public interface TrabajadorRepository {

   public Trabajador findByUser(User usuario);
}