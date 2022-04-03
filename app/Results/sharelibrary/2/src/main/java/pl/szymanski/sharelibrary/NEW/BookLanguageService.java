package pl.szymanski.sharelibrary.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.repositories.jpa.BookJPARepository;
import pl.szymanski.sharelibrary.entity.Book;
@Service
public class BookLanguageService {

@Autowired
 private BookJPARepository bookjparepository;


}