package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TemplateUploadMetaController {

 private SpringDataTemplateUploadMetaRepository springdatatemplateuploadmetarepository;


@PutMapping
("/setDateOfUpload/{id}")
public void setDateOfUpload(@PathVariable(name = "id") Integer id,@RequestParam(name = "dateOfUpload") Timestamp dateOfUpload){
 springdatatemplateuploadmetarepository.setDateOfUpload(id,dateOfUpload);
}


@PutMapping
("/setUserIp/{id}")
public void setUserIp(@PathVariable(name = "id") Integer id,@RequestParam(name = "userIp") String userIp){
 springdatatemplateuploadmetarepository.setUserIp(id,userIp);
}


@PutMapping
("/setDataProvidedBy/{id}")
public void setDataProvidedBy(@PathVariable(name = "id") Integer id,@RequestParam(name = "dataProvidedBy") String dataProvidedBy){
 springdatatemplateuploadmetarepository.setDataProvidedBy(id,dataProvidedBy);
}


}