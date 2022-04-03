package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.dao.CreepersPublicSanctionDao;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicSanction;
@Service
public class TCreepersPublicSanctionTCreepersAccountBakService {

@Autowired
 private CreepersPublicSanctionDao creeperspublicsanctiondao;


public void setTCreepersPublicSanctions(Long id,List<TCreepersPublicSanction> TCreepersPublicSanctions){
creeperspublicsanctiondao.setTCreepersPublicSanctions(id,TCreepersPublicSanctions);
}


public List<TCreepersPublicSanction> getTCreepersPublicSanctions(Long id){
return creeperspublicsanctiondao.getTCreepersPublicSanctions(id);
}


public TCreepersPublicSanction removeTCreepersPublicSanction(Long id,TCreepersPublicSanction TCreepersPublicSanction){
return creeperspublicsanctiondao.removeTCreepersPublicSanction(id,TCreepersPublicSanction);
}


public TCreepersPublicSanction addTCreepersPublicSanction(Long id,TCreepersPublicSanction TCreepersPublicSanction){
return creeperspublicsanctiondao.addTCreepersPublicSanction(id,TCreepersPublicSanction);
}


}