package com.zis.requirement.dto;
 import java.util.ArrayList;
import java.util.List;
import org.directwebremoting.annotations.DataTransferObject;
import com.zis.bookinfo.bean.Bookinfo;
@DataTransferObject
public class AddBookToDepartmentResult {

 private  boolean success;

 private  String failReason;

 private  List<Bookinfo> bookList;

public AddBookToDepartmentResult(boolean success, String failReason) {
    this.success = success;
    this.failReason = failReason;
}
public String getFailReason(){
    return failReason;
}


public void add(Bookinfo book){
    bookList.add(book);
}


public void setSuccess(boolean success){
    this.success = success;
}


public void setFailReason(String failReason){
    this.failReason = failReason;
}


public List<Bookinfo> getBookList(){
    return bookList;
}


public boolean getSuccess(){
    return success;
}


}