package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AgentServiceController {

 private AgentService agentservice;

 private AgentService agentservice;


@PutMapping
("/setQualitytype")
public void setQualitytype(@RequestParam(name = "qualitytype") String qualitytype){
agentservice.setQualitytype(qualitytype);
}


@PutMapping
("/setAssuser")
public void setAssuser(@RequestParam(name = "assuser") String assuser){
agentservice.setAssuser(assuser);
}


@PutMapping
("/setTemplateid")
public void setTemplateid(@RequestParam(name = "templateid") String templateid){
agentservice.setTemplateid(templateid);
}


@PutMapping
("/setQualitystatus")
public void setQualitystatus(@RequestParam(name = "qualitystatus") String qualitystatus){
agentservice.setQualitystatus(qualitystatus);
}


@PutMapping
("/setQualityactid")
public void setQualityactid(@RequestParam(name = "qualityactid") String qualityactid){
agentservice.setQualityactid(qualityactid);
}


@PutMapping
("/setQualityfilterid")
public void setQualityfilterid(@RequestParam(name = "qualityfilterid") String qualityfilterid){
agentservice.setQualityfilterid(qualityfilterid);
}


@PutMapping
("/setQualitydisorgan")
public void setQualitydisorgan(@RequestParam(name = "qualitydisorgan") String qualitydisorgan){
agentservice.setQualitydisorgan(qualitydisorgan);
}


@PutMapping
("/setQualitydistype")
public void setQualitydistype(@RequestParam(name = "qualitydistype") String qualitydistype){
agentservice.setQualitydistype(qualitydistype);
}


@PutMapping
("/setQualitydisuser")
public void setQualitydisuser(@RequestParam(name = "qualitydisuser") String qualitydisuser){
agentservice.setQualitydisuser(qualitydisuser);
}


}