package org.vaadin.paul.spring.Request;
import org.vaadin.paul.spring.DTO.Trabajador;
public interface TrabajadorRequest {

   public List<Trabajador> getTrabajadores(int id);
   public void setTrabajadores(List<Trabajador> trabajadores,int id);
}