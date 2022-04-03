package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.dao.CreepersPublicCivilDao;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicCivil;
@Service
public class TCreepersPublicCivilTCreepersAccountBakService {

@Autowired
 private CreepersPublicCivilDao creeperspubliccivildao;


public TCreepersPublicCivil addTCreepersPublicCivil(Long id,TCreepersPublicCivil TCreepersPublicCivil){
return creeperspubliccivildao.addTCreepersPublicCivil(id,TCreepersPublicCivil);
}


public TCreepersPublicCivil removeTCreepersPublicCivil(Long id,TCreepersPublicCivil TCreepersPublicCivil){
return creeperspubliccivildao.removeTCreepersPublicCivil(id,TCreepersPublicCivil);
}


public List<TCreepersPublicCivil> getTCreepersPublicCivils(Long id){
return creeperspubliccivildao.getTCreepersPublicCivils(id);
}


public void setTCreepersPublicCivils(Long id,List<TCreepersPublicCivil> TCreepersPublicCivils){
creeperspubliccivildao.setTCreepersPublicCivils(id,TCreepersPublicCivils);
}


}