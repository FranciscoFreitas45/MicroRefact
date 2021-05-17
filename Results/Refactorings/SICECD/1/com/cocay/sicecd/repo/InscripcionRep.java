import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cocay.sicecd.model.Grupo;
import com.cocay.sicecd.model.Inscripcion;
import com.cocay.sicecd.model.Profesor;
@Repository
public interface InscripcionRep extends PagingAndSortingRepository<Inscripcion, Integer> {


@Query("select  u.fk_id_grupo from Inscripcion u inner join u.fk_id_grupo ar where ar.clave =:clave")
public Inscripcion findIDGroup(String clave)


@Query("SELECT i FROM Inscripcion i where i.tempProfesor = :tempProfesor ")
public Inscripcion findByP(String tempProfesor)


@Query(value = "SELECT * FROM inscripcion", nativeQuery = true)
public List<Inscripcion> findAll()


@Modifying
@Query(value = "insert into Inscripcion (fk_id_grupo,fk_id_profesor,calif,aprobado) VALUES (:fk_id_grupo,:fk_id_profesor,:calif,:aprobado)", nativeQuery = true)
@Transactional
public void saveI(int fk_id_grupo,int fk_id_profesor,String calif,boolean aprobado)


public List<Inscripcion> getInscripciones(int pk_id_profesor)

public void setInscripciones(int pk_id_profesor,List<Inscripcion> inscripciones)

public List<Inscripcion> getInscripciones(int pk_id_grupo)

public void setInscripciones(int pk_id_grupo,List<Inscripcion> inscripciones)

}