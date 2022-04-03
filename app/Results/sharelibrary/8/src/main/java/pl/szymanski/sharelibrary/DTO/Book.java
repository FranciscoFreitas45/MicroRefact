package pl.szymanski.sharelibrary.DTO;
 import lombok.Data;
import pl.szymanski.sharelibrary.enums.BookCondition;
import javax.persistence;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import pl.szymanski.sharelibrary.Request.LanguageRequest;
import pl.szymanski.sharelibrary.Request.Impl.LanguageRequestImpl;
import pl.szymanski.sharelibrary.DTO.Language;
import pl.szymanski.sharelibrary.Request.CoverRequest;
import pl.szymanski.sharelibrary.Request.Impl.CoverRequestImpl;
import pl.szymanski.sharelibrary.DTO.Cover;
public class Book {

 private  Long id;

 private  String title;

 private  BookCondition condition;

 private  Language language;

 private  List<Cover> cover;

 private  List<Author> authors;

 private  List<UserBook> users;

 private  List<Category> categories;

 private Integer id;


public int getHashCodeForAuthorList(){
    return authors.stream().mapToInt(Author::hashCode).sum();
}


}