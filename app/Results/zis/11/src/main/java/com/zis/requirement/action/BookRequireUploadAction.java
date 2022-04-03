package com.zis.requirement.action;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.zis.common.actiontemplate.CommonImportAction;
import com.zis.requirement.biz.BookAmountService;
import com.zis.requirement.dto.BookRequireUploadDTO;
import com.zis.Interface.BookAmountService;
public class BookRequireUploadAction extends CommonImportAction<BookRequireUploadDTO>{

 private  long serialVersionUID;

 private  String college;

 private  String operator;

 private  String memo;

 private  BookAmountService bookAmountService;


@Validations(requiredFields = { @RequiredFieldValidator(fieldName = "excelFile", key = "文件必须输入"), @RequiredFieldValidator(fieldName = "college", key = "操作员必须输入"), @RequiredFieldValidator(fieldName = "operator", key = "操作员必须输入"), @RequiredFieldValidator(fieldName = "memo", key = "操作备注必须输入") })
@Override
public String initTemplatePath(){
    return "booklist.xls";
}


public void setCollege(String college){
    this.college = college;
}


@Override
public void saveImportRecord(List<BookRequireUploadDTO> list){
// this.bookAmountService.saveTempBookRequireImportDetails(list,
// college, operator,
// memo);
}


public String getCollege(){
    return college;
}


public void setBookAmountService(BookAmountService bookAmountService){
    this.bookAmountService = bookAmountService;
}


@Override
public String getFaildRecordMessage(BookRequireUploadDTO failRecord){
    // TODO Auto-generated method stub
    return null;
}


public void setMemo(String memo){
    this.memo = memo;
}


@Override
public Logger initLogger(){
    return Logger.getLogger(BookRequireUploadAction.class);
}


public String getMemo(){
    return memo;
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


public void setOperator(String operator){
    this.operator = operator;
}


@Override
public BookRequireUploadDTO getInstance(){
    return new BookRequireUploadDTO();
}


public String getOperator(){
    return operator;
}


}