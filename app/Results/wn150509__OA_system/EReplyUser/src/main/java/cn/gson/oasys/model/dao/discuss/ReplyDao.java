package cn.gson.oasys.model.dao.discuss;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import cn.gson.oasys.model.entity.discuss.Discuss;
import cn.gson.oasys.model.entity.discuss.Reply;
import cn.gson.oasys.model.entity.user.User;
public interface ReplyDao extends JpaRepository<Reply, Long>{


public List<Reply> findByDiscuss(Discuss discuss)
;

public Page<Reply> findByDiscussAndUser(Discuss discuss,User user,Pageable pa)
;

public void setUsers(Long id,Set<User> users);

}