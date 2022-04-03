package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaRiverDao;
import com.ushahidi.swiftriver.core.model.RiverDrop;
@Service
public class RiverDropAccountService {

@Autowired
 private JpaRiverDao jpariverdao;


public List<RiverDrop> getReadRiverDrops(long id){
return jpariverdao.getReadRiverDrops(id);
}


public void setReadRiverDrops(long id,List<RiverDrop> readRiverDrops){
jpariverdao.setReadRiverDrops(id,readRiverDrops);
}


}