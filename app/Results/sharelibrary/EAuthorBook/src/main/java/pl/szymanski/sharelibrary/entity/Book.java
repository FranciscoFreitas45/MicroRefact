package pl.szymanski.sharelibrary.entity;
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
@Entity
@Data
@Table(name = "book")
public class Book {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(nullable = false)
 private  String title;

@Column(nullable = false)
 private  BookCondition condition;

@Transient
 private  Language language;

@Transient
 private  List<Cover> cover;

@ManyToMany(cascade = ALL)
@JoinTable(joinColumns = @JoinColumn(name = "bookId"), inverseJoinColumns = @JoinColumn(name = "authorId"))
 private  List<Author> authors;

@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
 private  List<UserBook> users;

@ManyToMany(cascade = { PERSIST, MERGE, REFRESH })
@JoinTable(joinColumns = @JoinColumn(name = "bookId"), inverseJoinColumns = @JoinColumn(name = "categoryId"))
 private  List<Category> categories;

@Column(name = "id")
 private Integer id;

@Transient
 private LanguageRequest languagerequest = new LanguageRequestImpl();;

@Transient
 private CoverRequest coverrequest = new CoverRequestImpl();;


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