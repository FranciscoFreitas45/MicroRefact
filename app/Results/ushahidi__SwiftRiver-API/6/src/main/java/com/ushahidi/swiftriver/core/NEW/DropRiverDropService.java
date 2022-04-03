package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaDropDao;
import com.ushahidi.swiftriver.core.model.Drop;
@Service
public class DropRiverDropService {

@Autowired
 private JpaDropDao jpadropdao;


public void setDrop(long id2DXZ,Drop drop){
jpadropdao.setDrop(id2DXZ,drop);
}


public Drop getDrop(long id2DXZ){
return jpadropdao.getDrop(id2DXZ);
}


}