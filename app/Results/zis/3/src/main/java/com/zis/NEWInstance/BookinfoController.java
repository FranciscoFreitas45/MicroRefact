package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BookinfoController {

 private Bookinfo bookinfo;


@PutMapping
("/setBookName/{id}")
public void setBookName(@PathVariable(name = "id") Integer id,@RequestParam(name = "bookName") String bookName){
 bookinfodao.setBookName(id,bookName);
}


@PutMapping
("/setBookEdition/{id}")
public void setBookEdition(@PathVariable(name = "id") Integer id,@RequestParam(name = "bookEdition") String bookEdition){
 bookinfodao.setBookEdition(id,bookEdition);
}


}