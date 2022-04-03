package org.live.live.repository;
 import org.live.app.vo.SimpleUserVo;
import org.live.common.base.BaseRepository;
import org.live.live.entity.MobileUser;
import org.live.live.vo.MobileUserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Map;
public interface MobileUserRepository extends BaseRepository<MobileUser, String>{


@Query("select new org.live.app.vo.SimpleUserVo(u.id, u.account, u.nickname, u.headImgUrl) from MobileUser u " + "where u.account=:account")
public SimpleUserVo findMobileUserByAccountWithSimple(String account)
;

public Page<MobileUserVo> find(PageRequest pageRequest,Map<String,Object> filter)
;

public MobileUser findMobileUserByAccount(String account)
;

public MobileUser getUser(String id58IK);

public void setUser(String id58IK,MobileUser user);

public MobileUser getUser(String idQ2RW);

public void setUser(String idQ2RW,MobileUser user);

public MobileUser getUser(String id1YA2);

public void setUser(String id1YA2,MobileUser user);

public MobileUser getUser(String idL5CE);

public void setUser(String idL5CE,MobileUser user);

public MobileUser getUser(String idYUDA);

public void setUser(String idYUDA,MobileUser user);

public void setAnchorFlag(String id,boolean anchorFlag);

}