package com.cocay.sicecd.Request;
import com.cocay.sicecd.DTO.Inscripcion;
public interface InscripcionRequest {

   public List<Inscripcion> getInscripciones(int pk_id_profesor);
   public void setInscripciones(List<Inscripcion> inscripciones,int pk_id_profesor);
}