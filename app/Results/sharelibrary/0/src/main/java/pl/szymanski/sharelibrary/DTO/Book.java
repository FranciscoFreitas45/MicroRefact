package pl.szymanski.sharelibrary.DTO;
 import lombok.Data;
import pl.szymanski.sharelibrary.enums.BookCondition;
import javax.persistence;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
public class Book {

 private  Long id;

 private  String title;

 private  BookCondition condition;

 private  Language language;

 private  List<Cover> cover;

 private  List<Author> authors;

 private  List<UserBook> users;

 private  List<Category> categories;


public int getHashCodeForAuthorList(){
    return authors.stream().mapToInt(Author::hashCode).sum();
}


}