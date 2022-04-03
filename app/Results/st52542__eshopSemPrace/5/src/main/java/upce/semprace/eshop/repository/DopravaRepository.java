package upce.semprace.eshop.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import upce.semprace.eshop.entity.Doprava;
import java.util.List;
import java.util.Optional;
public interface DopravaRepository extends JpaRepository<Doprava, Long>{


public Optional<Doprava> findById(Long id)
;

public Doprava findByCena(Integer cena)
;

public void removeDopravaById(Long id)
;

public List<Doprava> findAll()
;

public void setDoprava(Long id,Doprava doprava);

public Doprava getDoprava(Long id);

}