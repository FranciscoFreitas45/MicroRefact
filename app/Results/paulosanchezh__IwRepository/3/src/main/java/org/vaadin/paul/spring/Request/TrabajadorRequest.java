package org.vaadin.paul.spring.Request;
import org.vaadin.paul.spring.DTO.Trabajador;
public interface TrabajadorRequest {

   public void setTrabajador(Trabajador trabajador,int id);
   public Trabajador getTrabajador(int id);
}