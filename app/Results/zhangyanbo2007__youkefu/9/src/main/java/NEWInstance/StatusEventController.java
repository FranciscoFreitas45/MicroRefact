package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StatusEventController {

 private StatusEvent statusevent;

 private StatusEvent statusevent;


@PutMapping
("/setQualitytype")
public void setQualitytype(@RequestParam(name = "qualitytype") String qualitytype){
statusevent.setQualitytype(qualitytype);
}


@PutMapping
("/setAssuser")
public void setAssuser(@RequestParam(name = "assuser") String assuser){
statusevent.setAssuser(assuser);
}


@PutMapping
("/setTemplateid")
public void setTemplateid(@RequestParam(name = "templateid") String templateid){
statusevent.setTemplateid(templateid);
}


@PutMapping
("/setQualityactid")
public void setQualityactid(@RequestParam(name = "qualityactid") String qualityactid){
statusevent.setQualityactid(qualityactid);
}


@PutMapping
("/setQualityfilterid")
public void setQualityfilterid(@RequestParam(name = "qualityfilterid") String qualityfilterid){
statusevent.setQualityfilterid(qualityfilterid);
}


@PutMapping
("/setQualitystatus")
public void setQualitystatus(@RequestParam(name = "qualitystatus") String qualitystatus){
statusevent.setQualitystatus(qualitystatus);
}


@PutMapping
("/setQualitydisorgan")
public void setQualitydisorgan(@RequestParam(name = "qualitydisorgan") String qualitydisorgan){
statusevent.setQualitydisorgan(qualitydisorgan);
}


@PutMapping
("/setQualitydistype")
public void setQualitydistype(@RequestParam(name = "qualitydistype") String qualitydistype){
statusevent.setQualitydistype(qualitydistype);
}


@PutMapping
("/setQualitydisuser")
public void setQualitydisuser(@RequestParam(name = "qualitydisuser") String qualitydisuser){
statusevent.setQualitydisuser(qualitydisuser);
}


}