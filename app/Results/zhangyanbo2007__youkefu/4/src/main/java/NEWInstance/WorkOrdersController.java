package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WorkOrdersController {

 private WorkOrders workorders;

 private WorkOrders workorders;


@PutMapping
("/setAnonymous")
public void setAnonymous(@RequestParam(name = "anonymous") boolean anonymous){
workorders.setAnonymous(anonymous);
}


@PutMapping
("/setQualitydistime")
public void setQualitydistime(@RequestParam(name = "qualitydistime") Date qualitydistime){
workorders.setQualitydistime(qualitydistime);
}


@PutMapping
("/setQualitytype")
public void setQualitytype(@RequestParam(name = "qualitytype") String qualitytype){
workorders.setQualitytype(qualitytype);
}


@PutMapping
("/setAssuser")
public void setAssuser(@RequestParam(name = "assuser") String assuser){
workorders.setAssuser(assuser);
}


@PutMapping
("/setTemplateid")
public void setTemplateid(@RequestParam(name = "templateid") String templateid){
workorders.setTemplateid(templateid);
}


@PutMapping
("/setQualitystatus")
public void setQualitystatus(@RequestParam(name = "qualitystatus") String qualitystatus){
workorders.setQualitystatus(qualitystatus);
}


@PutMapping
("/setQualityactid")
public void setQualityactid(@RequestParam(name = "qualityactid") String qualityactid){
workorders.setQualityactid(qualityactid);
}


@PutMapping
("/setQualityfilterid")
public void setQualityfilterid(@RequestParam(name = "qualityfilterid") String qualityfilterid){
workorders.setQualityfilterid(qualityfilterid);
}


@PutMapping
("/setQualitydisorgan")
public void setQualitydisorgan(@RequestParam(name = "qualitydisorgan") String qualitydisorgan){
workorders.setQualitydisorgan(qualitydisorgan);
}


@PutMapping
("/setQualitydistype")
public void setQualitydistype(@RequestParam(name = "qualitydistype") String qualitydistype){
workorders.setQualitydistype(qualitydistype);
}


@PutMapping
("/setQualitydisuser")
public void setQualitydisuser(@RequestParam(name = "qualitydisuser") String qualitydisuser){
workorders.setQualitydisuser(qualitydisuser);
}


}