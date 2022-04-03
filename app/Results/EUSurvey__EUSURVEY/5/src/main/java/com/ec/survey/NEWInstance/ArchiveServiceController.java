package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ArchiveServiceController {

 private ArchiveService archiveservice;


@PutMapping
("/add")
public void add(@RequestParam(name = "archive") Archive archive){
archiveservice.add(archive);
}


@PutMapping
("/update")
public void update(@RequestParam(name = "archive") Archive archive){
archiveservice.update(archive);
}


@GetMapping
("/getArchivesToRestart")
public List<Archive> getArchivesToRestart(){
  return archiveservice.getArchivesToRestart();
}


}