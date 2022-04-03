package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaDropDao;
import com.ushahidi.swiftriver.core.model.Drop;
@Service
public class DropBucketDropService {

@Autowired
 private JpaDropDao jpadropdao;


public void setDrop(long idINT8,Drop drop){
jpadropdao.setDrop(idINT8,drop);
}


public Drop getDrop(long idINT8){
return jpadropdao.getDrop(idINT8);
}


}