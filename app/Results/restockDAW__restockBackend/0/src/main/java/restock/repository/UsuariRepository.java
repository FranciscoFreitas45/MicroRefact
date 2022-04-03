package restock.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import restock.entities.Rol;
import restock.entities.Usuari;
import java.util.List;
public interface UsuariRepository extends JpaRepository<Usuari, Integer>{


public Usuari findByUserAndRol(String user,Rol rol)
;

@Query(value = " " + "SELECT usu " + "FROM Usuari usu WHERE " + "(usu.nom 	LIKE CONCAT('%',(:camp),'%') OR " + "usu.cognom1 	LIKE CONCAT('%',(:camp),'%') OR " + "usu.cognom2 	LIKE CONCAT('%',(:camp),'%') OR " + "usu.nif 		LIKE CONCAT('%',(:camp),'%')) AND " + "usu.rol.id = 2 " + "ORDER BY usu.nom ASC")
public List<Usuari> cercaUsuari(String camp)
;

@Query(value = " " + "SELECT usu " + "FROM Usuari usu WHERE " + "(usu.rol.id = 2)")
public List<Usuari> findResponsables()
;

public Usuari findById(Integer usuariId)
;

public Usuari findByUserAndNifAndOrganitzacioId(String user,String nif,Integer organitacioId)
;

public List<Usuari> findAll()
;

public Usuari findByUser(String user)
;

public Usuari findByUserAndPassword(String user,String password)
;

@Query(value = " " + "SELECT usu " + "FROM Usuari usu WHERE " + "(usu.rol.id = 2 AND " + "usu.organitzacio.id =:organitzacioId )")
public List<Usuari> findResponsablesOfOrganitzacio(Integer OrgId)
;

public List<Usuari> findByOrganitzacioId(Integer OrgId)
;

@Query(value = " " + "SELECT usu " + "FROM Usuari usu WHERE " + "(usu.user =:user AND " + "usu.organitzacio.id =:organitzacioId )")
public Usuari findByUserAndOrganitzacioId(String user,Integer organitzacioId)
;

}