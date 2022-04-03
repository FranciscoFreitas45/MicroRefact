package pl.szymanski.sharelibrary.repositories.jpa;
 import org.springframework.data.jpa.repository.JpaRepository;
import pl.szymanski.sharelibrary.entity.Author;
import java.util.List;
import java.util.Optional;
public interface AuthorJPARepository extends JpaRepository<Author, Long>{


public Optional<Author> findAuthorByNameIgnoreCaseAndSurnameIgnoreCase(String name,String surname)
;

public List<Author> findAuthorsByNameIgnoreCaseOrSurnameIgnoreCase(String name,String surname)
;

}