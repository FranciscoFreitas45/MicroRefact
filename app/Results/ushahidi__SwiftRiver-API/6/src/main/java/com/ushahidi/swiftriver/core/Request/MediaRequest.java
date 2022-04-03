package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Media;
public interface MediaRequest {

   public Media getImage(long idBTYE);
   public void setImage(Media image,long idBTYE);
}