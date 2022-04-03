package pl.szymanski.sharelibrary.exceptions.covers;
 import pl.szymanski.sharelibrary.exceptions.ExceptionMessages;
public class CoverForBookDoesNotExists extends RuntimeException{

public CoverForBookDoesNotExists(Long id) {
    super(String.format(ExceptionMessages.COVER_FOR_BOOK_NOT_EXISTS, id));
}
}