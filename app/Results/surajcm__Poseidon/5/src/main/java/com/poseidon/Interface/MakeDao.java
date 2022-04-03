package com.poseidon.Interface;
public interface MakeDao {

   public List<MakeAndModelVO> listAllMakesAndModels();
   public List<MakeAndModelVO> listAllMakes();
   public void addNewMake(MakeAndModelVO currentMakeVo);
   public MakeAndModelVO getMakeFromId(Long makeId);
   public void deleteMake(Long makeId);
   public MakeAndModelVO getModelFromId(Long modelId);
   public void deleteModel(Long modelId);
   public void updateMake(MakeAndModelVO currentMakeVo);
   public void addNewModel(MakeAndModelVO currentMakeVo);
   public void updateModel(Long id,Long makeId,String modalModelName);
   public List<MakeAndModelVO> searchMakeVOs(MakeAndModelVO searchMakeVo);
   public List<MakeVO> fetchMakes();
   public List<MakeAndModelVO> getAllModelsFromMakeId(Long makeId);
}