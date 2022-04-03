package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UtDataController {

 private DevinfoUtDataRepository devinfoutdatarepository;


@PutMapping
("/setData_Value/{id}")
public void setData_Value(@PathVariable(name = "id") int id,@RequestParam(name = "data_Value") Double data_Value){
 devinfoutdatarepository.setData_Value(id,data_Value);
}


@PutMapping
("/setArea_NId/{id}")
public void setArea_NId(@PathVariable(name = "id") int id,@RequestParam(name = "area_NId") int area_NId){
 devinfoutdatarepository.setArea_NId(id,area_NId);
}


@PutMapping
("/setSubgroup_Val_NId/{id}")
public void setSubgroup_Val_NId(@PathVariable(name = "id") int id,@RequestParam(name = "subgroup_Val_NId") int subgroup_Val_NId){
 devinfoutdatarepository.setSubgroup_Val_NId(id,subgroup_Val_NId);
}


@PutMapping
("/setIUNId/{id}")
public void setIUNId(@PathVariable(name = "id") int id,@RequestParam(name = "IUNId") String IUNId){
 devinfoutdatarepository.setIUNId(id,IUNId);
}


@PutMapping
("/setSource_NId/{id}")
public void setSource_NId(@PathVariable(name = "id") int id,@RequestParam(name = "source_NId") int source_NId){
 devinfoutdatarepository.setSource_NId(id,source_NId);
}


}