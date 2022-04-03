package com.cocay.sicecd.Request;
import com.cocay.sicecd.DTO.Profesor;
public interface ProfesorRequest {

   public Profesor getFk_id_profesor(int pk_id_profesor);
   public void setFk_id_profesor(Profesor fk_id_profesor,int pk_id_profesor);
}