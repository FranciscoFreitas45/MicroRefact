package com.hmm.Interface;
public interface InDetailedRepository {

   public Page<InStorageDetailedDTO> findInStorageDetailedByInAll(InStorage inStorageId,Pageable pageable);
   public Object findById(Object Object);
   public Object save(Object Object);
}