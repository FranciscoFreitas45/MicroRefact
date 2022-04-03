package org.live.live.service;
 import org.live.app.vo.AppAnchorInfo;
import org.live.common.base.BaseService;
import org.live.live.entity.Anchor;
import org.live.live.entity.MobileUser;
import org.live.live.vo.AnchorInfoVo;
import org.live.live.vo.AnchorVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface AnchorService extends BaseService<Anchor, String>{


public AppAnchorInfo findAnchorForAppUser(String userId,String liveRoomId)
;

public Anchor findAnchorByUser(MobileUser user)
;

public Page<AnchorVo> findAnchors(PageRequest page,String searchStr)
;

public AnchorInfoVo findAnchorInfo(String anchorId)
;

}