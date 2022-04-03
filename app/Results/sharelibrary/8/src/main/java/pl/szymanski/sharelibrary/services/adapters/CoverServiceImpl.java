package pl.szymanski.sharelibrary.services.adapters;
 import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.szymanski.sharelibrary.entity.Cover;
import pl.szymanski.sharelibrary.exceptions.covers.CoverForBookDoesNotExists;
import pl.szymanski.sharelibrary.services.ports.BookService;
import pl.szymanski.sharelibrary.services.ports.CoverService;
@RequiredArgsConstructor
@Service
public class CoverServiceImpl implements CoverService{

 private  BookService bookService;


@Override
public Cover getCoverByBookId(Long id){
    return bookService.findBookById(id).getCover().stream().findFirst().orElseThrow(() -> new CoverForBookDoesNotExists(id));
}


}