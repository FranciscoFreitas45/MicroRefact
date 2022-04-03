package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaTagDao;
import com.ushahidi.swiftriver.core.model.Tag;
@Service
public class TagRiverDropTagService {

@Autowired
 private JpaTagDao jpatagdao;


public void setTag(long id3OUD,Tag tag){
jpatagdao.setTag(id3OUD,tag);
}


public Tag getTag(long id3OUD){
return jpatagdao.getTag(id3OUD);
}


}