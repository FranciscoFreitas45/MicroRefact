package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.dao.CreepersHlDetailDao;
import com.fosun.fc.projects.creepers.entity.TCreepersHlDetail;
@Service
public class TCreepersHlDetailTCreepersAccountBakService {

@Autowired
 private CreepersHlDetailDao creepershldetaildao;


public TCreepersHlDetail addTCreepersHlDetail(Long id,TCreepersHlDetail TCreepersHlDetail){
return creepershldetaildao.addTCreepersHlDetail(id,TCreepersHlDetail);
}


public List<TCreepersHlDetail> getTCreepersHlDetails(Long id){
return creepershldetaildao.getTCreepersHlDetails(id);
}


public void setTCreepersHlDetails(Long id,List<TCreepersHlDetail> TCreepersHlDetails){
creepershldetaildao.setTCreepersHlDetails(id,TCreepersHlDetails);
}


public TCreepersHlDetail removeTCreepersHlDetail(Long id,TCreepersHlDetail TCreepersHlDetail){
return creepershldetaildao.removeTCreepersHlDetail(id,TCreepersHlDetail);
}


}