package com.cocay.sicecd.Interface;
public interface ProfesorRep {

   public List<Profesor> findAll();
   public Profesor findByRfc(String rfc);
}