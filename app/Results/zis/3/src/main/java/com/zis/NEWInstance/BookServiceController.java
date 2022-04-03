package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BookServiceController {

 private BookService bookservice;


@GetMapping
("/findBookById")
public Bookinfo findBookById(@RequestParam(name = "id") int id){
  return bookservice.findBookById(id);
}


@GetMapping
("/findBySpecification")
public List<Bookinfo> findBySpecification(@RequestParam(name = "spec") Specification<Bookinfo> spec){
  return bookservice.findBySpecification(spec);
}


@GetMapping
("/findAll")
public Page<Bookinfo> findAll(@RequestParam(name = "pageable") Pageable pageable){
  return bookservice.findAll(pageable);
}


@GetMapping
("/findShopItemByBookIdAndShopName")
public ShopItemInfo findShopItemByBookIdAndShopName(@RequestParam(name = "shopName") String shopName,@RequestParam(name = "bookId") Integer bookId){
  return bookservice.findShopItemByBookIdAndShopName(shopName,bookId);
}


@GetMapping
("/findBookInfoDetailByBookId")
public BookinfoDetail findBookInfoDetailByBookId(@RequestParam(name = "bookId") Integer bookId){
  return bookservice.findBookInfoDetailByBookId(bookId);
}


@GetMapping
("/captureBookInfoDetailFromNet")
public BookinfoDetail captureBookInfoDetailFromNet(@RequestParam(name = "bookId") Integer bookId){
  return bookservice.captureBookInfoDetailFromNet(bookId);
}


@GetMapping
("/findNormalBookById")
public Bookinfo findNormalBookById(@RequestParam(name = "id") int id){
  return bookservice.findNormalBookById(id);
}


@GetMapping
("/findBookByISBN")
public List<Bookinfo> findBookByISBN(@RequestParam(name = "isbn") String isbn){
  return bookservice.findBookByISBN(isbn);
}


@GetMapping
("/findByIdAndIsbn")
public Bookinfo findByIdAndIsbn(@RequestParam(name = "id") Integer id,@RequestParam(name = "isbn") String isbn){
  return bookservice.findByIdAndIsbn(id,isbn);
}


@GetMapping
("/findBySpecificationAll")
public List<Bookinfo> findBySpecificationAll(@RequestParam(name = "spec") Specification<Bookinfo> spec){
  return bookservice.findBySpecificationAll(spec);
}


@PutMapping
("/updateBook")
public void updateBook(@RequestParam(name = "book") Bookinfo book,@RequestParam(name = "detail") BookinfoDetail detail){
bookservice.updateBook(book,detail);
}


@GetMapping
("/findBookByCriteria")
public Object findBookByCriteria(@RequestParam(name = "Object") Object Object){
  return bookservice.findBookByCriteria(Object);
}


@PutMapping
("/saveShopItemForBatch")
public void saveShopItemForBatch(@RequestParam(name = "list") List<ShopItemInfoDTO> list){
bookservice.saveShopItemForBatch(list);
}


@PutMapping
("/updateTitleAndCategoryForBatch")
public void updateTitleAndCategoryForBatch(@RequestParam(name = "list") List<ShopItemInfoDTO> list){
bookservice.updateTitleAndCategoryForBatch(list);
}


@GetMapping
("/findBookInfoByBookNameLike")
public List<Bookinfo> findBookInfoByBookNameLike(@RequestParam(name = "bookName") String bookName){
  return bookservice.findBookInfoByBookNameLike(bookName);
}


}