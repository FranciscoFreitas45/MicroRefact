package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FileWebServiceController {

 private FileWebService filewebservice;


@GetMapping
("/getImage")
public FileDTO getImage(@RequestParam(name = "fileHash") UUID fileHash,@RequestParam(name = "width") Integer width,@RequestParam(name = "height") Integer height){
  return filewebservice.getImage(fileHash,width,height);
}


@GetMapping
("/getFile")
public FileDTO getFile(@RequestParam(name = "fileHash") UUID fileHash){
  return filewebservice.getFile(fileHash);
}


}