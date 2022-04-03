package cn.gson.oasys.model.dao.maildao;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import cn.gson.oasys.model.entity.mail.Inmaillist;
import cn.gson.oasys.model.entity.user.User;
public interface InMailDao extends PagingAndSortingRepository<Inmaillist, Long>{


@Query("from Inmaillist as mail where mail.mailUserid=?1 and mail.push=?2 and mail.del=?3 and (mail.mailTitle like %?4% or mail.inReceiver like %?4%)" + "order by mail.mailCreateTime desc")
public Page<Inmaillist> findbymailUseridAndPushAndDel(User mu,Boolean b,Boolean bo,String title,Pageable pa)
;

public Inmaillist findByMailUseridAndMailId(User user,Long id)
;

public Page<Inmaillist> findByPushAndMailUseridAndDelOrderByMailCreateTimeDesc(Boolean b,User mu,Boolean del,Pageable pa)
;

public Page<Inmaillist> findByMailUseridAndMailTypeAndPushAndDelOrderByMailCreateTimeDesc(User mu,Long typeid,Boolean b,Boolean bo,Pageable pa)
;

public Page<Inmaillist> findByMailUseridAndMailStatusidAndPushAndDelOrderByMailCreateTimeDesc(User mu,Long statusid,Boolean b,Boolean bo,Pageable pa)
;

public List<Inmaillist> findByPushAndDelAndMailUserid(Boolean bo,Boolean del,User user)
;

}