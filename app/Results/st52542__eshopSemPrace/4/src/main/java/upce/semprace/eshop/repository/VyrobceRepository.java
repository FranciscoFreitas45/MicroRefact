package upce.semprace.eshop.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import upce.semprace.eshop.entity.Vyrobce;
import java.util.List;
import java.util.Optional;
public interface VyrobceRepository extends JpaRepository<Vyrobce, Long>{


public Vyrobce findByNazev(String nazev)
;

public Optional<Vyrobce> findById(Long id)
;

public void removeVyrobceById(Long id)
;

public List<Vyrobce> findAll()
;

}