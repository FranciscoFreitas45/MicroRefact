package repositories;
 import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Finder;
import domain.Parade;
@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer>{


@Query("select p from Parade p where p.title like ?1 or p.description like ?1")
public List<Parade> getParadesByKeyWord(String keyWord)
;

@Query("select p from Brotherhood b join b.area a join b.parades p where a.name like ?1")
public List<Parade> getParadesByArea(String area)
;

@Query("select p from Parade p where p.moment < ?1")
public List<Parade> getParadesByMaxDate(Date date)
;

@Query("select p from Parade p where p.paradeStatus='ACCEPTED'")
public List<Parade> getPublushedParades()
;

@Query("select p from Parade p where p.moment > ?1")
public List<Parade> getParadesByMinDate(Date date)
;

}