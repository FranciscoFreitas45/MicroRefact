package com.zis.toolkit.action;
 import java.util.List;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.zis.toolkit.service.BookInfoToolkit;
public class BookNameReplaceAction extends BaseBookFixAction{

 private  long serialVersionUID;

 private  String orig;

 private  String replace;

 private  BookInfoToolkit bookInfoToolkit;


@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "orig", trim = true, key = "原始内容不能为空") })
public String batchReplaceBookName(){
    List<String> list = bookInfoToolkit.updateBatchReplaceBookName(orig, replace);
    showResult(list);
    return SUCCESS;
}


public String getOrig(){
    return orig;
}


public void setBookInfoToolkit(BookInfoToolkit bookInfoToolkit){
    this.bookInfoToolkit = bookInfoToolkit;
}


public String getReplace(){
    return replace;
}


public void setReplace(String replace){
    this.replace = replace;
}


public BookInfoToolkit getBookInfoToolkit(){
    return bookInfoToolkit;
}


public void setOrig(String orig){
    this.orig = orig;
}


}