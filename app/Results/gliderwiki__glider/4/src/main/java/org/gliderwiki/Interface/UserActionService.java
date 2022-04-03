package org.gliderwiki.Interface;
public interface UserActionService {

   public List<WikiLogVo> getMyWikiLogAction(MemberSessionVo memberSessionVo);
   public List<WikiLogVo> getSpaceInfoByIdx(List<Integer> wikiSpaceIdxList);
   public List<WeSpaceJoin> getUserSpaceJoinList(WeSpaceJoin joinDomain,int type);
}