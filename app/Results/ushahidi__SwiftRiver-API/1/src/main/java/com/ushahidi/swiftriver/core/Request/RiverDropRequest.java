package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.RiverDrop;
public interface RiverDropRequest {

   public void setDrops(List<RiverDrop> drops,long id);
   public List<RiverDrop> getDrops(long id);
}