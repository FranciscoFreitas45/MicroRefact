package org.gliderwiki.Interface;
public interface WikiService {

   public List<WeTemplate> getWeTemplateIdx(WeTemplate temp);
   public List<WeWiki> getWikiList(int spaceIdx);
   public WeWiki getWikiForEdit(WeWiki weWiki,MemberSessionVo loginUser);
   public int addWikiAllContents(WeWiki weWiki,WeSpace weSpace,String weTag,String[] weFileIdx,HttpServletRequest request);
   public int modifiedWikiAndSaveRevision(WeWiki wikiForm,WeSpace weSpace,String weTag,MemberSessionVo loginUser,HttpServletRequest request,String[] weFileIdx);
   public int addSubWikiAllContents(WeWiki weWiki,WeSpace weSpace,String weTag,String[] weFileIdx,HttpServletRequest request);
   public int delWeWikiFile(Integer weFileIdx);
   public int insertWikiComment(WeWikiComment domain);
   public int addFavorite(int loginUserIdx,int spaceIdx,int wikiIdx);
}