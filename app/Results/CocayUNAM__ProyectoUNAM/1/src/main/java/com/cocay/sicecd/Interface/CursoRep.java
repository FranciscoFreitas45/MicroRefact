package com.cocay.sicecd.Interface;
public interface CursoRep {

   public List<Curso> findAll();
   public Curso findForClave(String clave);
}