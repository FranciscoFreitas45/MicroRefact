package org.gliderwiki.web.wiki.common.dao;
 import java.util.List;
import org.gliderwiki.web.domain.WeAddFriend;
import org.gliderwiki.web.domain.WeFavorite;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.vo.WikiVo;
public interface CommonDao {


public void notiAllRead(int userIdx)
;

public List<WeGroupInfo> getGroupList(WeGroupInfo domain)
;

public String realNotiView(int userIdx)
;

public void changeRealTimeView(int userIdx,String isView)
;

public int delRelationDWR(WeAddFriend weAddFriend)
;

public int updateFavorite(WeFavorite weFavorite)
;

public List<WikiVo> getWikiSearchList(String wiki_text)
;

public int delFavorite(WeFavorite weFavorite)
;

public void updateUserPoint(WeProfile domain)
;

}