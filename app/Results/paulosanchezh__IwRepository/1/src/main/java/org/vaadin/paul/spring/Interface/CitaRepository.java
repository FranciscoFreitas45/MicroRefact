package org.vaadin.paul.spring.Interface;
public interface CitaRepository {

   public Object save(Object Object);
   public List<Cita> findByFechaAndSanitarioAndConfirmada(LocalDate fechaCita,Sanitario sanitario,boolean confirmada);
}