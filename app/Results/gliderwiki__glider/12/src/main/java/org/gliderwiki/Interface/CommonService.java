package org.gliderwiki.Interface;
public interface CommonService {

   public WeWiki getWikiInfo(Integer weWikiIdx);
   public List<WeWikiTag> getWeWikiTagList(Integer weWikiIdx,Integer weWikiRevision);
   public List<WeWikiNote> getWeWikiNoteList(Integer weWikiIdx,Integer weWikiRevision);
   public List<WeWikiLink> getWeWikiLinkList(Integer weWikiIdx,Integer weWikiRevision);
   public List<WeWikiFile> getWeWikiFileList(Integer weWikiIdx,Integer weWikiRevision);
   public List<WeWikiSummary> getWeWikiSummaryList(Integer weWikiIdx,Integer weWikiRevision);
   public WeWikiGraph getWeWikiGraph(Integer weWikiIdx,Integer weWikiRevision);
   public void updateUserPoint(Integer we_ins_user,int point);
   public int requestAlarmInfo(Integer we_login_user_idx,String we_user_nick,Integer we_meta_idx,Integer we_target_user_idx,Integer we_wiki_idx,Integer we_space_idx);
   public int insertWeFile(WeFile weFile);
   public void delWeFile(Integer weFileIdx);
   public WeWikiComment getWeWikiComment(String weWikiCommentIdx);
   public WeWikiFile getWikiFileInfo(Integer weFileIdx);
   public StringBuffer getHtmlSourceByWikiIdx(Integer we_wiki_idx);
}