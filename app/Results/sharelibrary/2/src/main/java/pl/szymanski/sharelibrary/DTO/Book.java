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

 private Integer id;


public boolean isAuthorsListEquals(List<Author> authors){
    for (int i = 0; i < authors.size(); i++) {
        if (!this.authors.get(i).getId().equals(authors.get(i).getId())) {
            return false;
        }
    }
    return true;
}


@Override
public int hashCode(){
    return Objects.hash(title, language, getHashCodeForAuthorList());
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof Book))
        return false;
    Book book = (Book) o;
    return title.equals(book.title) && language.equals(book.language) && isAuthorsListEquals(book.getAuthors());
}


@Override
public String toString(){
    return "Book{" + "id=" + id + ", title='" + title + '\'' + ", authors=" + authors + ", language=" + language + '}';
}


public int getHashCodeForAuthorList(){
    return authors.stream().mapToInt(Author::hashCode).sum();
}


}