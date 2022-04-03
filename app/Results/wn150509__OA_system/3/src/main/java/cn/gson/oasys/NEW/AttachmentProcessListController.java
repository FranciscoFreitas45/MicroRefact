package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.note.Attachment;
@RestController
@CrossOrigin
public class AttachmentProcessListController {

@Autowired
 private AttachmentProcessListService attachmentprocesslistservice;


@GetMapping
("/ProcessList/{id}/Attachment/getProFileid")
public Attachment getProFileid(@PathVariable(name="id") Long attachmentId){
return attachmentprocesslistservice.getProFileid(attachmentId);
}


@PutMapping
("/ProcessList/{id}/Attachment/setProFileid")
public void setProFileid(@PathVariable(name="id") Long attachmentId,@RequestBody Attachment proFileid){
attachmentprocesslistservice.setProFileid(attachmentId,proFileid);
}


}