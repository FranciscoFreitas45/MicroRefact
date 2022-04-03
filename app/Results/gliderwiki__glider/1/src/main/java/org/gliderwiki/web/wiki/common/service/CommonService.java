package org.gliderwiki.web.wiki.common.service;
 import java.util.List;
import org.gliderwiki.web.domain.FavorityType;
import org.gliderwiki.web.domain.WeBbsComment;
import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeMenu;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeTemplate;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiComment;
import org.gliderwiki.web.domain.WeWikiFile;
import org.gliderwiki.web.domain.WeWikiGraph;
import org.gliderwiki.web.domain.WeWikiLink;
import org.gliderwiki.web.domain.WeWikiNote;
import org.gliderwiki.web.domain.WeWikiSummary;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.vo.WikiVo;
public interface CommonService {


public WeWiki getWikiInfo(Integer weWikiIdx)
;

public List<WeGroupInfo> getGroupList(Integer loginUserIdx)
;

public List<WeWikiNote> getWeWikiNoteList(Integer weWikiIdx,Integer weWikiRevision)
;

public WeWikiFile getWikiFileInfo(Integer weFileIdx)
;

public void delWeFile(Integer weFileIdx)
;

public WeGroupInfo getWeGroupInfo(Integer groupIdx)
;

public int addFavorite(Integer weUserIdx,FavorityType type,int spaceIdx,int wikiIdx)
;

public int delRelationDWR(Integer weUserIdx,Integer targetUserIdx)
;

public WeSpace getWikiSpaceInfoDWR(Integer weWikiSpaceIdx)
;

public WeWikiGraph getWeWikiGraph(Integer weWikiIdx,Integer weWikiRevision)
;

public WeTemplate getTemplateByIdx(Integer weTemplateIdx)
;

public List<WeWikiSummary> getWeWikiSummaryList(Integer weWikiIdx,Integer weWikiRevision)
;

public List<WikiVo> getWikiSearchList(String wiki_text)
;

public int insertWeFile(WeFile weFile)
;

public WeWikiComment getWeWikiComment(String weWikiCommentIdx)
;

public WeProfile getUserProfileInfo(Integer weUserIdx)
;

public String realNotiView(int userIdx)
;

public void changeRealTimeView(int userIdx,String isView)
;

public List<WeWikiTag> getWeWikiTagList(Integer weWikiIdx,Integer weWikiRevision)
;

public int delFavorite(Integer weUserIdx,FavorityType type,Integer addIdx)
;

public int delFavoriteDWR(Integer weUserIdx,String type,Integer addIdx)
;

public List<WeUser> getWeUserList(Integer loginUserIdx,String userNick,String userEmail,String userName)
;

public void updateAllRead(int userIdx)
;

public WeBbsComment getWeBbsComment(String weBbsCommentIdx)
;

public WeFile getUserFileInfo(Integer weFileIdx)
;

public List<WeWikiLink> getWeWikiLinkList(Integer weWikiIdx,Integer weWikiRevision)
;

public WeMenu getMenuInfo(Integer we_menu_idx)
;

public WeUser getUserInfo(Integer weUserIdx)
;

public void updateUserPoint(Integer we_ins_user,int point)
;

public StringBuffer getHtmlSourceByWikiIdx(Integer we_wiki_idx)
;

public int requestAlarmInfo(Integer we_login_user_idx,String we_user_nick,Integer we_meta_idx,Integer we_target_user_idx,Integer we_wiki_idx,Integer we_space_idx)
;

public List<WeWikiFile> getWeWikiFileList(Integer weWikiIdx,Integer weWikiRevision)
;

}