package pl.szymanski.sharelibrary.entity;
 import lombok.Data;
import javax.persistence;
import java.util.List;
import java.util.Objects;
import pl.szymanski.sharelibrary.Request.BookRequest;
import pl.szymanski.sharelibrary.Request.Impl.BookRequestImpl;
import pl.szymanski.sharelibrary.DTO.Book;
@Entity
@Table(name = "language")
@Data
public class Language {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;

@Column(nullable = false)
 private  String name;

@Transient
 private  List<Book> books;

@Transient
 private BookRequest bookrequest = new BookRequestImpl();;


@Override
public int hashCode(){
    return Objects.hash(id);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof Language))
        return false;
    Language language = (Language) o;
    return id.equals(language.id);
}


@Override
public String toString(){
    return "Language{" + "id=" + id + ", name='" + name + '\'' + '}';
}


}