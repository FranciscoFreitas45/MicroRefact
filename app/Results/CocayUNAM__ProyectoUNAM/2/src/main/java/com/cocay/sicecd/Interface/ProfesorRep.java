package com.cocay.sicecd.Interface;
public interface ProfesorRep {

   public Profesor findByCorreo(String correo);
   public List<Profesor> findAll();
}