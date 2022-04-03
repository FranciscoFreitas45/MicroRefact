package org.gliderwiki.web.wiki.parser.service;
 import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeTemplate;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiBak;
import org.gliderwiki.web.domain.WeWikiComment;
import org.gliderwiki.web.vo.MemberSessionVo;
public interface WikiService {


public int addWikiAllContents(WeWiki weWiki,WeSpace weSpace,String weTag,String[] weFileIdx,HttpServletRequest request)
;

public WeWiki getWikiInfo(WeWiki wiki)
;

public List<WeTemplate> getWeTemplateList(WeTemplate temp)
;

public List<WeWiki> getWikiList(int spaceIdx)
;

public int addFavorite(int loginUserIdx,int spaceIdx,int wikiIdx)
;

public int addSubWikiAllContents(WeWiki weWiki,WeSpace weSpace,String weTag,String[] weFileIdx,HttpServletRequest request)
;

public int enableWikiEditor(WeWiki wiki)
;

public int insertWikiComment(WeWikiComment domain)
;

public int delWeWikiFile(Integer weFileIdx)
;

public WeWiki getWikiForEdit(WeWiki weWiki,MemberSessionVo loginUser)
;

public List<WeTemplate> getWeTemplateIdx(WeTemplate temp)
;

public int modifiedWikiAndSaveRevision(WeWiki wikiForm,WeSpace weSpace,String weTag,MemberSessionVo loginUser,HttpServletRequest request,String[] weFileIdx)
;

}