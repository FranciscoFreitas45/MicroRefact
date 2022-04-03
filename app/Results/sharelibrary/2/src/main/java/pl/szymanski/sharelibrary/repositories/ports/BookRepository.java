package pl.szymanski.sharelibrary.repositories.ports;
 import pl.szymanski.sharelibrary.entity.Book;
import java.util.List;
import java.util.Optional;
public interface BookRepository {


public List<Book> getBooks()
;

public Book saveBook(Book book)
;

public List<Book> findBooksByUserId(Long userId)
;

public List<Book> findBooksByTitle(String title)
;

public Optional<Book> getBookById(Long id)
;

}