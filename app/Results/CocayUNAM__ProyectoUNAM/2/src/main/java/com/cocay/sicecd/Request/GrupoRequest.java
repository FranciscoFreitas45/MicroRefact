package com.cocay.sicecd.Request;
import com.cocay.sicecd.DTO.Grupo;
public interface GrupoRequest {

   public void setFk_id_grupo(Grupo fk_id_grupo,int pk_id_grupo);
   public Grupo getFk_id_grupo(int pk_id_grupo);
}