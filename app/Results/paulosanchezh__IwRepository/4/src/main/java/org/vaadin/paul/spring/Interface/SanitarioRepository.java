package org.vaadin.paul.spring.Interface;
public interface SanitarioRepository {

   public Sanitario findByTrabajadorId(int id);
   public Sanitario findByTrabajador(Trabajador trabajador);
}