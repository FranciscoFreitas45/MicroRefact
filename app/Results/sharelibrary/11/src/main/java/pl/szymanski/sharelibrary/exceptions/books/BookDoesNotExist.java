package pl.szymanski.sharelibrary.exceptions.books;
 import pl.szymanski.sharelibrary.exceptions.ExceptionMessages;
public class BookDoesNotExist extends RuntimeException{

public BookDoesNotExist(Long id) {
    super(String.format(ExceptionMessages.BOOK_DOES_NOT_EXIST_EXCEPTION_FORMAT, id));
}
}