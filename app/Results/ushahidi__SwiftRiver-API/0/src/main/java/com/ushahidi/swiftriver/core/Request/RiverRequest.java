package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.River;
public interface RiverRequest {

   public River getRiver(Long idNMZI);
   public void setRiver(River river,Long idNMZI);
}