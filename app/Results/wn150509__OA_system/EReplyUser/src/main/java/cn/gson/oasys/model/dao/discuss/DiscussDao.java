package cn.gson.oasys.model.dao.discuss;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import cn.gson.oasys.model.entity.discuss.Discuss;
import cn.gson.oasys.model.entity.user.User;
public interface DiscussDao extends JpaRepository<Discuss, Long>{


public Page<Discuss> findByTitleLike(String title,Pageable pa)
;

public Page<Discuss> findByUser(User user,Pageable pa)
;

public Page<Discuss> findByUserAndTitleLike(User user,String title,Pageable pa)
;

public void setVisitNum(Long id,Integer visitNum);

public void setUsers(Long id,Set<User> users);

public void setUser(Long id,User user);

public void setModifyTime(Long id,Date modifyTime);

public void setTitle(Long id,String title);

public void setContent(Long id,String content);

}