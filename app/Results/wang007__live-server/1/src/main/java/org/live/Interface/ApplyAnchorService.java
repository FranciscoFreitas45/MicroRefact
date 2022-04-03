package org.live.Interface;
public interface ApplyAnchorService {

   public boolean judgeUserApplyCount(String userId,int systemMaxCount);
   public Date getLastApplyAnchorDate(String userId);
   public Object save(Object Object);
}