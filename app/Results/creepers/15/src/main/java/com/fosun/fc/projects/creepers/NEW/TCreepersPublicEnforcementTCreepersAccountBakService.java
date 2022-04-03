package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.dao.CreepersPublicEnforcementDao;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicEnforcement;
@Service
public class TCreepersPublicEnforcementTCreepersAccountBakService {

@Autowired
 private CreepersPublicEnforcementDao creeperspublicenforcementdao;


public void setTCreepersPublicEnforcements(Long id,List<TCreepersPublicEnforcement> TCreepersPublicEnforcements){
creeperspublicenforcementdao.setTCreepersPublicEnforcements(id,TCreepersPublicEnforcements);
}


public List<TCreepersPublicEnforcement> getTCreepersPublicEnforcements(Long id){
return creeperspublicenforcementdao.getTCreepersPublicEnforcements(id);
}


public TCreepersPublicEnforcement removeTCreepersPublicEnforcement(Long id,TCreepersPublicEnforcement TCreepersPublicEnforcement){
return creeperspublicenforcementdao.removeTCreepersPublicEnforcement(id,TCreepersPublicEnforcement);
}


public TCreepersPublicEnforcement addTCreepersPublicEnforcement(Long id,TCreepersPublicEnforcement TCreepersPublicEnforcement){
return creeperspublicenforcementdao.addTCreepersPublicEnforcement(id,TCreepersPublicEnforcement);
}


}