package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaRiverDao;
import com.ushahidi.swiftriver.core.model.River;
@Service
public class RiverRiverActivityService {

@Autowired
 private JpaRiverDao jpariverdao;


public River getActionOnObj(Long id37M8){
return jpariverdao.getActionOnObj(id37M8);
}


public void setActionOnObj(Long id37M8,River actionOnObj){
jpariverdao.setActionOnObj(id37M8,actionOnObj);
}


}