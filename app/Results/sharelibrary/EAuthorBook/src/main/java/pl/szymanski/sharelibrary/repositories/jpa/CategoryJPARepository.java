package pl.szymanski.sharelibrary.repositories.jpa;
 import org.springframework.data.jpa.repository.JpaRepository;
import pl.szymanski.sharelibrary.entity.Category;
import java.util.Optional;
public interface CategoryJPARepository extends JpaRepository<Category, Integer>{


public Optional<Category> findFirstByNameIgnoreCase(String name)
;

}