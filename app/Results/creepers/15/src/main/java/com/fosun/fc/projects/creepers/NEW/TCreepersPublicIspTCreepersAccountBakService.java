package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.dao.CreepersPublicIspDao;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicIsp;
@Service
public class TCreepersPublicIspTCreepersAccountBakService {

@Autowired
 private CreepersPublicIspDao creeperspublicispdao;


public TCreepersPublicIsp addTCreepersPublicIsp(Long id,TCreepersPublicIsp TCreepersPublicIsp){
return creeperspublicispdao.addTCreepersPublicIsp(id,TCreepersPublicIsp);
}


public List<TCreepersPublicIsp> getTCreepersPublicIsps(Long id){
return creeperspublicispdao.getTCreepersPublicIsps(id);
}


public TCreepersPublicIsp removeTCreepersPublicIsp(Long id,TCreepersPublicIsp TCreepersPublicIsp){
return creeperspublicispdao.removeTCreepersPublicIsp(id,TCreepersPublicIsp);
}


public void setTCreepersPublicIsps(Long id,List<TCreepersPublicIsp> TCreepersPublicIsps){
creeperspublicispdao.setTCreepersPublicIsps(id,TCreepersPublicIsps);
}


}