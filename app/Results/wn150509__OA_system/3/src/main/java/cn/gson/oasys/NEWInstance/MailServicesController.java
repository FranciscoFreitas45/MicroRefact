package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MailServicesController {

 private MailServices mailservices;


@GetMapping
("/upload")
public Attachment upload(@RequestParam(name = "file") MultipartFile file,@RequestParam(name = "mu") User mu){
  return mailservices.upload(file,mu);
}


}