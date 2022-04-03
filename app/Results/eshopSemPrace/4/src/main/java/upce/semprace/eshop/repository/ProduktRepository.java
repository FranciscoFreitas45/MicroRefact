package upce.semprace.eshop.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upce.semprace.eshop.entity.Produkt;
import java.util.List;
import java.util.Optional;
  import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
public interface ProduktRepository extends JpaRepository<Produkt, Long>{


public Produkt findByNazev(String nazev)
;

public void removeProduktById(Long id)
;

public Optional<Produkt> findById(Long id)
;

@Query(value = "SELECT * FROM Produkt pr WHERE pr.v_nabidce = true ORDER BY pr.sleva_procenta DESC LIMIT 3", nativeQuery = true)
public List<Produkt> findTop()
;

public List<Produkt> findAll()
;

@Transactional
 @Modifying
@Query(value = "update produkt u set u.id = ?1 where u.id = ?1", nativeQuery = true)
public void setProdukt(Long id,Produkt produkt);

@Query(value = "Select * from produkt u  where u.id = ?1", nativeQuery = true)
public Produkt getProdukt(Long id);

}