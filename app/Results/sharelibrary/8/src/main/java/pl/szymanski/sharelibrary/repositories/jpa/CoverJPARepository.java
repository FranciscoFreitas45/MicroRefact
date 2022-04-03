package pl.szymanski.sharelibrary.repositories.jpa;
 import org.springframework.data.jpa.repository.JpaRepository;
import pl.szymanski.sharelibrary.entity.Cover;
import java.util.Optional;
public interface CoverJPARepository extends JpaRepository<Cover, Long>{


public Optional<Cover> findCoverById(Long id)
;

}