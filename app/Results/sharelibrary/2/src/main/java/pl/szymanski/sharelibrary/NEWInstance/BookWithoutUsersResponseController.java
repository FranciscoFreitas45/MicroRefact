package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BookWithoutUsersResponseController {

 private BookWithoutUsersResponse bookwithoutusersresponse;


@GetMapping
("/of")
public BookWithoutUsersResponse of(@RequestParam(name = "book") Book book){
  return bookwithoutusersresponse.of(book);
}


}