package com.yalcin.Request;
import com.yalcin.DTO.Adress;
public interface AdressRequest {

   public void setAdress(Set<Adress> adress,Integer id);
   public Set<Adress> getAdress(Integer id);
}