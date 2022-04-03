package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaDropDao;
import com.ushahidi.swiftriver.core.model.Drop;
@Service
public class DropIdentityService {

@Autowired
 private JpaDropDao jpadropdao;


public void setDrops(long id,List<Drop> drops){
jpadropdao.setDrops(id,drops);
}


public List<Drop> getDrops(long id){
return jpadropdao.getDrops(id);
}


}