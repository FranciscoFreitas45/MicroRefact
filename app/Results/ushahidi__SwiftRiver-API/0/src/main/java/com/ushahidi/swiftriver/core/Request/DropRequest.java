package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Drop;
public interface DropRequest {

   public void setDrops(List<Drop> drops,long id);
   public List<Drop> getDrops(long id);
}