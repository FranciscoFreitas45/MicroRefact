package upce.semprace.eshop.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import upce.semprace.eshop.entity.Nakup;
import upce.semprace.eshop.DTO.*;
import java.util.*;
public interface NakupRepository extends JpaRepository<Nakup, Long>{


@Query(value = "SELECT * FROM Nakup", nativeQuery = true)
public List<Nakup> findAdmin()
;

@Transactional
@Modifying
@Query("update Nakup u set u.stav = :status where u.id = :id")
void zmenStav(@Param("status") boolean state, @Param("id") Long id);


@Query(value = "Select * from nakup u  where u.id = ?1", nativeQuery = true)
public Set<Nakup> getNakup(Long id);


@Transactional
 @Modifying
@Query(value = "update nakup u set u.id = ?1 where u.id = ?1", nativeQuery = true)
public void setNakup(Long id,Set<Nakup> nakup);




@Transactional
 @Modifying
@Query(value = "update nakup u set u.id = ?1 where u.id = ?1", nativeQuery = true)
public void setUzivatel(Long id,Uzivatel uzivatel);

@Transactional
 @Modifying
@Query(value = "update nakup u set u.id = ?1 where u.id = ?1", nativeQuery = true)
public void setPlatba(Long id,Platba platba);

@Transactional
 @Modifying
@Query(value = "update nakup u set u.id = ?1 where u.datum_vytvorer = ?2", nativeQuery = true)
public void setDatumVytvoreni(Long id,Date datumVytvoreni);
@Transactional
 @Modifying
@Query(value = "update nakup u set u.id = ?1 where u.objednavka = ?2", nativeQuery = true)
public void setObjednavka(Long id,Integer objednavka);

@Transactional
 @Modifying
@Query(value = "update nakup u set u.id = ?1 where u.stav = ?2", nativeQuery = true)
public void setStav(Long id,Boolean stav);

}