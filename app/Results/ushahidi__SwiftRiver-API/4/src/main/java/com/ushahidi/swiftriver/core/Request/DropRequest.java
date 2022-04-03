package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Drop;
public interface DropRequest {

   public void setDrop(Drop drop,long idINT8);
   public Drop getDrop(long idINT8);
}