package upce.semprace.eshop.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import upce.semprace.eshop.entity.Platba;
import java.util.List;
import java.util.Optional;
public interface PlatbaRepository extends JpaRepository<Platba, Long>{


public Platba findByPrevod(Double prevod)
;

public Optional<Platba> findById(Long id)
;

public void removePlatbaById(Long id)
;

public List<Platba> findAll()
;

public Platba getPlatba(Long id);

public void setPlatba(Long id,Platba platba);

}