package upce.semprace.eshop.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import upce.semprace.eshop.entity.Doprava;
import java.util.List;
import java.util.Optional;
  import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

public interface DopravaRepository extends JpaRepository<Doprava, Long>{


public Optional<Doprava> findById(Long id)
;

public Doprava findByCena(Integer cena)
;

public void removeDopravaById(Long id)
;

public List<Doprava> findAll()
;


@Transactional
 @Modifying
@Query(value = "update doprava u set u.id = ?1 where u.id = ?1", nativeQuery = true)
public void setDoprava(Long id,Doprava doprava);

@Query(value = "Select * from doprava u  where u.id = ?1", nativeQuery = true)
public Doprava getDoprava(Long id);

}