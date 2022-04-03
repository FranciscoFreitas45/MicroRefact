package org.gliderwiki.web.user.service;
 import java.util.List;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.WikiFavoriteVo;
public interface UserFavorService {


public List<WikiFavoriteVo> getMyFavoriteWikiList(Integer weUserIdx)
;

public List<WikiFavoriteVo> getMyFavoriteSpaceList(Integer weUserIdx)
;

}