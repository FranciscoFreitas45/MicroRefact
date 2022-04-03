package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ExcelExportController {

 private ExcelExport excelexport;

 private ExcelExport excelexport;


@PutMapping
("/exportData")
public void exportData(){
excelexport.exportData();
}


}