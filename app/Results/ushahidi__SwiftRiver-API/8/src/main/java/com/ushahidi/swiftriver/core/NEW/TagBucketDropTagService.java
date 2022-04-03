package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaTagDao;
import com.ushahidi.swiftriver.core.model.Tag;
@Service
public class TagBucketDropTagService {

@Autowired
 private JpaTagDao jpatagdao;


public void setTag(long idQ98E,Tag tag){
jpatagdao.setTag(idQ98E,tag);
}


public Tag getTag(long idQ98E){
return jpatagdao.getTag(idQ98E);
}


}