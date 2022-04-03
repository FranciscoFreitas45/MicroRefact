package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.LifeStyleDao;
import org.jugbd.mnet.domain.LifeStyle;
@Service
public class LifeStyleRegisterService {

@Autowired
 private LifeStyleDao lifestyledao;


public void setLifeStyle(Long id,LifeStyle lifeStyle){
lifestyledao.setLifeStyle(id,lifeStyle);
}


public LifeStyle getLifeStyle(Long id){
return lifestyledao.getLifeStyle(id);
}


}