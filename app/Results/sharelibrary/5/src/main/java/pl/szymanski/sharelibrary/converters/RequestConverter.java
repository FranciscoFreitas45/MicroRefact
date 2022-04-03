package pl.szymanski.sharelibrary.converters;
 import org.springframework.beans.BeanUtils;
import pl.szymanski.sharelibrary.entity;
import pl.szymanski.sharelibrary.enums.BookCondition;
import pl.szymanski.sharelibrary.requests;
import java.util.LinkedList;
import java.util.List;
public class RequestConverter {


public Category categoryRequestToCategory(CategoryRequest categoryRequest){
    Category category = new Category();
    BeanUtils.copyProperties(categoryRequest, category);
    return category;
}


public Language languageRequestToLanguage(LanguageRequest languageRequest){
    Language language = new Language();
    BeanUtils.copyProperties(languageRequest, language);
    return language;
}


public Book addBookRequestToBook(AddBookRequest addBookRequest){
    Book book = new Book();
    List<Author> authors = new LinkedList<>();
    addBookRequest.getAuthors().forEach(it -> authors.add(authorRequestToAuthor(it)));
    List<Category> categories = new LinkedList<>();
    addBookRequest.getCategories().forEach(it -> categories.add(categoryRequestToCategory(it)));
    book.setAuthors(authors);
    book.setCategories(categories);
    book.setLanguage(languageRequestToLanguage(addBookRequest.getLanguage()));
    book.setCondition(BookCondition.values()[addBookRequest.getConditionId()]);
    BeanUtils.copyProperties(addBookRequest, book, "authors", "image", "categories", "language");
    return book;
}


public Coordinates coordinatesRequestToCoordinates(CoordinatesRequest coordinatesRequest){
    Coordinates coordinates = new Coordinates();
    BeanUtils.copyProperties(coordinatesRequest, coordinates);
    return coordinates;
}


public Author authorRequestToAuthor(AuthorRequest authorRequest){
    Author author = new Author();
    BeanUtils.copyProperties(authorRequest, author);
    return author;
}


public Exchange addExchangeRequestToExchange(AddExchangeRequest addExchangeRequest){
    Exchange exchange = new Exchange();
    exchange.setCoordinates(coordinatesRequestToCoordinates(addExchangeRequest.getCoordinates()));
    BeanUtils.copyProperties(addExchangeRequest, exchange, "authors");
    return exchange;
}


public User userRequestToUser(UserRequest userRequest){
    User user = new User();
    user.setCoordinates(coordinatesRequestToCoordinates(userRequest.getCoordinates()));
    BeanUtils.copyProperties(userRequest, user);
    return user;
}


}