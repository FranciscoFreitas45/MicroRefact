package com.poseidon.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.poseidon.Interface.MakeDao;
public class MakeDaoImpl implements MakeDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<MakeAndModelVO> listAllMakesAndModels(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listAllMakesAndModels"))
;  List<MakeAndModelVO> aux = restTemplate.getForObject(builder.toUriString(), List<MakeAndModelVO>.class);

 return aux;
}


public List<MakeAndModelVO> listAllMakes(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listAllMakes"))
;  List<MakeAndModelVO> aux = restTemplate.getForObject(builder.toUriString(), List<MakeAndModelVO>.class);

 return aux;
}


public void addNewMake(MakeAndModelVO currentMakeVo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addNewMake"))
    .queryParam("currentMakeVo",currentMakeVo)
;
  restTemplate.put(builder.toUriString(), null);
}


public MakeAndModelVO getMakeFromId(Long makeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMakeFromId"))
    .queryParam("makeId",makeId)
;  MakeAndModelVO aux = restTemplate.getForObject(builder.toUriString(), MakeAndModelVO.class);

 return aux;
}


public void deleteMake(Long makeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteMake"))
    .queryParam("makeId",makeId)
;
  restTemplate.put(builder.toUriString(), null);
}


public MakeAndModelVO getModelFromId(Long modelId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getModelFromId"))
    .queryParam("modelId",modelId)
;  MakeAndModelVO aux = restTemplate.getForObject(builder.toUriString(), MakeAndModelVO.class);

 return aux;
}


public void deleteModel(Long modelId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteModel"))
    .queryParam("modelId",modelId)
;
  restTemplate.put(builder.toUriString(), null);
}


public void updateMake(MakeAndModelVO currentMakeVo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateMake"))
    .queryParam("currentMakeVo",currentMakeVo)
;
  restTemplate.put(builder.toUriString(), null);
}


public void addNewModel(MakeAndModelVO currentMakeVo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addNewModel"))
    .queryParam("currentMakeVo",currentMakeVo)
;
  restTemplate.put(builder.toUriString(), null);
}


public void updateModel(Long id,Long makeId,String modalModelName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateModel"))
    .queryParam("id",id)
    .queryParam("makeId",makeId)
    .queryParam("modalModelName",modalModelName)
;
  restTemplate.put(builder.toUriString(), null);
}


public List<MakeAndModelVO> searchMakeVOs(MakeAndModelVO searchMakeVo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/searchMakeVOs"))
    .queryParam("searchMakeVo",searchMakeVo)
;  List<MakeAndModelVO> aux = restTemplate.getForObject(builder.toUriString(), List<MakeAndModelVO>.class);

 return aux;
}


public List<MakeVO> fetchMakes(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fetchMakes"))
;  List<MakeVO> aux = restTemplate.getForObject(builder.toUriString(), List<MakeVO>.class);

 return aux;
}


public List<MakeAndModelVO> getAllModelsFromMakeId(Long makeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllModelsFromMakeId"))
    .queryParam("makeId",makeId)
;  List<MakeAndModelVO> aux = restTemplate.getForObject(builder.toUriString(), List<MakeAndModelVO>.class);

 return aux;
}


}