package org.live.live.repository;
 import org.live.common.base.BaseRepository;
import org.live.live.entity.Anchor;
import org.live.live.entity.MobileUser;
import org.live.live.vo.AnchorInfoVo;
import org.live.live.vo.AnchorVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface AnchorRepository extends BaseRepository<Anchor, String>{


public Anchor findAnchorByUser(MobileUser user)
;

public Page<AnchorVo> findAnchors(PageRequest page,String searchStr)
;

@Query("select new org.live.live.vo.AnchorInfoVo(a.id,a.user.account, a.user.nickname, a.realName, a.user.headImgUrl, a.idCard, a.createTime, a.description, a.user.email, a.user.mobileNumber, a.user.lockFlag) from Anchor a where a.id=:anchorId")
public AnchorInfoVo findAnchorInfo(String anchorId)
;

public Anchor getAnchor(String idFK1I);

public void setAnchor(String idFK1I,Anchor anchor);

public void setCreateTime(String id,Date createTime);

public void setRealName(String id,String realName);

public void setIdCard(String id,String idCard);

public void setDescription(String id,String description);

}