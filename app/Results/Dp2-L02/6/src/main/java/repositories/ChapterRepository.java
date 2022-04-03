package repositories;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Area;
import domain.Chapter;
@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer>{


@Query("select a from Area a where a not in (select b.area from Chapter b where b.area is not null)")
public List<Area> getFreeAreas()
;

@Query("select a from Chapter a join a.userAccount b where b.username = ?1")
public Chapter getChapterByUsername(String a)
;

}