package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaBucketDao;
import com.ushahidi.swiftriver.core.model.Bucket;
@Service
public class BucketBucketDropService {

@Autowired
 private JpaBucketDao jpabucketdao;


public void setBucket(long idFGI4,Bucket bucket){
jpabucketdao.setBucket(idFGI4,bucket);
}


public Bucket getBucket(long idFGI4){
return jpabucketdao.getBucket(idFGI4);
}


}