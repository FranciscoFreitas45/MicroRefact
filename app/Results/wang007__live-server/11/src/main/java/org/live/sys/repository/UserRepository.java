package org.live.sys.repository;
 import org.live.common.base.BaseRepository;
import org.live.sys.entity.User;
import org.live.sys.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface UserRepository extends BaseRepository<User, String>{


public List<User> findByUsername(String username)
;

public Page<UserVo> findUsers(Pageable pageable,UserVo userVo,boolean isDelete)
;

public void setLastLoginTime(String id,Date lastLoginTime);

public void setLastLoginIp(String id,String lastLoginIp);

}