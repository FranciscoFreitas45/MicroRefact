package com.zis.purchase.dto;
 import java.util.List;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.purchase.bean.TempImportDetail;
import com.zis.Interface.Bookinfo;
public class TempImportDetailView extends TempImportDetail{

 private  Bookinfo associateBook;

 private  List<Bookinfo> relatedBooks;


public void setRelatedBooks(List<Bookinfo> relatedBooks){
    this.relatedBooks = relatedBooks;
}


public Bookinfo getAssociateBook(){
    return associateBook;
}


public void setAssociateBook(Bookinfo associateBook){
    this.associateBook = associateBook;
}


public List<Bookinfo> getRelatedBooks(){
    return relatedBooks;
}


}