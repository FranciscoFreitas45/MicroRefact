package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QualityMissionHisController {

 private QualityMissionHis qualitymissionhis;

 private QualityMissionHis qualitymissionhis;


@PutMapping
("/setQualitydisuser")
public void setQualitydisuser(@RequestParam(name = "qualitydisuser") String qualitydisuser){
qualitymissionhis.setQualitydisuser(qualitydisuser);
}


@PutMapping
("/setDataid")
public void setDataid(@RequestParam(name = "dataid") String dataid){
qualitymissionhis.setDataid(dataid);
}


@PutMapping
("/setQualitytype")
public void setQualitytype(@RequestParam(name = "qualitytype") String qualitytype){
qualitymissionhis.setQualitytype(qualitytype);
}


@PutMapping
("/setFormfilterid")
public void setFormfilterid(@RequestParam(name = "formfilterid") String formfilterid){
qualitymissionhis.setFormfilterid(formfilterid);
}


@PutMapping
("/setQualitytime")
public void setQualitytime(@RequestParam(name = "qualitytime") Date qualitytime){
qualitymissionhis.setQualitytime(qualitytime);
}


@PutMapping
("/setAssuser")
public void setAssuser(@RequestParam(name = "assuser") String assuser){
qualitymissionhis.setAssuser(assuser);
}


@PutMapping
("/setTemplateid")
public void setTemplateid(@RequestParam(name = "templateid") String templateid){
qualitymissionhis.setTemplateid(templateid);
}


@PutMapping
("/setQualitystatus")
public void setQualitystatus(@RequestParam(name = "qualitystatus") String qualitystatus){
qualitymissionhis.setQualitystatus(qualitystatus);
}


@PutMapping
("/setOrgi")
public void setOrgi(@RequestParam(name = "orgi") String orgi){
qualitymissionhis.setOrgi(orgi);
}


@PutMapping
("/setActid")
public void setActid(@RequestParam(name = "actid") String actid){
qualitymissionhis.setActid(actid);
}


@PutMapping
("/setOrgan")
public void setOrgan(@RequestParam(name = "organ") String organ){
qualitymissionhis.setOrgan(organ);
}


@PutMapping
("/setFilterid")
public void setFilterid(@RequestParam(name = "filterid") String filterid){
qualitymissionhis.setFilterid(filterid);
}


@PutMapping
("/setTaskid")
public void setTaskid(@RequestParam(name = "taskid") String taskid){
qualitymissionhis.setTaskid(taskid);
}


}