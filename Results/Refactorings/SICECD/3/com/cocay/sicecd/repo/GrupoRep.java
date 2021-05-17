import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Grupo;
import com.cocay.sicecd.model.Profesor;
@Repository
public interface GrupoRep extends PagingAndSortingRepository<Grupo, Integer> {


@Query("SELECT g FROM Grupo g " + "WHERE g.fecha_fin >= :fecha_ini " + "AND g.fecha_fin <= :fecha_fin " + "AND upper(g.clave) LIKE CONCAT('%',:clave,'%') ")
public List<Grupo> findByFechaFin(Date fecha_ini,Date fecha_fin,String clave)


@Query("SELECT g FROM Grupo g WHERE upper(g.clave) LIKE CONCAT('%',:clave,'%')")
public List<Grupo> findByClave(String clave)


@Modifying
@Query(value = "insert into Grupo (clave, fk_id_curso) VALUES (:clave, :fk_id_curso)", nativeQuery = true)
@Transactional
public void saveC(String clave,Integer fk_id_curso)


@Query("SELECT g FROM Grupo g " + "WHERE g.fecha_inicio >= :fecha_ini_1 " + "AND g.fecha_inicio <= :fecha_ini_2 " + "AND g.fecha_fin >= :fecha_fin_1 " + "AND g.fecha_fin <= :fecha_fin_2 " + "AND upper(g.clave) LIKE CONCAT('%',:clave,'%') ")
public List<Grupo> findByFecha(Date fecha_ini_1,Date fecha_ini_2,Date fecha_fin_1,Date fecha_fin_2,String clave)


@Query("SELECT g FROM Grupo g WHERE g.clave = :clave ")
public Grupo findForClave(String clave)


@Query(value = "SELECT * FROM Grupo WHERE fk_id_profesor = :id_asesor", nativeQuery = true)
public List<Grupo> findByIdAsesor(Integer id_asesor)


@Query("SELECT g FROM Grupo g")
public List<Grupo> findAll()


@Query("SELECT pk_id_grupo FROM Grupo")
public List<Grupo> findId()


@Query("SELECT g FROM Grupo g " + "WHERE g.fecha_inicio >= :fecha_ini " + "AND g.fecha_inicio <= :fecha_fin " + "AND upper(g.clave) LIKE CONCAT('%',:clave,'%') ")
public List<Grupo> findByFechaInicio(Date fecha_ini,Date fecha_fin,String clave)


@Query(value = "SELECT * FROM Grupo", nativeQuery = true)
public List<Grupo> loadAllGrupo()


@Query("SELECT g FROM Grupo g WHERE upper(g.clave) = :clave AND g.fk_id_curso = :curso")
public Grupo findByClaveGrupoIdCurso(String clave,Curso curso)


@Query("SELECT g FROM Grupo g WHERE upper(g.clave) = :clave")
public Grupo findByClaveGrupo(String clave)


public void setFk_id_grupo(int int,Grupo fk_id_grupo)

public Grupo getFk_id_grupo(int int)

public void setFk_id_grupo(int int,Grupo fk_id_grupo)

public Grupo getFk_id_grupo(int int)

public void setFk_id_grupo(int int,Grupo fk_id_grupo)

public Grupo getFk_id_grupo(int int)

public void setGrupos(int pk_id_curso,List<Grupo> grupos)

public void setFk_id_grupo(int pk_id_curso,Grupo fk_id_grupo)

public List<Grupo> getGrupos(int pk_id_curso)

public Grupo getFk_id_grupo(int pk_id_curso)

}