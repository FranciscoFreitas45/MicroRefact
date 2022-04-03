package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FileHandlersController {

 private FileHandlers filehandlers;


@GetMapping
("/upload")
public UploadResult upload(@RequestParam(name = "file") MultipartFile file,@RequestParam(name = "attachmentType") AttachmentType attachmentType){
  return filehandlers.upload(file,attachmentType);
}


@PutMapping
("/delete")
public void delete(@RequestParam(name = "attachment") Attachment attachment){
filehandlers.delete(attachment);
}


}