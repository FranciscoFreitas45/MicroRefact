package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FileServiceController {

 private FileService fileservice;


@GetMapping
("/removeByKey")
public Object removeByKey(@RequestParam(name = "Object") Object Object){
  return fileservice.removeByKey(Object);
}


@GetMapping
("/saveImage")
public File saveImage(@RequestParam(name = "multipartFile") MultipartFile multipartFile,@RequestParam(name = "alternativeDescription") String alternativeDescription){
  return fileservice.saveImage(multipartFile,alternativeDescription);
}


@GetMapping
("/getByHash")
public Object getByHash(@RequestParam(name = "Object") Object Object){
  return fileservice.getByHash(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return fileservice.update(Object);
}


@GetMapping
("/saveFile")
public File saveFile(@RequestParam(name = "file") MultipartFile file){
  return fileservice.saveFile(file);
}


@GetMapping
("/removeByHash")
public Object removeByHash(@RequestParam(name = "Object") Object Object){
  return fileservice.removeByHash(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return fileservice.save(Object);
}


}