package pl.szymanski.sharelibrary.repositories.ports;
 import pl.szymanski.sharelibrary.entity.Author;
import java.util.List;
import java.util.Optional;
public interface AuthorRepository {


public List<Author> findAuthorByNameOrSurname(String name,String surname)
;

public Optional<Author> findAuthorByNameAndSurname(String name,String surname)
;

}