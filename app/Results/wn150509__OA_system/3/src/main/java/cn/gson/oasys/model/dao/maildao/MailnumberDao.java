package cn.gson.oasys.model.dao.maildao;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import cn.gson.oasys.model.entity.mail.Mailnumber;
import cn.gson.oasys.model.entity.user.User;
public interface MailnumberDao extends PagingAndSortingRepository<Mailnumber, Long>{


public Page<Mailnumber> findByMailUserId(User user,Pageable page)
;

@Query("select mn from Mailnumber mn where  mn.mailUserName like %:val% and mn.mailUserId=:tu ")
public Page<Mailnumber> findByMailUserNameLikeAndMailUserId(String val,User tu,Pageable page)
;

public Page<Mailnumber> findByMailUserIdOrderByMailType(User user,Pageable page)
;

public List<Mailnumber> findByStatusAndMailUserId(Long status,User u)
;

public Page<Mailnumber> findByMailUserIdOrderByMailCreateTimeDesc(User user,Pageable page)
;

public Page<Mailnumber> findByMailUserIdOrderByStatus(User user,Pageable page)
;

}