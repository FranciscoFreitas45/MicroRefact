package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsEnclosureController {

 private QmsEnclosureRepository qmsenclosurerepository;


@PutMapping
("/setEnclosureAddress/{id}")
public void setEnclosureAddress(@PathVariable(name = "id") Long id,@RequestParam(name = "enclosureAddress") String enclosureAddress){
 qmsenclosurerepository.setEnclosureAddress(id,enclosureAddress);
}


@PutMapping
("/setInspectionInfoId/{id}")
public void setInspectionInfoId(@PathVariable(name = "id") Long id,@RequestParam(name = "inspectionInfoId") Integer inspectionInfoId){
 qmsenclosurerepository.setInspectionInfoId(id,inspectionInfoId);
}


@PutMapping
("/setInspectionKbn/{id}")
public void setInspectionKbn(@PathVariable(name = "id") Long id,@RequestParam(name = "inspectionKbn") String inspectionKbn){
 qmsenclosurerepository.setInspectionKbn(id,inspectionKbn);
}


@PutMapping
("/setMakeTime/{id}")
public void setMakeTime(@PathVariable(name = "id") Long id,@RequestParam(name = "makeTime") ZonedDateTime makeTime){
 qmsenclosurerepository.setMakeTime(id,makeTime);
}


@PutMapping
("/setModifyUser/{id}")
public void setModifyUser(@PathVariable(name = "id") Long id,@RequestParam(name = "modifyUser") String modifyUser){
 qmsenclosurerepository.setModifyUser(id,modifyUser);
}


}