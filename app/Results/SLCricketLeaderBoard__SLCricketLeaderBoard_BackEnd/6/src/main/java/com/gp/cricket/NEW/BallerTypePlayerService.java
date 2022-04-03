package com.gp.cricket.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.repository.BallerTypeRepository;
import com.gp.cricket.entity.BallerType;
@Service
public class BallerTypePlayerService {

@Autowired
 private BallerTypeRepository ballertyperepository;


public BallerType getBallerTypeId(Integer ballerTypeIdv2){
return ballertyperepository.getBallerTypeId(ballerTypeIdv2);
}


public void setBallerTypeId(Integer ballerTypeIdv2,BallerType ballerTypeId){
ballertyperepository.setBallerTypeId(ballerTypeIdv2,ballerTypeId);
}


}