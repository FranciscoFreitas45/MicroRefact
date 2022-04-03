package com.poseidon.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MakeDaoController {

 private MakeDao makedao;


@GetMapping
("/listAllMakesAndModels")
public List<MakeAndModelVO> listAllMakesAndModels(){
  return makedao.listAllMakesAndModels();
}


@GetMapping
("/listAllMakes")
public List<MakeAndModelVO> listAllMakes(){
  return makedao.listAllMakes();
}


@PutMapping
("/addNewMake")
public void addNewMake(@RequestParam(name = "currentMakeVo") MakeAndModelVO currentMakeVo){
makedao.addNewMake(currentMakeVo);
}


@GetMapping
("/getMakeFromId")
public MakeAndModelVO getMakeFromId(@RequestParam(name = "makeId") Long makeId){
  return makedao.getMakeFromId(makeId);
}


@PutMapping
("/deleteMake")
public void deleteMake(@RequestParam(name = "makeId") Long makeId){
makedao.deleteMake(makeId);
}


@GetMapping
("/getModelFromId")
public MakeAndModelVO getModelFromId(@RequestParam(name = "modelId") Long modelId){
  return makedao.getModelFromId(modelId);
}


@PutMapping
("/deleteModel")
public void deleteModel(@RequestParam(name = "modelId") Long modelId){
makedao.deleteModel(modelId);
}


@PutMapping
("/updateMake")
public void updateMake(@RequestParam(name = "currentMakeVo") MakeAndModelVO currentMakeVo){
makedao.updateMake(currentMakeVo);
}


@PutMapping
("/addNewModel")
public void addNewModel(@RequestParam(name = "currentMakeVo") MakeAndModelVO currentMakeVo){
makedao.addNewModel(currentMakeVo);
}


@PutMapping
("/updateModel")
public void updateModel(@RequestParam(name = "id") Long id,@RequestParam(name = "makeId") Long makeId,@RequestParam(name = "modalModelName") String modalModelName){
makedao.updateModel(id,makeId,modalModelName);
}


@GetMapping
("/searchMakeVOs")
public List<MakeAndModelVO> searchMakeVOs(@RequestParam(name = "searchMakeVo") MakeAndModelVO searchMakeVo){
  return makedao.searchMakeVOs(searchMakeVo);
}


@GetMapping
("/fetchMakes")
public List<MakeVO> fetchMakes(){
  return makedao.fetchMakes();
}


@GetMapping
("/getAllModelsFromMakeId")
public List<MakeAndModelVO> getAllModelsFromMakeId(@RequestParam(name = "makeId") Long makeId){
  return makedao.getAllModelsFromMakeId(makeId);
}


}