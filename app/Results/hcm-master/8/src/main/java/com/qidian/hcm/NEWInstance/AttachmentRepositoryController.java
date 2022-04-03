package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttachmentRepositoryController {

 private AttachmentRepository attachmentrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return attachmentrepository.save(Object);
}


@GetMapping
("/findByFileId")
public Optional<Attachment> findByFileId(@RequestParam(name = "fileId") Long fileId){
  return attachmentrepository.findByFileId(fileId);
}


}