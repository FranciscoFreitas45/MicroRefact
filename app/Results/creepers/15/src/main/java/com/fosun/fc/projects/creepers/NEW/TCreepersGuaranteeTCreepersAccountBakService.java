package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.dao.CreepersGuaranteeDao;
import com.fosun.fc.projects.creepers.entity.TCreepersGuarantee;
@Service
public class TCreepersGuaranteeTCreepersAccountBakService {

@Autowired
 private CreepersGuaranteeDao creepersguaranteedao;


public void setTCreepersGuarantees(Long id,List<TCreepersGuarantee> TCreepersGuarantees){
creepersguaranteedao.setTCreepersGuarantees(id,TCreepersGuarantees);
}


public TCreepersGuarantee addTCreepersGuarantee(Long id,TCreepersGuarantee TCreepersGuarantee){
return creepersguaranteedao.addTCreepersGuarantee(id,TCreepersGuarantee);
}


public List<TCreepersGuarantee> getTCreepersGuarantees(Long id){
return creepersguaranteedao.getTCreepersGuarantees(id);
}


public TCreepersGuarantee removeTCreepersGuarantee(Long id,TCreepersGuarantee TCreepersGuarantee){
return creepersguaranteedao.removeTCreepersGuarantee(id,TCreepersGuarantee);
}


}