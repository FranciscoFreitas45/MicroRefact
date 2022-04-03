package pl.szymanski.sharelibrary.utils.generator;
 import pl.szymanski.sharelibrary.entity.Book;
import pl.szymanski.sharelibrary.entity.UserBook;
import pl.szymanski.sharelibrary.enums.BookCondition;
import pl.szymanski.sharelibrary.enums.BookStatus;
import pl.szymanski.sharelibrary.requests.AddBookRequest;
import pl.szymanski.sharelibrary.requests.AssignBookRequest;
import pl.szymanski.sharelibrary.response.BookWithoutUsersResponse;
import pl.szymanski.sharelibrary.utils.constant.BookConstant;
import java.util.Collections;
import java.util.List;
public class BookGenerator {


public Book getBook(){
    Book book = new Book();
    book.setId(1L);
    book.setTitle(BookConstant.TEST_BOOK_TITLE);
    book.setAuthors(List.of(AuthorGenerator.getAuthor()));
    book.setCategories(List.of(CategoryGenerator.getCategory()));
    book.setLanguage(LanguageGenerator.getLanguage());
    book.setCondition(BookCondition.GOOD);
    return book;
}


public BookWithoutUsersResponse getBookWithoutUsersResponse(){
    return BookWithoutUsersResponse.of(getBook());
}


public UserBook getUserBook(){
    UserBook userBook = new UserBook();
    userBook.setBook(getBook());
    userBook.setStatus(BookStatus.AT_OWNER);
    return userBook;
}


public AddBookRequest getAddBookRequest(){
    return new AddBookRequest(BookConstant.TEST_BOOK_TITLE, Collections.singletonList(AuthorGenerator.getAuthorRequest()), Collections.singletonList(CategoryGenerator.getCategoryRequest()), LanguageGenerator.getLanguageRequest(), BookCondition.NEW.ordinal());
}


public AssignBookRequest getAssignBookRequest(){
    return new AssignBookRequest(1L, 1L);
}


}