package com.weflors.service;
 import com.weflors.entity.ContragentsEntity;
import com.weflors.repository.ContragentsRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class ContragentsServiceImpl {

 private  ContragentsRepository contragentsRepository;

public ContragentsServiceImpl(ContragentsRepository contragentsRepository) {
    this.contragentsRepository = contragentsRepository;
}
public void deleteContragent(ContragentsEntity contragentsEntity){
    contragentsRepository.deleteContragentById(contragentsEntity.getContragentId());
}


public List<ContragentsEntity> findAllContragents(){
    return contragentsRepository.findAll();
}


public Optional<ContragentsEntity> findContragentByName(String contragentName){
    return contragentsRepository.findByContragentName(contragentName);
}


public ContragentsEntity loadContragentByContragentID(Integer contragentId){
    return contragentsRepository.findByContragentID(contragentId);
}


public void updateContragent(ContragentsEntity contragentsEntity){
    contragentsRepository.updateContragentById(contragentsEntity.getAddress(), contragentsEntity.getContragentName(), contragentsEntity.getPhone1(), contragentsEntity.getPhone2(), contragentsEntity.getUnk(), contragentsEntity.getInn(), contragentsEntity.getZipCode(), contragentsEntity.getContragentId());
}


public ContragentsEntity saveContragent(ContragentsEntity contragentsEntity){
    return contragentsRepository.save(contragentsEntity);
}


}