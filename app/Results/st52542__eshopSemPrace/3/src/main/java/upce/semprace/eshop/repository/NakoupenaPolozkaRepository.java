package upce.semprace.eshop.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import upce.semprace.eshop.entity.NakoupenaPolozka;
import java.util.List;
import java.util.Optional;
public interface NakoupenaPolozkaRepository extends JpaRepository<NakoupenaPolozka, Long>{


public Optional<NakoupenaPolozka> findById(Long id)
;

public List<NakoupenaPolozka> findByNakupId(Long id)
;

public void setNakoupenaPolozka(Long id,NakoupenaPolozka nakoupenaPolozka);

public NakoupenaPolozka getNakoupenaPolozka(Long id);

}