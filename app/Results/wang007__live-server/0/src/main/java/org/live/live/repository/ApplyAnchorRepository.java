package org.live.live.repository;
 import net.sf.ehcache.search.impl.BaseResult;
import org.live.common.base.BaseRepository;
import org.live.live.entity.ApplyAnchor;
import org.live.live.entity.MobileUser;
import org.live.live.vo.ApplyInfoVo;
import org.live.live.vo.ApplyVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;
import java.util.Map;
public interface ApplyAnchorRepository extends BaseRepository<ApplyAnchor, String>{


public Page<ApplyVo> findApplys(PageRequest page,String searchVal,boolean auditFlag)
;

@Query("select max(a.createTime) from ApplyAnchor a where a.user.id=:userId")
public Date getLastApplyAnchorDate(String userId)
;

public List<ApplyAnchor> findApplyAnchorsByUser(MobileUser user)
;

@Query("select new org.live.live.vo.ApplyInfoVo(a.id, a.user.account, a.user.nickname, a.idCard," + " a.realName, a.user.member.realName, a.user.member.sex, a.category.categoryName, a.passFlag," + " a.noPassReason, a.createTime, a.idImgUrl, a.user.email, a.user.mobileNumber) from ApplyAnchor a where a.id=:applyId")
public ApplyInfoVo getApplyInfo(String applyId)
;

public long countApplyAnchorByUser_Id(String userId)
;

public void setRealName(String id,String realName);

public void setIdCard(String id,String idCard);

public void setCreateTime(String id,Date createTime);

public void setCategory(String id,LiveCategory category);

public void setIdImgUrl(String id,String idImgUrl);

}