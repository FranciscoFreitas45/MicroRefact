package com.zis.toolkit.action;
 import java.util.List;
import com.zis.toolkit.service.BookInfoToolkit;
public class BookEditionFixAction extends BaseBookFixAction{

 private  long serialVersionUID;

 private  BookInfoToolkit bookInfoToolkit;


public void setBookInfoToolkit(BookInfoToolkit bookInfoToolkit){
    this.bookInfoToolkit = bookInfoToolkit;
}


public BookInfoToolkit getBookInfoToolkit(){
    return bookInfoToolkit;
}


public String batchReplaceEditionByBookName(){
    List<String> list = bookInfoToolkit.updateReplaceEditionByBookName();
    showResult(list);
    return SUCCESS;
}


public String batchFixEditionByBookName(){
    List<String> list = bookInfoToolkit.updateFixEditionByBookName("修订版");
    List<String> list2 = bookInfoToolkit.updateFixEditionByBookName("修订本");
    list.addAll(list2);
    showResult(list);
    return SUCCESS;
}


}