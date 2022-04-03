package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaBucketDao;
import com.ushahidi.swiftriver.core.model.Bucket;
@Service
public class BucketAccountService {

@Autowired
 private JpaBucketDao jpabucketdao;


public List<Bucket> getCollaboratingBuckets(long id){
return jpabucketdao.getCollaboratingBuckets(id);
}


public List<Bucket> getFollowingBuckets(long id){
return jpabucketdao.getFollowingBuckets(id);
}


public void setCollaboratingBuckets(long id,List<Bucket> collaboratingBuckets){
jpabucketdao.setCollaboratingBuckets(id,collaboratingBuckets);
}


public void setBuckets(long id,List<Bucket> buckets){
jpabucketdao.setBuckets(id,buckets);
}


public List<Bucket> getBuckets(long id){
return jpabucketdao.getBuckets(id);
}


public void setFollowingBuckets(long id,List<Bucket> followingBuckets){
jpabucketdao.setFollowingBuckets(id,followingBuckets);
}


}