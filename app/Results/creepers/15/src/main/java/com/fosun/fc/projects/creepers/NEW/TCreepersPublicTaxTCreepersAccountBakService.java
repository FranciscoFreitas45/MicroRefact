package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.dao.CreepersPublicTaxDao;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicTax;
@Service
public class TCreepersPublicTaxTCreepersAccountBakService {

@Autowired
 private CreepersPublicTaxDao creeperspublictaxdao;


public List<TCreepersPublicTax> getTCreepersPublicTaxs(Long id){
return creeperspublictaxdao.getTCreepersPublicTaxs(id);
}


public void setTCreepersPublicTaxs(Long id,List<TCreepersPublicTax> TCreepersPublicTaxs){
creeperspublictaxdao.setTCreepersPublicTaxs(id,TCreepersPublicTaxs);
}


public TCreepersPublicTax addTCreepersPublicTax(Long id,TCreepersPublicTax TCreepersPublicTax){
return creeperspublictaxdao.addTCreepersPublicTax(id,TCreepersPublicTax);
}


public TCreepersPublicTax removeTCreepersPublicTax(Long id,TCreepersPublicTax TCreepersPublicTax){
return creeperspublictaxdao.removeTCreepersPublicTax(id,TCreepersPublicTax);
}


}