package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.dao.CreepersBasicDao;
import com.fosun.fc.projects.creepers.entity.TCreepersBasic;
@Service
public class TCreepersBasicTCreepersAccountBakService {

@Autowired
 private CreepersBasicDao creepersbasicdao;


public List<TCreepersBasic> getTCreepersBasics(Long id){
return creepersbasicdao.getTCreepersBasics(id);
}


public TCreepersBasic addTCreepersBasic(Long id,TCreepersBasic TCreepersBasic){
return creepersbasicdao.addTCreepersBasic(id,TCreepersBasic);
}


public void setTCreepersBasics(Long id,List<TCreepersBasic> TCreepersBasics){
creepersbasicdao.setTCreepersBasics(id,TCreepersBasics);
}


public TCreepersBasic removeTCreepersBasic(Long id,TCreepersBasic TCreepersBasic){
return creepersbasicdao.removeTCreepersBasic(id,TCreepersBasic);
}


}