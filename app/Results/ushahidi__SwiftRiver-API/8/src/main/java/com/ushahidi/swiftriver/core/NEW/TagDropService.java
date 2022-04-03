package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaTagDao;
import com.ushahidi.swiftriver.core.model.Tag;
@Service
public class TagDropService {

@Autowired
 private JpaTagDao jpatagdao;


public List<Tag> getTags(long id){
return jpatagdao.getTags(id);
}


public void setTags(long id,List<Tag> tags){
jpatagdao.setTags(id,tags);
}


}