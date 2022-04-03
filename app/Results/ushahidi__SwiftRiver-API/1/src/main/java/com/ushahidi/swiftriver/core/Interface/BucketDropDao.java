package com.ushahidi.swiftriver.core.Interface;
public interface BucketDropDao {

   public Object delete(Object Object);
   public void increaseVeracity(BucketDrop bucketDrop);
   public Object create(Object Object);
   public BucketDropTag findTag(BucketDrop bucketDrop,Tag tag);
   public void addTag(BucketDrop bucketDrop,Tag tag);
   public boolean deleteTag(BucketDrop bucketDrop,Tag tag);
   public BucketDropLink findLink(BucketDrop bucketDrop,Link link);
   public void addLink(BucketDrop bucketDrop,Link link);
   public boolean deleteLink(BucketDrop bucketDrop,Link link);
   public BucketDropPlace findPlace(BucketDrop bucketDrop,Place place);
   public void addPlace(BucketDrop bucketDrop,Place place);
   public boolean deletePlace(BucketDrop bucketDrop,Place place);
   public BucketDropComment addComment(BucketDrop bucketDrop,Account account,String commentText);
   public boolean deleteComment(Long commentId);
   public BucketDropForm findForm(Long dropId,Long formId);
   public boolean isRead(BucketDrop bucketDrop,Account account);
}