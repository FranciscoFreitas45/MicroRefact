package com.cocay.sicecd.Interface;
public interface CursoRep {

   public Curso findByUniqueClaveCurso(String clave);
   public void saveC(String clave,String nombre);
}