package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaRiverDao;
import com.ushahidi.swiftriver.core.model.River;
@Service
public class RiverRuleService {

@Autowired
 private JpaRiverDao jpariverdao;


public River getRiver(Long idNMZI){
return jpariverdao.getRiver(idNMZI);
}


public void setRiver(Long idNMZI,River river){
jpariverdao.setRiver(idNMZI,river);
}


}