package com.zis.storage.dto;
 import com.zis.bookinfo.bean.Bookinfo;
import com.zis.storage.entity.StorageProduct;
import com.zis.Interface.Bookinfo;
public class StorageProductDTO extends StorageProduct{

 private  Bookinfo book;


public Bookinfo getBook(){
    return book;
}


public void setBook(Bookinfo book){
    this.book = book;
}


}