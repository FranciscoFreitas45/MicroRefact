package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.dao.CreepersAccountBakDao;
import com.fosun.fc.projects.creepers.entity.TCreepersAccountBak;
@Service
public class TCreepersAccountBakTCreepersCompensatoryService {

@Autowired
 private CreepersAccountBakDao creepersaccountbakdao;


public void setTCreepersAccountBak(Long id,TCreepersAccountBak TCreepersAccountBak){
creepersaccountbakdao.setTCreepersAccountBak(id,TCreepersAccountBak);
}


public TCreepersAccountBak getTCreepersAccountBak(Long id){
return creepersaccountbakdao.getTCreepersAccountBak(id);
}


}