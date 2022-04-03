package com.poseidon.make.dao.mapper;
 import com.poseidon.make.dao.entities.Make;
import com.poseidon.make.dao.entities.Model;
import com.poseidon.make.domain.MakeAndModelVO;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class MakeAndModelEntityConverter {


public List<MakeAndModelVO> convertModelsToMakeAndModelVOs(List<Model> models){
    return models.stream().map(this::createMakeAndModelVO).toList();
}


public MakeAndModelVO createMakeAndModelVO(Make make){
    var makeAndModelVO = new MakeAndModelVO();
    makeAndModelVO.setMakeId(make.getId());
    makeAndModelVO.setMakeName(make.getMakeName());
    makeAndModelVO.setDescription(make.getDescription());
    makeAndModelVO.setCreatedBy(make.getCreatedBy());
    makeAndModelVO.setModifiedBy(make.getModifiedBy());
    return makeAndModelVO;
}


public Model convertMakeAndModelVOToModel(MakeAndModelVO makeAndModelVO){
    var model = new Model();
    model.setModelName(makeAndModelVO.getModelName());
    model.setMakeId(makeAndModelVO.getMakeId());
    model.setModelId(makeAndModelVO.getMakeId());
    model.setCreatedBy(makeAndModelVO.getCreatedBy());
    model.setModifiedBy(makeAndModelVO.getModifiedBy());
    return model;
}


public List<MakeAndModelVO> convertMakeToMakeAndModelVOs(List<Make> makes){
    return makes.stream().map(this::createMakeAndModelVO).toList();
}


public Make convertToMake(MakeAndModelVO currentMakeVO){
    var make = new Make();
    make.setId(currentMakeVO.getMakeId());
    make.setMakeName(currentMakeVO.getMakeName());
    make.setDescription(currentMakeVO.getDescription());
    make.setModifiedBy(currentMakeVO.getModifiedBy());
    return make;
}


public MakeAndModelVO getMakeVOFromMake(Make make){
    var makeAndModelVO = new MakeAndModelVO();
    makeAndModelVO.setMakeId(make.getId());
    makeAndModelVO.setMakeName(make.getMakeName());
    makeAndModelVO.setDescription(make.getDescription());
    return makeAndModelVO;
}


public MakeAndModelVO convertModelToMakeAndModelVO(Model model){
    var makeAndModelVO = new MakeAndModelVO();
    makeAndModelVO.setModelId(model.getModelId());
    makeAndModelVO.setModelName(model.getModelName());
    makeAndModelVO.setMakeId(model.getMake().getId());
    makeAndModelVO.setMakeName(model.getMake().getMakeName());
    return makeAndModelVO;
}


}