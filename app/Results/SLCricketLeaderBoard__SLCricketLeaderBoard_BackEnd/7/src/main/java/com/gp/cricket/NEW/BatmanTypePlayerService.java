package com.gp.cricket.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.repository.BatmanTypeRepository;
import com.gp.cricket.entity.BatmanType;
@Service
public class BatmanTypePlayerService {

@Autowired
 private BatmanTypeRepository batmantyperepository;


public BatmanType getBatmanTypeId(Integer batmanTypeIdv2){
return batmantyperepository.getBatmanTypeId(batmanTypeIdv2);
}


public void setBatmanTypeId(Integer batmanTypeIdv2,BatmanType batmanTypeId){
batmantyperepository.setBatmanTypeId(batmanTypeIdv2,batmanTypeId);
}


}