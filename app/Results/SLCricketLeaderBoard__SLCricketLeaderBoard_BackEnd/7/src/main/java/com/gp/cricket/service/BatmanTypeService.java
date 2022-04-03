package com.gp.cricket.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.BatmanType;
import com.gp.cricket.repository.BatmanTypeRepository;
@Service
public class BatmanTypeService {

@Autowired
 private BatmanTypeRepository batmanTypeRepository;


public List<BatmanType> getBatmanTypeList(){
    return batmanTypeRepository.findAll();
}


}