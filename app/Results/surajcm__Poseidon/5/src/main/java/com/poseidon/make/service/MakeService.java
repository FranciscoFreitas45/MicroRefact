package com.poseidon.make.service;
 import com.poseidon.make.dao.MakeDao;
import com.poseidon.make.domain.MakeAndModelVO;
import com.poseidon.make.domain.MakeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import com.poseidon.Interface.MakeDao;
@Service
@SuppressWarnings("unused")
public class MakeService {

 private  String MAKE_EXCEPTION_OCCURRED;

 private  Logger LOG;

 private  MakeDao makeDAO;

public MakeService(final MakeDao makeDAO) {
    this.makeDAO = makeDAO;
}
public List<MakeAndModelVO> listAllMakes(){
    return makeDAO.listAllMakes();
}


public List<MakeAndModelVO> listAllMakesAndModels(){
    return makeDAO.listAllMakesAndModels();
}


public void addNewModel(MakeAndModelVO currentMakeVO){
    makeDAO.addNewModel(currentMakeVO);
}


public void updateMake(MakeAndModelVO currentMakeVO){
    makeDAO.updateMake(currentMakeVO);
}


public void updateModel(Long id,Long makeId,String modalModelName){
    makeDAO.updateModel(id, makeId, modalModelName);
}


public void deleteMake(Long makeId){
    makeDAO.deleteMake(makeId);
}


public MakeAndModelVO getModelFromId(Long modelId){
    return makeDAO.getModelFromId(modelId);
}


public List<MakeAndModelVO> getAllModelsFromMakeId(Long id){
    return makeDAO.getAllModelsFromMakeId(id);
}


public void addNewMake(MakeAndModelVO currentMakeVO){
    makeDAO.addNewMake(currentMakeVO);
}


public void deleteModel(Long modelId){
    makeDAO.deleteModel(modelId);
}


public List<MakeAndModelVO> searchMakeVOs(MakeAndModelVO searchMakeVO){
    return makeDAO.searchMakeVOs(searchMakeVO);
}


public MakeAndModelVO getMakeFromId(Long makeId){
    return makeDAO.getMakeFromId(makeId);
}


public List<MakeVO> fetchMakes(){
    return makeDAO.fetchMakes();
}


}