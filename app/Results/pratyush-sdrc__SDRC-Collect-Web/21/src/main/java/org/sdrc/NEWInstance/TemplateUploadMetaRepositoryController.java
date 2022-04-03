package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TemplateUploadMetaRepositoryController {

 private TemplateUploadMetaRepository templateuploadmetarepository;


@GetMapping
("/save")
public TemplateUploadMeta save(@RequestParam(name = "templateUploadMeta") TemplateUploadMeta templateUploadMeta){
  return templateuploadmetarepository.save(templateUploadMeta);
}


}