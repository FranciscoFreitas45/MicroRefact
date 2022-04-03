package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TSAttachmentController {

 private TSAttachment tsattachment;

 private TSAttachment tsattachment;


@PutMapping
("/setCreatedate")
public void setCreatedate(@RequestParam(name = "createdate") Timestamp createdate){
tsattachment.setCreatedate(createdate);
}


@PutMapping
("/setExtend")
public void setExtend(@RequestParam(name = "extend") String extend){
tsattachment.setExtend(extend);
}


@PutMapping
("/setRealpath")
public void setRealpath(@RequestParam(name = "realpath") String realpath){
tsattachment.setRealpath(realpath);
}


@PutMapping
("/setSwfpath")
public void setSwfpath(@RequestParam(name = "swfpath") String swfpath){
tsattachment.setSwfpath(swfpath);
}


}