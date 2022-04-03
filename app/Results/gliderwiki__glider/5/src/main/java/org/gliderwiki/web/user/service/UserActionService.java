package org.gliderwiki.web.user.service;
 import java.util.List;
import org.gliderwiki.web.domain.JoinStatus;
import org.gliderwiki.web.domain.WeSpaceJoin;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.WikiLogVo;
public interface UserActionService {


public List<WeSpaceJoin> getUserSpaceJoinList(WeSpaceJoin joinDomain,int type)
;

public String cancelJoinToSpaceDWR(Integer we_user_idx,Integer we_space_join_idx,Integer we_space_idx,String joinStatus)
;

public List<WikiLogVo> getSpaceInfoByIdx(List<Integer> wikiSpaceIdxList)
;

public List<WikiLogVo> getMyWikiLogAction(MemberSessionVo memberSessionVo)
;

}