package org.vaadin.paul.spring.Interface;
public interface CentroRepository {

   public List<Centro> findByLocalidad(Localidad Localidad);
}