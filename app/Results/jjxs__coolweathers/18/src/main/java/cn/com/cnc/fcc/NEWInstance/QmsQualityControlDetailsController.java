package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsQualityControlDetailsController {

 private QmsQualityControlDetailsRepository qmsqualitycontroldetailsrepository;


@PutMapping
("/setTechnicalRequirement/{id}")
public void setTechnicalRequirement(@PathVariable(name = "id") Long id,@RequestParam(name = "technicalRequirement") String technicalRequirement){
 qmsqualitycontroldetailsrepository.setTechnicalRequirement(id,technicalRequirement);
}


@PutMapping
("/setInspectionInstrument/{id}")
public void setInspectionInstrument(@PathVariable(name = "id") Long id,@RequestParam(name = "inspectionInstrument") String inspectionInstrument){
 qmsqualitycontroldetailsrepository.setInspectionInstrument(id,inspectionInstrument);
}


@PutMapping
("/setStandard/{id}")
public void setStandard(@PathVariable(name = "id") Long id,@RequestParam(name = "standard") Double standard){
 qmsqualitycontroldetailsrepository.setStandard(id,standard);
}


@PutMapping
("/setInspectionResultDiff/{id}")
public void setInspectionResultDiff(@PathVariable(name = "id") Long id,@RequestParam(name = "inspectionResultDiff") String inspectionResultDiff){
 qmsqualitycontroldetailsrepository.setInspectionResultDiff(id,inspectionResultDiff);
}


@PutMapping
("/setIsCheckObj/{id}")
public void setIsCheckObj(@PathVariable(name = "id") Long id,@RequestParam(name = "isCheckObj") String isCheckObj){
 qmsqualitycontroldetailsrepository.setIsCheckObj(id,isCheckObj);
}


@PutMapping
("/setRemark/{id}")
public void setRemark(@PathVariable(name = "id") Long id,@RequestParam(name = "remark") String remark){
 qmsqualitycontroldetailsrepository.setRemark(id,remark);
}


@PutMapping
("/setMakeTime/{id}")
public void setMakeTime(@PathVariable(name = "id") Long id,@RequestParam(name = "makeTime") ZonedDateTime makeTime){
 qmsqualitycontroldetailsrepository.setMakeTime(id,makeTime);
}


@PutMapping
("/setMakeUser/{id}")
public void setMakeUser(@PathVariable(name = "id") Long id,@RequestParam(name = "makeUser") String makeUser){
 qmsqualitycontroldetailsrepository.setMakeUser(id,makeUser);
}


@PutMapping
("/setModifyTime/{id}")
public void setModifyTime(@PathVariable(name = "id") Long id,@RequestParam(name = "modifyTime") ZonedDateTime modifyTime){
 qmsqualitycontroldetailsrepository.setModifyTime(id,modifyTime);
}


}