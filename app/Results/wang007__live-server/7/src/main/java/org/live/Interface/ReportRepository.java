package org.live.Interface;
public interface ReportRepository {

   public Report getRecentlyReport(String userId,String liveRoomId);
   public Object save(Object Object);
}