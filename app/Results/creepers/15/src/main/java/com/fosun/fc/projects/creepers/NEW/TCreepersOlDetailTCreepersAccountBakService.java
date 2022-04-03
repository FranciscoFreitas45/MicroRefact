package com.fosun.fc.projects.creepers.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.dao.CreepersOlDetailDao;
import com.fosun.fc.projects.creepers.entity.TCreepersOlDetail;
@Service
public class TCreepersOlDetailTCreepersAccountBakService {

@Autowired
 private CreepersOlDetailDao creepersoldetaildao;


public List<TCreepersOlDetail> getTCreepersOlDetails(Long id){
return creepersoldetaildao.getTCreepersOlDetails(id);
}


public TCreepersOlDetail removeTCreepersOlDetail(Long id,TCreepersOlDetail TCreepersOlDetail){
return creepersoldetaildao.removeTCreepersOlDetail(id,TCreepersOlDetail);
}


public void setTCreepersOlDetails(Long id,List<TCreepersOlDetail> TCreepersOlDetails){
creepersoldetaildao.setTCreepersOlDetails(id,TCreepersOlDetails);
}


public TCreepersOlDetail addTCreepersOlDetail(Long id,TCreepersOlDetail TCreepersOlDetail){
return creepersoldetaildao.addTCreepersOlDetail(id,TCreepersOlDetail);
}


}