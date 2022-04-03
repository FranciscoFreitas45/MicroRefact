package com.ushahidi.swiftriver.core.Interface;
public interface BucketService {

   public List<GetBucketDTO> findBuckets(String searchTerm,int count,int page);
}