package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.dao.CreepersCompensatoryDao;
import com.fosun.fc.projects.creepers.entity.TCreepersCompensatory;
@Service
public class TCreepersCompensatoryTCreepersAccountBakService {

@Autowired
 private CreepersCompensatoryDao creeperscompensatorydao;


public void setTCreepersCompensatories(Long id,List<TCreepersCompensatory> TCreepersCompensatories){
creeperscompensatorydao.setTCreepersCompensatories(id,TCreepersCompensatories);
}


public TCreepersCompensatory removeTCreepersCompensatory(Long id,TCreepersCompensatory TCreepersCompensatory){
return creeperscompensatorydao.removeTCreepersCompensatory(id,TCreepersCompensatory);
}


public TCreepersCompensatory addTCreepersCompensatory(Long id,TCreepersCompensatory TCreepersCompensatory){
return creeperscompensatorydao.addTCreepersCompensatory(id,TCreepersCompensatory);
}


public List<TCreepersCompensatory> getTCreepersCompensatories(Long id){
return creeperscompensatorydao.getTCreepersCompensatories(id);
}


}