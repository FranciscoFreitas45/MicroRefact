package upce.semprace.eshop.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import upce.semprace.eshop.entity.Nakup;
import java.util.List;
public interface NakupRepository extends JpaRepository<Nakup, Long>{


@Query(value = "SELECT * FROM Nakup", nativeQuery = true)
public List<Nakup> findAdmin()
;

@Transactional
@Modifying
@Query("update Nakup u set u.stav = :status where u.id = :id")
public void zmenStav(boolean state,Long id)
;

public Set<Nakup> getNakup(Long id);

public void setNakup(Long id,Set<Nakup> nakup);

public Set<Nakup> getNakup(Long id);

public void setNakup(Long id,Set<Nakup> nakup);

public Set<Nakup> getNakup(Long id);

public void setNakup(Long id,Set<Nakup> nakup);

}