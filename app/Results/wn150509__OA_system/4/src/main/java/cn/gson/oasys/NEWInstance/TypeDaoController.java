package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TypeDaoController {

 private TypeDao typedao;


@GetMapping
("/findByTypeModel")
public List<SystemTypeList> findByTypeModel(@RequestParam(name = "typeModel") String typeModel){
  return typedao.findByTypeModel(typeModel);
}


@GetMapping
("/findname")
public String findname(@RequestParam(name = "id") Long id){
  return typedao.findname(id);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return typedao.findOne(Object);
}


@GetMapping
("/findByTypeModelAndTypeName")
public SystemTypeList findByTypeModelAndTypeName(@RequestParam(name = "typeModel") String typeModel,@RequestParam(name = "typeName") String typeName){
  return typedao.findByTypeModelAndTypeName(typeModel,typeName);
}


}