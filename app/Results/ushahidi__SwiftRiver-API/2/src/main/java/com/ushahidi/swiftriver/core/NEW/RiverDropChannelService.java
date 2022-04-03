package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaRiverDao;
import com.ushahidi.swiftriver.core.model.RiverDrop;
@Service
public class RiverDropChannelService {

@Autowired
 private JpaRiverDao jpariverdao;


public void setDrops(long id,List<RiverDrop> drops){
jpariverdao.setDrops(id,drops);
}


public List<RiverDrop> getDrops(long id){
return jpariverdao.getDrops(id);
}


}