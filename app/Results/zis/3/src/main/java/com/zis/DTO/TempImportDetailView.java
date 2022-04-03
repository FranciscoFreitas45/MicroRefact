package com.zis.DTO;
 import java.util.List;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.purchase.bean.TempImportDetail;
public class TempImportDetailView extends TempImportDetail{

 private  Bookinfo associateBook;

 private  List<Bookinfo> relatedBooks;


public Bookinfo getAssociateBook(){
    return associateBook;
}


public List<Bookinfo> getRelatedBooks(){
    return relatedBooks;
}


}