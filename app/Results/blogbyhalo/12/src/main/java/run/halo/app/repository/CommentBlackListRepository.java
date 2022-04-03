package run.halo.app.repository;
 import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import run.halo.app.model.entity.CommentBlackList;
import run.halo.app.repository.base.BaseRepository;
public interface CommentBlackListRepository extends BaseRepository<CommentBlackList, Long>{


public Optional<CommentBlackList> findByIpAddress(String ipAddress)
;

@Modifying
@Query("UPDATE CommentBlackList SET banTime=:#{#commentBlackList.banTime} WHERE " + "ipAddress=:#{#commentBlackList.ipAddress}")
public int updateByIpAddress(CommentBlackList commentBlackList)
;

}