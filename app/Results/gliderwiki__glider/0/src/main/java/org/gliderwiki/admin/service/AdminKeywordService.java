package org.gliderwiki.admin.service;
 import java.util.List;
import org.gliderwiki.web.vo.KeywordVo;
public interface AdminKeywordService {


public List<KeywordVo> getMoreKeyword(Integer startRow,Integer endRow)
;

public int deleteKeywordWiki(Integer wikiIdx,Integer wikiRevision,Integer weUserIdx)
;

public List<KeywordVo> getKeywordList(KeywordVo keyword)
;

public int deleteKeyword(Integer weWikiTagIdx,Integer weUserIdx)
;

}