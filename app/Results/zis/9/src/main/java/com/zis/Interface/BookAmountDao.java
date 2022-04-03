package com.zis.Interface;
public interface BookAmountDao {

   public List<BookAmount> findByBookIdGradeAndCollege(Integer bookId);
   public List<BookAmount> findByBookIdListGradeAndCollege(List<Integer> bookId);
}