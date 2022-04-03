package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsPartsAssemblyRelationController {

 private QmsPartsAssemblyRelationRepository qmspartsassemblyrelationrepository;


@PutMapping
("/setBomTechnologyId/{id}")
public void setBomTechnologyId(@PathVariable(name = "id") Long id,@RequestParam(name = "bomTechnologyId") Integer bomTechnologyId){
 qmspartsassemblyrelationrepository.setBomTechnologyId(id,bomTechnologyId);
}


@PutMapping
("/setAssemblyMaterielId/{id}")
public void setAssemblyMaterielId(@PathVariable(name = "id") Long id,@RequestParam(name = "assemblyMaterielId") Integer assemblyMaterielId){
 qmspartsassemblyrelationrepository.setAssemblyMaterielId(id,assemblyMaterielId);
}


@PutMapping
("/setAssemblyCount/{id}")
public void setAssemblyCount(@PathVariable(name = "id") Long id,@RequestParam(name = "assemblyCount") Integer assemblyCount){
 qmspartsassemblyrelationrepository.setAssemblyCount(id,assemblyCount);
}


@PutMapping
("/setModifyTime/{id}")
public void setModifyTime(@PathVariable(name = "id") Long id,@RequestParam(name = "modifyTime") ZonedDateTime modifyTime){
 qmspartsassemblyrelationrepository.setModifyTime(id,modifyTime);
}


@PutMapping
("/setFlagStatus/{id}")
public void setFlagStatus(@PathVariable(name = "id") Long id,@RequestParam(name = "flagStatus") String flagStatus){
 qmspartsassemblyrelationrepository.setFlagStatus(id,flagStatus);
}


}