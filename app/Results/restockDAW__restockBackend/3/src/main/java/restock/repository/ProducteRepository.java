package restock.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import restock.entities.Producte;
public interface ProducteRepository extends JpaRepository<Producte, Integer>{


@Query(value = " " + "SELECT prod " + "FROM Producte prod WHERE " + "prod.marca =:marca AND " + "prod.model =:model AND " + "prod.subfamilia.id =:subfamiliaId AND " + "prod.proveidor.id =:proveidorId")
public Producte findByMarcaAndModelAndSubfamiliaAndProveidor(String marca,String model,Integer subfamiliaId,Integer proveidorId)
;

@Query(value = " " + "SELECT prod " + "FROM Producte prod WHERE " + "(prod.model 		LIKE CONCAT('%',(:camp),'%') OR " + "prod.marca 		LIKE CONCAT('%',(:camp),'%') OR " + "prod.descripcio 	LIKE CONCAT('%',(:camp),'%')) AND " + "prod.proveidor.organitzacio.id =:orgId " + "ORDER BY prod.marca ASC")
public List<Producte> cercaProducte(String camp,Integer orgId)
;

public List<Producte> findByProveidorId(Integer provId)
;

public Producte findById(Integer id)
;

@Query(value = " " + "SELECT prod " + "FROM Producte prod WHERE " + "prod.proveidor.organitzacio.id =:orgId " + "ORDER BY prod.marca ASC")
public List<Producte> findByOrganitzacioId(Integer orgId)
;

}