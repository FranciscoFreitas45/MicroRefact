package com.zis.bookinfo.action;
 import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.zis.bookinfo.service.BookService;
public class AddYouLuDataAction extends ActionSupport{

 private  long serialVersionUID;

 private  BookService bookService;

 private  Integer startId;

 private  Integer finalId;

 private  String operateType;


public void setStartId(Integer startId){
    this.startId = startId;
}


public String getOperateType(){
    return operateType;
}


public BookService getBookService(){
    return bookService;
}


public Integer getFinalId(){
    return finalId;
}


@Validations(intRangeFields = { @IntRangeFieldValidator(fieldName = "startId", min = "1", key = "开始ID不能小于1"), @IntRangeFieldValidator(fieldName = "finalId", min = "1", key = "结束ID不能小于1") }, requiredFields = { @RequiredFieldValidator(fieldName = "startId", key = "开始ID不能为空"), @RequiredFieldValidator(fieldName = "finalId", key = "结束ID不能为空") })
public String addYouLuData(){
    if (startId <= finalId) {
        Integer activeTaskCount = bookService.asynchronousCaptureBookInfoFromYouLuNet(startId, finalId, operateType);
        ActionContext context = ActionContext.getContext();
        if (activeTaskCount <= 1)
            activeTaskCount = 1;
        context.put("activeTaskCount", activeTaskCount + "");
    }
    return SUCCESS;
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


public void setFinalId(Integer finalId){
    this.finalId = finalId;
}


public void setOperateType(String operateType){
    this.operateType = operateType;
}


public Integer getStartId(){
    return startId;
}


}