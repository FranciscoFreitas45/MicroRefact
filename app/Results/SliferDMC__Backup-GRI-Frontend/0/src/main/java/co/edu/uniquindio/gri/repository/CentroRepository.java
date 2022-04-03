package co.edu.uniquindio.gri.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.gri.model.Centro;
@Repository
public interface CentroRepository extends JpaRepository<Centro, Long>{


@Query("FROM co.edu.uniquindio.gri.model.Centro WHERE id<>0")
public List<Centro> findAll()
;

public Centro getCentro(long id);

public void setCentro(long id,Centro centro);

}