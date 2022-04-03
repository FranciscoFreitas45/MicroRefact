package pl.szymanski.sharelibrary.services.ports;
 import org.springframework.web.multipart.MultipartFile;
import pl.szymanski.sharelibrary.entity.Author;
import pl.szymanski.sharelibrary.entity.Book;
import pl.szymanski.sharelibrary.entity.Language;
import pl.szymanski.sharelibrary.response.UserBookResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
public interface BookService {


public List<Book> getBooksByTitle(String title)
;

public List<Book> getBooksByAuthorNameAndSurname(Author author)
;

public List<Book> getBooks(String query)
;

public Book saveBook(Book book,MultipartFile cover,Long userId)
;

public Set<Language> getLanguages()
;

public Book findBookById(Long id)
;

public List<UserBookResponse> findBooksByUserId(Long userId)
;

}