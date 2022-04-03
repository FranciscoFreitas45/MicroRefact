package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FileImporterController {

 private FileImporter fileimporter;

 private FileImporter fileimporter;


@GetMapping
("/validate")
public String validate(){
  return fileimporter.validate();
}


@GetMapping
("/loadFactHeader")
public List<String> loadFactHeader(){
  return fileimporter.loadFactHeader();
}


@GetMapping
("/parse")
public List<T> parse(@RequestParam(name = "t") T t,@RequestParam(name = "propMapping") Map<String,Integer> propMapping){
  return fileimporter.parse(t,propMapping);
}


@PutMapping
("/setHeaderRowNums")
public void setHeaderRowNums(@RequestParam(name = "headerRowNums") Integer headerRowNums){
fileimporter.setHeaderRowNums(headerRowNums);
}


}