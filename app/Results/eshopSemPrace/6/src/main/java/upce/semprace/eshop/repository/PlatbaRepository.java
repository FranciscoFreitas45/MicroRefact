package upce.semprace.eshop.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import upce.semprace.eshop.entity.Platba;
import java.util.List;
import java.util.Optional;
  import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
public interface PlatbaRepository extends JpaRepository<Platba, Long>{


public Platba findByPrevod(Double prevod)
;

public Optional<Platba> findById(Long id)
;

public void removePlatbaById(Long id)
;

public List<Platba> findAll()
;

@Query(value = "Select * from platba u  where u.id = ?1", nativeQuery = true)
public Platba getPlatba(Long id);
@Transactional
 @Modifying
@Query(value = "update platba u set u.id = ?1 where u.id = ?1", nativeQuery = true)
public void setPlatba(Long id,Platba platba);

}