package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttachmentController {

 private Attachment attachment;


@GetMapping
("/getAttachmentId")
public Long getAttachmentId(){
  return attachment.getAttachmentId();
}


@GetMapping
("/getAttachmentSize")
public Long getAttachmentSize(){
  return attachment.getAttachmentSize();
}


@GetMapping
("/intValue")
public Object intValue(@RequestParam(name = "Object") Object Object){
  return attachment.intValue(Object);
}


@GetMapping
("/getAttachmentType")
public String getAttachmentType(){
  return attachment.getAttachmentType();
}


@GetMapping
("/getAttachmentName")
public String getAttachmentName(){
  return attachment.getAttachmentName();
}


@GetMapping
("/getBytes")
public Object getBytes(@RequestParam(name = "Object") Object Object){
  return attachment.getBytes(Object);
}


@PutMapping
("/setModel/{id}")
public void setModel(@PathVariable(name = "id") Long id,@RequestParam(name = "model") String model){
 attachmentdao.setModel(id,model);
}


@PutMapping
("/setAttachmentPath/{id}")
public void setAttachmentPath(@PathVariable(name = "id") Long id,@RequestParam(name = "attachmentPath") String attachmentPath){
 attachmentdao.setAttachmentPath(id,attachmentPath);
}


@PutMapping
("/setAttachmentShuffix/{id}")
public void setAttachmentShuffix(@PathVariable(name = "id") Long id,@RequestParam(name = "attachmentShuffix") String attachmentShuffix){
 attachmentdao.setAttachmentShuffix(id,attachmentShuffix);
}


@PutMapping
("/setAttachmentSize/{id}")
public void setAttachmentSize(@PathVariable(name = "id") Long id,@RequestParam(name = "attachmentSize") Long attachmentSize){
 attachmentdao.setAttachmentSize(id,attachmentSize);
}


@PutMapping
("/setAttachmentType/{id}")
public void setAttachmentType(@PathVariable(name = "id") Long id,@RequestParam(name = "attachmentType") String attachmentType){
 attachmentdao.setAttachmentType(id,attachmentType);
}


@PutMapping
("/setUploadTime/{id}")
public void setUploadTime(@PathVariable(name = "id") Long id,@RequestParam(name = "uploadTime") Date uploadTime){
 attachmentdao.setUploadTime(id,uploadTime);
}


@PutMapping
("/setUserId/{id}")
public void setUserId(@PathVariable(name = "id") Long id,@RequestParam(name = "userId") String userId){
 attachmentdao.setUserId(id,userId);
}


}