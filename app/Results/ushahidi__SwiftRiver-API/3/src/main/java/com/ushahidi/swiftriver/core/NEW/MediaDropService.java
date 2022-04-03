package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaMediaDao;
import com.ushahidi.swiftriver.core.model.Media;
@Service
public class MediaDropService {

@Autowired
 private JpaMediaDao jpamediadao;


public Media getImage(long idBTYE){
return jpamediadao.getImage(idBTYE);
}


public void setImage(long idBTYE,Media image){
jpamediadao.setImage(idBTYE,image);
}


}