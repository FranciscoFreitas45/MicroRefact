package com.hmm.Request;
import com.hmm.DTO.InStorage;
public interface InStorageRequest {

   public void setInAll(InStorage inAll,String inStorageIdEAHF);
   public InStorage getInAll(String inStorageIdEAHF);
}