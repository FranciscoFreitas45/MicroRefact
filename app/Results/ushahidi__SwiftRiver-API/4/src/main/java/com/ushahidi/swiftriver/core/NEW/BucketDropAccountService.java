package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaBucketDropDao;
import com.ushahidi.swiftriver.core.model.BucketDrop;
@Service
public class BucketDropAccountService {

@Autowired
 private JpaBucketDropDao jpabucketdropdao;


public List<BucketDrop> getReadBucketDrops(long id){
return jpabucketdropdao.getReadBucketDrops(id);
}


public void setReadBucketDrops(long id,List<BucketDrop> readBucketDrops){
jpabucketdropdao.setReadBucketDrops(id,readBucketDrops);
}


}