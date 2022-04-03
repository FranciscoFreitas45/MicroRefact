package restock.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import restock.entities.Proveidor;
public interface ProveidorRepository extends JpaRepository<Proveidor, Integer>{


public Proveidor findByNif(String nif)
;

public Proveidor findByNomAndOrganitzacioId(String nom,Integer organitzacioId)
;

@Query(value = " " + "SELECT prov " + "FROM Proveidor prov WHERE " + "(prov.nom 		LIKE CONCAT('%',(:camp),'%') OR " + "prov.nif 		LIKE CONCAT('%',(:camp),'%') OR " + "prov.poblacio	LIKE CONCAT('%',(:camp),'%')) AND " + "prov.organitzacio.id =:orgId " + "ORDER BY prov.nom ASC")
public List<Proveidor> cercaProveidor(String camp,Integer orgId)
;

public Proveidor findByNom(String nom)
;

public Proveidor findById(Integer id)
;

public List<Proveidor> findByOrganitzacioId(Integer organitzacioId)
;

}