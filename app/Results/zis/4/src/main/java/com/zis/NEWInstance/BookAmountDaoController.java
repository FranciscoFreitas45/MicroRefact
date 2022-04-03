package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BookAmountDaoController {

 private BookAmountDao bookamountdao;


@GetMapping
("/findByBookId")
public List<BookAmount> findByBookId(@RequestParam(name = "bookId") Integer bookId){
  return bookamountdao.findByBookId(bookId);
}


@GetMapping
("/distinctBookId")
public List<Integer> distinctBookId(){
  return bookamountdao.distinctBookId();
}


@GetMapping
("/findByBookIdGradeAndCollege")
public List<BookAmount> findByBookIdGradeAndCollege(@RequestParam(name = "bookId") Integer bookId){
  return bookamountdao.findByBookIdGradeAndCollege(bookId);
}


@GetMapping
("/findByBookIdListGradeAndCollege")
public List<BookAmount> findByBookIdListGradeAndCollege(@RequestParam(name = "bookId") List<Integer> bookId){
  return bookamountdao.findByBookIdListGradeAndCollege(bookId);
}


}