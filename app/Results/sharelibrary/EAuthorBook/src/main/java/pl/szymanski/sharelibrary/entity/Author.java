package pl.szymanski.sharelibrary.entity;
 import lombok.Data;
import javax.persistence;
import java.util.List;
import java.util.Objects;
@Data
@Entity
@Table(name = "author")
public class Author {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String name;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String surname;

@ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
 private  List<Book> books;


@Override
public int hashCode(){
    return Objects.hash(id);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof Author))
        return false;
    Author author = (Author) o;
    return id.equals(author.id);
}


@Override
public String toString(){
    return "Author{" + "id=" + id + ", name='" + name + '\'' + ", surname='" + surname + '\'' + '}';
}


}