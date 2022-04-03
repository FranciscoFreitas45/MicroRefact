package com.poseidon.make.dao;
 import com.poseidon.make.dao.entities.Make;
import com.poseidon.make.dao.entities.Model;
import com.poseidon.make.dao.mapper.MakeAndModelEntityConverter;
import com.poseidon.make.dao.repo.MakeRepository;
import com.poseidon.make.dao.repo.ModelRepository;
import com.poseidon.make.domain.MakeAndModelVO;
import com.poseidon.make.domain.MakeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import com.rainerhahnekamp.sneakythrow.Sneaky.sneak;
import com.rainerhahnekamp.sneakythrow.Sneaky.sneaked;
import com.poseidon.DTO.MakeAndModelVO;
@Service
@SuppressWarnings("unused")
public class MakeDao {

 private  Logger LOG;

 private  MakeRepository makeRepository;

 private  ModelRepository modelRepository;

 private  MakeAndModelEntityConverter makeAndModelEntityConverter;

public MakeDao(final MakeRepository makeRepository, final ModelRepository modelRepository, final MakeAndModelEntityConverter makeAndModelEntityConverter) {
    this.makeRepository = makeRepository;
    this.modelRepository = modelRepository;
    this.makeAndModelEntityConverter = makeAndModelEntityConverter;
}
public List<MakeAndModelVO> listAllMakes(){
    var makes = sneak(makeRepository::findAll);
    return makeAndModelEntityConverter.convertMakeToMakeAndModelVOs(makes);
}


public List<MakeAndModelVO> listAllMakesAndModels(){
    var models = sneak(modelRepository::findAll);
    // todo: better MakeAndModelVO to render things in a better way
    return makeAndModelEntityConverter.convertModelsToMakeAndModelVOs(models);
}


public List<Model> getModels(MakeAndModelVO searchMakeVO,String modelName){
    List<Model> models;
    if (Boolean.TRUE.equals(searchMakeVO.getIncludes())) {
        models = modelRepository.findByModelNameWildCard("%" + modelName + "%");
    } else if (Boolean.TRUE.equals(searchMakeVO.getStartswith())) {
        models = modelRepository.findByModelNameWildCard(modelName + "%");
    } else {
        models = modelRepository.findByModelName(modelName);
    }
    return models;
}


public MakeAndModelVO getMakeAndModelVO(Make make,Model model){
    var makeAndModelVO = new MakeAndModelVO();
    makeAndModelVO.setModelId(model.getModelId());
    makeAndModelVO.setModelName(model.getModelName());
    makeAndModelVO.setMakeId(make.getId());
    makeAndModelVO.setMakeName(make.getMakeName());
    return makeAndModelVO;
}


public void addNewModel(MakeAndModelVO currentMakeVo){
    var model = makeAndModelEntityConverter.convertMakeAndModelVOToModel(currentMakeVo);
    var model1 = updateModelWithMake(model);
    sneak(() -> modelRepository.save(model1));
}


public void updateMake(MakeAndModelVO currentMakeVo){
    var make = makeAndModelEntityConverter.convertToMake(currentMakeVo);
    var optionalMake = sneak(() -> makeRepository.findById(currentMakeVo.getMakeId()));
    if (optionalMake.isPresent()) {
        var newMake = optionalMake.get();
        newMake.setMakeName(make.getMakeName());
        newMake.setDescription(make.getDescription());
        newMake.setModifiedBy(make.getModifiedBy());
        sneak(() -> makeRepository.save(newMake));
    }
}


public void updateModel(Long id,Long makeId,String modalModelName){
    var optionalModel = sneak(() -> modelRepository.findById(id));
    if (optionalModel.isPresent()) {
        var model = optionalModel.get();
        model.setModelName(modalModelName);
        var optionalMake = sneak(() -> makeRepository.findById(makeId));
        optionalMake.ifPresent(model::setMake);
        sneak(() -> modelRepository.save(model));
    }
}


public List<MakeAndModelVO> mapItOut(List<Model> models,Function<Model,MakeAndModelVO> converter){
    return models.stream().map(converter).toList();
}


public boolean hasModelName(MakeAndModelVO searchMakeVO){
    return searchMakeVO.getModelName() != null && searchMakeVO.getModelName().trim().length() > 0;
}


public void deleteMake(Long makeId){
    var consumer = sneaked(makeRepository::deleteById);
    consumer.accept(makeId);
}


public List<MakeAndModelVO> searchModels(MakeAndModelVO searchMakeVO){
    List<MakeAndModelVO> makeAndModelVOS = new ArrayList<>();
    if (searchMakeVO.getMakeId() != null && searchMakeVO.getMakeId() > 0) {
        var optionalMake = sneak(() -> makeRepository.findById(searchMakeVO.getMakeId()));
        if (optionalMake.isPresent()) {
            var make = optionalMake.get();
            var models = make.getModels();
            Function<Model, MakeAndModelVO> converter = model -> getMakeAndModelVO(make, model);
            makeAndModelVOS = mapItOut(models, converter);
        }
    }
    if (hasModelName(searchMakeVO)) {
        var models = getModels(searchMakeVO, searchMakeVO.getModelName());
        Function<Model, MakeAndModelVO> converter = model -> getMakeAndModelVO(model.getMake(), model);
        makeAndModelVOS = mapItOut(models, converter);
    }
    return makeAndModelVOS;
}


public Model updateModelWithMake(Model model){
    var optionalMake = sneak(() -> makeRepository.findById(model.getMakeId()));
    optionalMake.ifPresent(model::setMake);
    return model;
}


public MakeAndModelVO getModelFromId(Long modelId){
    MakeAndModelVO makeAndModelVO = null;
    var optionalModel = sneak(() -> modelRepository.findById(modelId));
    if (optionalModel.isPresent()) {
        makeAndModelVO = makeAndModelEntityConverter.convertModelToMakeAndModelVO(optionalModel.get());
    }
    return makeAndModelVO;
}


public List<MakeAndModelVO> getAllModelsFromMakeId(Long makeId){
    List<MakeAndModelVO> makeVOs = null;
    var optionalMake = sneak(() -> makeRepository.findById(makeId));
    if (optionalMake.isPresent()) {
        var make = optionalMake.get();
        var models = make.getModels();
        if (models != null && !models.isEmpty()) {
            makeVOs = models.stream().map(model -> getMakeAndModelVO(make, model)).toList();
        }
    }
    return makeVOs;
}


public void addNewMake(MakeAndModelVO currentMakeVo){
    var make = makeAndModelEntityConverter.convertToMake(currentMakeVo);
    sneak(() -> makeRepository.save(make));
}


public void deleteModel(Long modelId){
    var consumer = sneaked(modelRepository::deleteById);
    consumer.accept(modelId);
}


public List<MakeVO> convertMakeToMakeVO(List<Make> makes){
    return makes.stream().map(this::createMakeVO).toList();
}


public List<MakeAndModelVO> searchMakeVOs(MakeAndModelVO searchMakeVo){
    return searchModels(searchMakeVo);
}


public MakeAndModelVO getMakeFromId(Long makeId){
    MakeAndModelVO makeVO = null;
    var optionalMake = sneak(() -> makeRepository.findById(makeId));
    if (optionalMake.isPresent()) {
        makeVO = makeAndModelEntityConverter.getMakeVOFromMake(optionalMake.get());
    }
    return makeVO;
}


public List<MakeVO> fetchMakes(){
    var makes = sneak(makeRepository::findAll);
    return convertMakeToMakeVO(makes);
}


public MakeVO createMakeVO(Make make){
    var makeVO = new MakeVO();
    makeVO.setId(make.getId());
    makeVO.setMakeName(make.getMakeName());
    makeVO.setDescription(make.getDescription());
    makeVO.setCreatedBy(make.getCreatedBy());
    makeVO.setModifiedBy(make.getModifiedBy());
    return makeVO;
}


}