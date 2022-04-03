package cn.gson.oasys.model.dao.informdao;
 import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import cn.gson.oasys.model.entity.notice.NoticeUserRelation;
import cn.gson.oasys.model.entity.notice.NoticesList;
import cn.gson.oasys.model.entity.user.User;
public interface InformRelationDao extends PagingAndSortingRepository<NoticeUserRelation, Long>{


public NoticeUserRelation findByUserIdAndNoticeId(User userId,NoticesList notice)
;

public List<NoticeUserRelation> findByNoticeId(NoticesList notice)
;

public List<NoticeUserRelation> findByUserId(User userId)
;

public List<NoticeUserRelation> findByReadAndUserId(Boolean read,User userid)
;

}