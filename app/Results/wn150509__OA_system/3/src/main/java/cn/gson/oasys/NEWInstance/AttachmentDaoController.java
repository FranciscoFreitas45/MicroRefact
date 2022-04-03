package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttachmentDaoController {

 private AttachmentDao attachmentdao;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return attachmentdao.findOne(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return attachmentdao.save(Object);
}


@GetMapping
("/findByAttachmentPath")
public Attachment findByAttachmentPath(@RequestParam(name = "AttachmentPath") String AttachmentPath){
  return attachmentdao.findByAttachmentPath(AttachmentPath);
}


@GetMapping
("/findByAttachmentId")
public Attachment findByAttachmentId(@RequestParam(name = "AttachmentId") long AttachmentId){
  return attachmentdao.findByAttachmentId(AttachmentId);
}


}