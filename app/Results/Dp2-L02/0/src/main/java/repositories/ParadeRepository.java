package repositories;
 import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Area;
import domain.Parade;
@Repository
public interface ParadeRepository extends JpaRepository<Parade, Integer>{


@Query("select p from Brotherhood b join b.parades p where p.paradeStatus = 'SUBMITTED' and p.isDraftMode=false and b.id = ?1")
public List<Parade> getSubmittedParadesByBrotherhood(int id)
;

@Query("select p from Brotherhood b join b.parades p where p.paradeStatus = 'REJECTED' and b.id = ?1")
public List<Parade> getRejectedParadesByBrotherhood(int id)
;

@Query("select p from Brotherhood b join b.parades p where p.paradeStatus = 'ACCEPTED' and b.area = ?1")
public List<Parade> getAcceptedParadesByChapter(Area area)
;

@Query("select p from Brotherhood b join b.parades p where p.isDraftMode = false and b.area = ?1")
public List<Parade> getParadesByArea(Area area)
;

@Query("select p from Brotherhood b join b.parades p where p.paradeStatus = 'ACCEPTED' and b.id = ?1")
public List<Parade> getAcceptedParadesByBrotherhood(int id)
;

@Query("select p from Parade p where paradeStatus = 'ACCEPTED'")
public Collection<Parade> getAcceptedParades()
;

@Query("select p from Brotherhood b join b.parades p where p.paradeStatus = 'SUBMITTED'and p.isDraftMode = false and b.area = ?1")
public List<Parade> getSubmittedParadesByChapter(Area area)
;

@Query("select p from Brotherhood b join b.parades p where p.paradeStatus = 'REJECTED' and b.area = ?1")
public List<Parade> getRejectedParadesByChapter(Area area)
;

@Query("select p from Parade p where isDraftMode = true")
public Collection<Parade> getDraftParades()
;

@Query("select p from Brotherhood b join b.parades p where p.isDraftMode = true and b.id = ?1")
public List<Parade> getDraftParadesByBrotherhood(int id)
;

}