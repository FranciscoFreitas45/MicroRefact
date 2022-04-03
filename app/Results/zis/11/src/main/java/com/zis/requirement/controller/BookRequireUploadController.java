package com.zis.requirement.controller;
 import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.zis.common.controllertemplate.CommonImportController;
import com.zis.requirement.dto.BookRequireUploadDTO;
@Controller
@RequestMapping(value = "/requirement")
public class BookRequireUploadController extends CommonImportController<BookRequireUploadDTO>{


@Override
public String initTemplatePath(){
    return "booklist.xls";
}


@Override
public String getFailPage(){
    return "error";
}


@Override
public Logger initLogger(){
    return Logger.getLogger(BookRequireUploadController.class);
}


@Override
public void saveImportRecord(List<BookRequireUploadDTO> list,String memo){
// this.bookAmountService.saveTempBookRequireImportDetails(list,
// college, operator,
// memo);
}


@Override
public Map<String,Integer> initPropMapping(){
    Map<String, Integer> mapping = new HashMap<String, Integer>();
    mapping.put("isbn", 0);
    mapping.put("bookName", 1);
    mapping.put("bookAuthor", 2);
    mapping.put("bookEdition", 3);
    mapping.put("bookPublisher", 4);
    mapping.put("college", 5);
    mapping.put("institute", 6);
    mapping.put("partName", 7);
    mapping.put("grade", 8);
    mapping.put("term", 9);
    mapping.put("classNum", 10);
    mapping.put("amount", 11);
    return mapping;
}


@RequiresPermissions(value = { "requirement:uploadBookRequirement" })
@RequestMapping(value = "/uploadBookRequirement")
public String upload(MultipartFile excelFile,String memo,ModelMap map){
    try {
        InputStream fileInputStream = excelFile.getInputStream();
        return super.upload(fileInputStream, memo, map);
    } catch (IOException e) {
        e.printStackTrace();
        map.put("actionError", "传入文件为空");
        return getFailPage();
    }
}


@Override
public String getSuccessPage(){
    return "redirect:/requirement/viewBookRequireImportTask";
}


@Override
public BookRequireUploadDTO getInstance(){
    return new BookRequireUploadDTO();
}


@Override
public String getFaildRecordMessage(BookRequireUploadDTO failRecord){
    return null;
}


}