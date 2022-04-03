package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaRiverDao;
import com.ushahidi.swiftriver.core.model.River;
@Service
public class RiverChannelService {

@Autowired
 private JpaRiverDao jpariverdao;


public River getRiver(Long idATXO){
return jpariverdao.getRiver(idATXO);
}


public void setRiver(Long idATXO,River river){
jpariverdao.setRiver(idATXO,river);
}


}