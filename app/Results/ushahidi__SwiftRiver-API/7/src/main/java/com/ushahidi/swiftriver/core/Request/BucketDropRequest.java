package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.BucketDrop;
public interface BucketDropRequest {

   public List<BucketDrop> getReadBucketDrops(long id);
   public void setReadBucketDrops(List<BucketDrop> readBucketDrops,long id);
}