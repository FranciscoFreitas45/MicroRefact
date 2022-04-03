package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.RiverDrop;
public interface RiverDropRequest {

   public List<RiverDrop> getReadRiverDrops(long id);
   public void setReadRiverDrops(List<RiverDrop> readRiverDrops,long id);
}