package com.gp.cricket.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.BallerType;
import com.gp.cricket.repository.BallerTypeRepository;
@Service
public class BallerTypeService {

@Autowired
 private BallerTypeRepository ballerTypeRepository;


public List<BallerType> getBallerTypeList(){
    return ballerTypeRepository.findAll();
}


}