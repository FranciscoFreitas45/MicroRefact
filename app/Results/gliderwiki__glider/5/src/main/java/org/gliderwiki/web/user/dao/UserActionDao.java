package org.gliderwiki.web.user.dao;
 import java.util.List;
import org.gliderwiki.web.domain.WeSpaceJoin;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.WikiLogVo;
public interface UserActionDao {


public List<WeSpaceJoin> getUserSpaceJoinList(WeSpaceJoin joinDomain,int type)
;

public List<WikiLogVo> getSpaceInfoByIdx(List<Integer> wikiSpaceIdxList)
;

public List<WikiLogVo> getMyWikiLogAction(MemberSessionVo memberSessionVo)
;

}