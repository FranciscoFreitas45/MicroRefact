package com.zis.Interface;
public interface BookService {

   public List<Bookinfo> findBookInfoByBookNameLike(String bookName);
   public List<Bookinfo> findBookByISBN(String isbn);
}