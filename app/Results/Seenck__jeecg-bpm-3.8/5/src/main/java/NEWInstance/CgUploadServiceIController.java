package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CgUploadServiceIController {

 private CgUploadServiceI cguploadservicei;


@PutMapping
("/writeBack")
public void writeBack(@RequestParam(name = "cgFormId") String cgFormId,@RequestParam(name = "cgFormName") String cgFormName,@RequestParam(name = "cgFormField") String cgFormField,@RequestParam(name = "fileId") String fileId,@RequestParam(name = "fileUrl") String fileUrl){
cguploadservicei.writeBack(cgFormId,cgFormName,cgFormField,fileId,fileUrl);
}


@PutMapping
("/deleteFile")
public void deleteFile(@RequestParam(name = "file") CgUploadEntity file){
cguploadservicei.deleteFile(file);
}


}