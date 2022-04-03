package pl.szymanski.sharelibrary.repositories.adapters;
 import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.szymanski.sharelibrary.entity.Book;
import pl.szymanski.sharelibrary.entity.Cover;
import pl.szymanski.sharelibrary.repositories.jpa.BookJPARepository;
import pl.szymanski.sharelibrary.repositories.ports.BookRepository;
import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository{

 private  BookJPARepository bookJPARepository;


@Override
public List<Book> getBooks(){
    return bookJPARepository.findAll();
}


@Override
public Book saveBook(Book book){
    for (Cover cover : book.getCover()) {
        cover.setBook(book);
    }
    return bookJPARepository.saveAndFlush(book);
}


@Override
public List<Book> findBooksByUserId(Long userId){
    return bookJPARepository.findByUserId(userId);
}


@Override
public List<Book> findBooksByTitle(String title){
    return bookJPARepository.findByTitleIsContainingIgnoreCase(title);
}


@Override
public Optional<Book> getBookById(Long id){
    return bookJPARepository.findById(id);
}


}