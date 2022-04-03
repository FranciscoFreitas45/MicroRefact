package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Bucket;
public interface BucketRequest {

   public void setBucket(Bucket bucket,long idFGI4);
   public Bucket getBucket(long idFGI4);
}