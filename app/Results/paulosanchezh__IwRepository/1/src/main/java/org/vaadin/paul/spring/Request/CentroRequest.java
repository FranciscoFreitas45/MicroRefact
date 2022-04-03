package org.vaadin.paul.spring.Request;
import org.vaadin.paul.spring.DTO.Centro;
public interface CentroRequest {

   public Centro getCentro(int id);
   public void setCentro(Centro centro,int id);
}