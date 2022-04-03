package pl.szymanski.sharelibrary.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.entity.Book;
@RestController
@CrossOrigin
public class BookExchangeController {

@Autowired
 private BookExchangeService bookexchangeservice;


}