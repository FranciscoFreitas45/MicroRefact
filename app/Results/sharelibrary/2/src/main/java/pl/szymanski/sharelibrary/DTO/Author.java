package pl.szymanski.sharelibrary.DTO;
 import lombok.Data;
import javax.persistence;
import java.util.List;
import java.util.Objects;
public class Author {

 private  Long id;

 private  String name;

 private  String surname;

 private  List<Book> books;


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof Author))
        return false;
    Author author = (Author) o;
    return id.equals(author.id);
}


}