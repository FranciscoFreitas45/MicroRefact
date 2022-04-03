package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BookServiceController {

 private BookService bookservice;


@GetMapping
("/findBookById")
public Book findBookById(@RequestParam(name = "id") Long id){
  return bookservice.findBookById(id);
}


}