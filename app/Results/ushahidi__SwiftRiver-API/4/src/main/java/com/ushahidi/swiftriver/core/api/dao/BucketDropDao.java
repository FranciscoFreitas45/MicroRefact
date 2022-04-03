package com.ushahidi.swiftriver.core.api.dao;
 import com.ushahidi.swiftriver.core.model.Account;
import com.ushahidi.swiftriver.core.model.Bucket;
import com.ushahidi.swiftriver.core.model.BucketDrop;
import com.ushahidi.swiftriver.core.model.BucketDropComment;
import com.ushahidi.swiftriver.core.model.BucketDropForm;
import com.ushahidi.swiftriver.core.model.BucketDropLink;
import com.ushahidi.swiftriver.core.model.BucketDropPlace;
import com.ushahidi.swiftriver.core.model.BucketDropTag;
import com.ushahidi.swiftriver.core.model.Link;
import com.ushahidi.swiftriver.core.model.Place;
import com.ushahidi.swiftriver.core.model.Tag;
public interface BucketDropDao extends ContextDropDao, GenericDao<BucketDrop>{


public BucketDropForm findForm(Long dropId,Long formId)
;

public BucketDropTag findTag(BucketDrop bucketDrop,Tag tag)
;

public BucketDropPlace findPlace(BucketDrop bucketDrop,Place place)
;

public boolean deleteComment(Long commentId)
;

public boolean isRead(BucketDrop bucketDrop,Account account)
;

public void addTag(BucketDrop bucketDrop,Tag tag)
;

public boolean deleteLink(BucketDrop bucketDrop,Link link)
;

public boolean deleteTag(BucketDrop bucketDrop,Tag tag)
;

public BucketDropLink findLink(BucketDrop bucketDrop,Link link)
;

public void increaseVeracity(BucketDrop bucketDrop)
;

public boolean deletePlace(BucketDrop bucketDrop,Place place)
;

public void addPlace(BucketDrop bucketDrop,Place place)
;

public void addLink(BucketDrop bucketDrop,Link link)
;

public BucketDropComment addComment(BucketDrop bucketDrop,Account account,String commentText)
;

}