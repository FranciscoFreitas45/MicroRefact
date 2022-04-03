package com.cocay.sicecd.Request;
import com.cocay.sicecd.DTO.Curso;
public interface CursoRequest {

   public Curso getFk_id_curso(int pk_id_curso);
   public void setFk_id_curso(Curso curso,int pk_id_curso);
}