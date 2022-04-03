package com.ushahidi.swiftriver.core.Interface;
public interface BucketDao {

   public Bucket findBucketByName(Account account,String bucketName);
   public Object create(Object Object);
   public Object findById(Object Object);
   public Object update(Object Object);
   public Object delete(Object Object);
   public BucketCollaborator findCollaborator(Long bucketId,Long accountId);
   public BucketCollaborator addCollaborator(Bucket bucket,Account account,boolean readOnly);
   public void updateCollaborator(BucketCollaborator collaborator);
   public List<Drop> getDrops(Long bucketId,DropFilter filter,int page,int dropCount,Account queryingAccount);
   public void decreaseDropCount(Bucket bucket);
   public BucketDrop findBucketDrop(Long bucketId,Long dropId);
   public void increaseDropCount(Bucket bucket);
   public BucketComment addComment(Bucket bucket,String commentText,Account account);
   public List<Bucket> findAll(String searchTerm,int count,int page);
}