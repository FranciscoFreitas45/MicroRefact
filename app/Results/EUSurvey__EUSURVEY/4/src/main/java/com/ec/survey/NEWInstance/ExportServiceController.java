package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ExportServiceController {

 private ExportService exportservice;


@GetMapping
("/getExports")
public List<Export> getExports(@RequestParam(name = "userId") int userId,@RequestParam(name = "sortKey") String sortKey,@RequestParam(name = "ascending") boolean ascending,@RequestParam(name = "page") int page,@RequestParam(name = "rowsPerPage") int rowsPerPage,@RequestParam(name = "eagerloading") boolean eagerloading,@RequestParam(name = "determinestate") boolean determinestate,@RequestParam(name = "onlynotnotified") boolean onlynotnotified,@RequestParam(name = "overrideSurveyTitle") boolean overrideSurveyTitle){
  return exportservice.getExports(userId,sortKey,ascending,page,rowsPerPage,eagerloading,determinestate,onlynotnotified,overrideSurveyTitle);
}


@PutMapping
("/deleteExport")
public void deleteExport(@RequestParam(name = "export") Export export){
exportservice.deleteExport(export);
}


@PutMapping
("/applyExportTimeout")
public void applyExportTimeout(){
exportservice.applyExportTimeout();
}


@PutMapping
("/deleteOldExports")
public void deleteOldExports(){
exportservice.deleteOldExports();
}


}