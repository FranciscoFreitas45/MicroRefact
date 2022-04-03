package org.gliderwiki.Interface;
public interface UserFavorService {

   public List<WikiFavoriteVo> getMyFavoriteSpaceList(Integer weUserIdx);
   public List<WikiFavoriteVo> getMyFavoriteWikiList(Integer weUserIdx);
}