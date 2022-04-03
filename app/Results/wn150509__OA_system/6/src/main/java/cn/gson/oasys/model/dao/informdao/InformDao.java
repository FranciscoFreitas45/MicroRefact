package cn.gson.oasys.model.dao.informdao;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import cn.gson.oasys.model.entity.notice.NoticeUserRelation;
import cn.gson.oasys.model.entity.notice.NoticeVO;
import cn.gson.oasys.model.entity.notice.NoticesList;
public interface InformDao extends JpaRepository<NoticesList, Long>{


@Query("from NoticesList n where n.userId = :userId and n.title like :baseKey")
public Page<NoticesList> findByBaseKey(Long userId,String baseKey,Pageable pageable)
;

@Query("SELECT n FROM NoticeUserRelation n left outer join n.noticeId u WHERE u.userId.userId=:userId ORDER BY u.top DESC ,u.modifyTime DESC ")
public List<NoticeUserRelation> findNoticeList(Long userId)
;

public Page<NoticesList> findByUserId(Long userId,Pageable pageable)
;

}