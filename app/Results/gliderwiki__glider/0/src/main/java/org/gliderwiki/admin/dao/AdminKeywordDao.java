package org.gliderwiki.admin.dao;
 import java.util.List;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.vo.KeywordVo;
public interface AdminKeywordDao {


public Integer updateKeywordWiki(WeWikiTag weWikiTag)
;

public List<KeywordVo> getKeywordList(KeywordVo keyword)
;

}