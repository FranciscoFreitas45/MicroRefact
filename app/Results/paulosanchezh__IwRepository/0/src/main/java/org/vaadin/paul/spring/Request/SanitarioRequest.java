package org.vaadin.paul.spring.Request;
import org.vaadin.paul.spring.DTO.Sanitario;
public interface SanitarioRequest {

   public Sanitario getSanitario(int id);
   public void setSanitario(Sanitario sanitario,int id);
}