package com.zis.Interface;
public interface BookService {

   public Bookinfo findBookById(int id);
   public BookinfoDetail findBookInfoDetailByBookId(Integer bookId);
}