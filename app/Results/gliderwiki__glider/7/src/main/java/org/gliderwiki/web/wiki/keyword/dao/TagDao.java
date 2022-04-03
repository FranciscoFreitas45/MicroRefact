package org.gliderwiki.web.wiki.keyword.dao;
 import java.util.Date;
import java.util.List;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.vo.WikiTagVo;
import org.gliderwiki.web.vo.WikiVo;
public interface TagDao {


public List<WikiVo> getWikiListByTagIdx(List<Integer> wikiListIdx)
;

public List<Integer> getTagNameList(String tagName)
;

public List<WeSpace> getSpaceIdxByWikiIdx(List<Integer> wikiListIdx)
;

public List<WikiTagVo> getTagList(Date month,Integer maxLimit)
;

public List<WeSpace> getSpaceInfoBySpaceIdx(List<Integer> spaceListIdx)
;

}