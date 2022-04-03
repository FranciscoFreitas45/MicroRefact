package com.cocay.sicecd.Interface;
public interface GrupoRep {

   public Grupo findByClaveGrupoIdCurso(String clave,Curso curso);
   public void saveC(String clave,Integer fk_id_curso);
}