package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaRiverDao;
import com.ushahidi.swiftriver.core.model.River;
@Service
public class RiverAccountService {

@Autowired
 private JpaRiverDao jpariverdao;


public List<River> getRivers(long id){
return jpariverdao.getRivers(id);
}


public void setFollowingRivers(long id,List<River> followingRivers){
jpariverdao.setFollowingRivers(id,followingRivers);
}


public void setCollaboratingRivers(long id,List<River> collaboratingRivers){
jpariverdao.setCollaboratingRivers(id,collaboratingRivers);
}


public void setRivers(long id,List<River> rivers){
jpariverdao.setRivers(id,rivers);
}


public List<River> getCollaboratingRivers(long id){
return jpariverdao.getCollaboratingRivers(id);
}


public List<River> getFollowingRivers(long id){
return jpariverdao.getFollowingRivers(id);
}


}