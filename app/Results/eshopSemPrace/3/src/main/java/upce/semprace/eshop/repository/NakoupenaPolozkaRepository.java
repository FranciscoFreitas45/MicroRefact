package upce.semprace.eshop.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import upce.semprace.eshop.entity.NakoupenaPolozka;
import java.util.List;
import java.util.Optional;
import upce.semprace.eshop.DTO.*;
  import upce.semprace.eshop.entity.*;
  import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

public interface NakoupenaPolozkaRepository extends JpaRepository<NakoupenaPolozka, Long>{


public Optional<NakoupenaPolozka> findById(Long id)
;

public List<NakoupenaPolozka> findByNakupId(Long id)
;

@Transactional
 @Modifying
@Query(value = "update nakoupena_polozka u set u.id = ?1 where u.id = ?1", nativeQuery = true)
public void setNakoupenaPolozka(Long id,NakoupenaPolozka nakoupenaPolozka);

@Query(value = "Select * from nakoupena_polozka u  where u.id = ?1", nativeQuery = true)
public NakoupenaPolozka getNakoupenaPolozka(Long id);

@Transactional
 @Modifying
@Query(value = "update nakoupena_polozka u set u.id = ?1 where u.id = ?2", nativeQuery = true)
public void setMnozstvi(Long id,Integer mnozstvi);

@Transactional
 @Modifying
@Query(value = "update nakoupena_polozka u set u.id = ?1 where u.id = ?1", nativeQuery = true)
public void setNakup(Long id,Nakup nakup);

}