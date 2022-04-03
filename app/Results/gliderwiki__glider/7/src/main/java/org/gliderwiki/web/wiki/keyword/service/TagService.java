package org.gliderwiki.web.wiki.keyword.service;
 import java.util.List;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.vo.WikiTagVo;
import org.gliderwiki.web.vo.WikiVo;
public interface TagService {


public List<WikiVo> getWikiListByTagIdx(List<Integer> wikiListIdx)
;

public List<Integer> getTagNameList(String tagName)
;

public List<WeSpace> getSpaceIdxByWikiIdx(List<Integer> wikiListIdx)
;

public List<WikiVo> createWikiListWithTag(String tagName)
;

public List<WeSpace> createSpaceListWithTag(List<WikiVo> wikiInfoList)
;

public List<WikiTagVo> getTagList()
;

public List<WeSpace> getSpaceInfoBySpaceIdx(List<Integer> spaceListIdx)
;

}