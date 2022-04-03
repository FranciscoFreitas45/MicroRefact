package org.vaadin.paul.spring.Request;
import org.vaadin.paul.spring.DTO.Cita;
public interface CitaRequest {

   public List<Cita> getCitas(int id);
}