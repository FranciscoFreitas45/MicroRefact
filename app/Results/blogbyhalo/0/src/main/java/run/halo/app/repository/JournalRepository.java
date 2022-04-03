package run.halo.app.repository;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.Journal;
import run.halo.app.model.enums.JournalType;
import run.halo.app.repository.base.BaseRepository;
public interface JournalRepository extends BaseRepository<Journal, Integer>, JpaSpecificationExecutor<Journal>{


@Modifying
@Query("update Journal j set j.likes = j.likes + :likes where j.id = :id")
public int updateLikes(long likes,Integer id)
;

@NonNull
public Page<Journal> findAllByType(JournalType type,Pageable pageable)
;

}