package com.byr.warehouse.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ExcelServiceController {

 private ExcelService excelservice;


@PutMapping
("/ExportEcel")
public void ExportEcel(@RequestParam(name = "excellist") List<T> excellist,@RequestParam(name = "title") String title,@RequestParam(name = "sheetName") String sheetName,@RequestParam(name = "filename") String filename,@RequestParam(name = "calzz") Class calzz){
excelservice.ExportEcel(excellist,title,sheetName,filename,calzz);
}


@PutMapping
("/ExportEcelService")
public void ExportEcelService(@RequestParam(name = "excellist") List<T> excellist,@RequestParam(name = "title") String title,@RequestParam(name = "sheetName") String sheetName,@RequestParam(name = "response") HttpServletResponse response,@RequestParam(name = "filename") String filename,@RequestParam(name = "calzz") Class calzz){
excelservice.ExportEcelService(excellist,title,sheetName,response,filename,calzz);
}


}