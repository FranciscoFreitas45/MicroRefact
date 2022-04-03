package co.edu.uniquindio.gri.repository;
 import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.gri.model.Facultad;
@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Long>{


@Query(value = "SELECT count(distinct(i.id)) FROM gri.investigadores i join gri.grupos_inves gi ON i.id = gi.investigadores_id join gri.grupos g ON g.id = gi.grupos_id WHERE gi.estado = 'ACTUAL' UNION ALL SELECT count(*) FROM gri.grupos UNION ALL SELECT count(*) FROM gri.centros WHERE id<>0", nativeQuery = true)
public List<BigInteger> getStats()
;

@Query("FROM co.edu.uniquindio.gri.model.Facultad where id<>0")
public List<Facultad> findAll()
;

public void setFacultad(long id,Facultad facultad);

public Facultad getFacultad(long id);

}