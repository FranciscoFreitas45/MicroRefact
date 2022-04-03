package com.ushahidi.swiftriver.core.api.dao;
 import java.util.List;
import com.ushahidi.swiftriver.core.api.filter.DropFilter;
import com.ushahidi.swiftriver.core.model.Account;
import com.ushahidi.swiftriver.core.model.Bucket;
import com.ushahidi.swiftriver.core.model.BucketCollaborator;
import com.ushahidi.swiftriver.core.model.BucketComment;
import com.ushahidi.swiftriver.core.model.BucketDrop;
import com.ushahidi.swiftriver.core.model.Drop;
public interface BucketDao extends GenericDao<Bucket>{


public void decreaseDropCount(Bucket bucket)
;

public Bucket findBucketByName(Account account,String bucketName)
;

public void increaseDropCount(Bucket bucket)
;

public List<Bucket> findAll(String searchTerm,int count,int page)
;

public BucketCollaborator addCollaborator(Bucket bucket,Account account,boolean readOnly)
;

public void updateCollaborator(BucketCollaborator collaborator)
;

public void deleteCollaborator(BucketCollaborator collaborator)
;

public boolean addDrop(Bucket bucket,Drop drop)
;

public BucketCollaborator findCollaborator(Long bucketId,Long accountId)
;

public BucketDrop findDrop(Long bucketId,Long dropId)
;

public List<Drop> getDrops(Long bucketId,DropFilter filter,int page,int dropCount,Account queryingAccount)
;

public BucketDrop findBucketDrop(Long bucketId,Long dropId)
;

public boolean deleteDrop(Long id,Long dropId)
;

public BucketComment addComment(Bucket bucket,String commentText,Account account)
;

}