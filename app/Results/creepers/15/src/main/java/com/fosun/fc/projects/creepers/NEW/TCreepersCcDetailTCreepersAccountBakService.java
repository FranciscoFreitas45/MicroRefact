package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.dao.CreepersCcDetailDao;
import com.fosun.fc.projects.creepers.entity.TCreepersCcDetail;
@Service
public class TCreepersCcDetailTCreepersAccountBakService {

@Autowired
 private CreepersCcDetailDao creepersccdetaildao;


public void setTCreepersCcDetails(Long id,List<TCreepersCcDetail> TCreepersCcDetails){
creepersccdetaildao.setTCreepersCcDetails(id,TCreepersCcDetails);
}


public TCreepersCcDetail removeTCreepersCcDetail(Long id,TCreepersCcDetail TCreepersCcDetail){
return creepersccdetaildao.removeTCreepersCcDetail(id,TCreepersCcDetail);
}


public List<TCreepersCcDetail> getTCreepersCcDetails(Long id){
return creepersccdetaildao.getTCreepersCcDetails(id);
}


public TCreepersCcDetail addTCreepersCcDetail(Long id,TCreepersCcDetail TCreepersCcDetail){
return creepersccdetaildao.addTCreepersCcDetail(id,TCreepersCcDetail);
}


}