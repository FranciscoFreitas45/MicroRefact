package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Bucket;
public interface BucketRequest {

   public List<Bucket> getCollaboratingBuckets(long id);
   public List<Bucket> getFollowingBuckets(long id);
   public void setCollaboratingBuckets(List<Bucket> collaboratingBuckets,long id);
   public void setBuckets(List<Bucket> buckets,long id);
   public List<Bucket> getBuckets(long id);
   public void setFollowingBuckets(List<Bucket> followingBuckets,long id);
}