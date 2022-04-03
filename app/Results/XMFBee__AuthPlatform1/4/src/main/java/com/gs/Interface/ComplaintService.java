package com.gs.Interface;
public interface ComplaintService {

   public Object count(Object Object);
   public Object queryByPager(Object Object);
   public int countComplaintUser(String userId);
   public List<Complaint> queryByPagerComplaintUser(Pager pager,String userId);
   public int countName(Complaint complaint,User user);
   public List<Complaint> queryByPagerName(Pager pager,Complaint complaint);
   public Object insert(Object Object);
   public Object update(Object Object);
}