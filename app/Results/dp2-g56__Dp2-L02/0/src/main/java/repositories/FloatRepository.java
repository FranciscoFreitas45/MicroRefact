package repositories;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Float;
@Repository
public interface FloatRepository extends JpaRepository<Float, Integer>{


@Query("select f from Brotherhood b join b.parades p join p.floats f where p.isDraftMode = false and b.id = ?1")
public List<domain.Float> getFloatsInParadeInFinalMode(int b)
;

}