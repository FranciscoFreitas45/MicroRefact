package cn.gson.oasys.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.dao.attendcedao.AttendceDao;
import cn.gson.oasys.model.entity.attendce.Attends;
@Service
public class AttendsUserService {

@Autowired
 private AttendceDao attendcedao;


public Set<Attends> getaSet(Long userId){
return attendcedao.getaSet(userId);
}


public void setaSet(Long userId,Set<Attends> aSet){
attendcedao.setaSet(userId,aSet);
}


}