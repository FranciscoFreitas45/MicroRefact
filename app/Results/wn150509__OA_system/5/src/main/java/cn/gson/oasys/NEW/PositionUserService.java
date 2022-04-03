package cn.gson.oasys.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.dao.user.PositionDao;
import cn.gson.oasys.model.entity.user.Position;
@Service
public class PositionUserService {

@Autowired
 private PositionDao positiondao;


public void setPosition(Long id,Position position){
positiondao.setPosition(id,position);
}


public Position getPosition(Long id){
return positiondao.getPosition(id);
}


}