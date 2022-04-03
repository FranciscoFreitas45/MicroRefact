package com.hmm.Request;
import com.hmm.DTO.InDetailed;
public interface InDetailedRequest {

   public List<InDetailed> getInDetaileds(String inStorageId);
   public void setInDetaileds(List<InDetailed> inDetaileds,String inStorageId);
}