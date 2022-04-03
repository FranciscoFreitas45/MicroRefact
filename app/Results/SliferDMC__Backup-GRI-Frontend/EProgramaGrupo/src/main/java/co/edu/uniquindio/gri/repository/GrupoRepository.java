package co.edu.uniquindio.gri.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.gri.model.Grupo;
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>{


@Query("SELECT NEW co.edu.uniquindio.gri.model.Grupo(g.id, g.nombre, g.categoria, g.lider) FROM co.edu.uniquindio.gri.model.Grupo g join g.centro c join c.facultad f where f.id = :id")
public List<Grupo> getGruposFacultadC(Long facultadId)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.Grupo(g.id, g.nombre, g.categoria, g.lider) FROM co.edu.uniquindio.gri.model.Grupo g join g.programas p join p.facultad f where f.id = :id")
public List<Grupo> getGruposFacultadP(Long facultadId)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.Grupo(g.id, g.nombre, g.categoria, g.lider) FROM co.edu.uniquindio.gri.model.Grupo g join g.programas p where p.id = :id")
public List<Grupo> getGruposPrograma(Long programaId)
;

@Query("SELECT NEW co.edu.uniquindio.gri.model.Grupo(g.id, g.nombre, g.categoria, g.lider) FROM co.edu.uniquindio.gri.model.Grupo g where g.centro.id = :id")
public List<Grupo> getGruposCentro(Long centroId)
;

public void setGrupo(long id,List<Grupo> grupo);

public List<Grupo> getGrupo(long id);

}