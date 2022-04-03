package org.vaadin.paul.spring.Interface;
public interface Trabajador {

   public String getNombre();
   public int getId();
   public User getUser();
   public LocalTime getHoraInicio();
   public LocalTime getHoraFinal();
   public Object compareTo(Object Object);
}