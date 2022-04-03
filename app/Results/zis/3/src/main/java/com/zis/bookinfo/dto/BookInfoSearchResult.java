package com.zis.bookinfo.dto;
 import java.util.ArrayList;
import java.util.List;
import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.beans.BeanUtils;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.BookinfoDetail;
import com.zis.bookinfo.util.BookMetadata;
import com.zis.Interface.BookMetadata;
@DataTransferObject
public class BookInfoSearchResult {

 private  boolean isSysData;

 private  List<BookInfoAndDetailDTO> booksExist;

 private  BookMetadata bookCaptured;


public boolean isSysData(){
    return isSysData;
}


public void setBookCaptured(BookMetadata bookCaptured){
    this.bookCaptured = bookCaptured;
}


public void addBookExist(Bookinfo book,BookinfoDetail detail){
    BookInfoAndDetailDTO record = new BookInfoAndDetailDTO();
    BeanUtils.copyProperties(book, record);
    if (detail != null) {
        BeanUtils.copyProperties(detail, record);
    }
    this.booksExist.add(record);
}


public List<BookInfoAndDetailDTO> getBooksExist(){
    return booksExist;
}


public BookMetadata getBookCaptured(){
    return bookCaptured;
}


public void setSysData(boolean isSysData){
    this.isSysData = isSysData;
}


}