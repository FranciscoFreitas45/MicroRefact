package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FileServiceController {

 private FileService fileservice;


@GetMapping
("/getArchiveFolder")
public java.io.File getArchiveFolder(@RequestParam(name = "surveyUID") String surveyUID){
  return fileservice.getArchiveFolder(surveyUID);
}


@PutMapping
("/addNewTransaction")
public void addNewTransaction(@RequestParam(name = "file") File file){
fileservice.addNewTransaction(file);
}


@PutMapping
("/saveNewTransaction")
public void saveNewTransaction(@RequestParam(name = "ec") ExportCache ec){
fileservice.saveNewTransaction(ec);
}


@GetMapping
("/getSurveyFile")
public java.io.File getSurveyFile(@RequestParam(name = "surveyUID") String surveyUID,@RequestParam(name = "fileUID") String fileUID){
  return fileservice.getSurveyFile(surveyUID,fileUID);
}


@GetMapping
("/add")
public boolean add(@RequestParam(name = "item") FileResult item,@RequestParam(name = "map") Map<String,FileResult> map){
  return fileservice.add(item,map);
}


@GetMapping
("/getSurveyExportFile")
public java.io.File getSurveyExportFile(@RequestParam(name = "surveyUID") String surveyUID,@RequestParam(name = "fileUID") String fileUID,@RequestParam(name = "create") boolean create){
  return fileservice.getSurveyExportFile(surveyUID,fileUID,create);
}


@PutMapping
("/logOldFileSystemUse")
public void logOldFileSystemUse(@RequestParam(name = "path") String path){
fileservice.logOldFileSystemUse(path);
}


@GetMapping
("/getCachedExport")
public ExportCache getCachedExport(@RequestParam(name = "surveyId") Integer surveyId,@RequestParam(name = "hash") String hash,@RequestParam(name = "type") String type){
  return fileservice.getCachedExport(surveyId,hash,type);
}


@PutMapping
("/save")
public void save(@RequestParam(name = "ec") ExportCache ec){
fileservice.save(ec);
}


@GetMapping
("/get")
public File get(@RequestParam(name = "uid") String uid,@RequestParam(name = "id") Integer id){
  return fileservice.get(uid,id);
}


@GetMapping
("/createTempFile")
public java.io.File createTempFile(@RequestParam(name = "filename") String filename,@RequestParam(name = "suffix") String suffix){
  return fileservice.createTempFile(filename,suffix);
}


}