package co.edu.uniquindio.gri.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.gri.model.Programa;
@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Long>{


@Query("FROM co.edu.uniquindio.gri.model.Programa WHERE id<>0 and facultad.id = :id")
public List<Programa> getProgramasFacultad(Long idFacultad)
;

public void setPrograma(long id,List<Programa> programa);

public List<Programa> getPrograma(long id);

}