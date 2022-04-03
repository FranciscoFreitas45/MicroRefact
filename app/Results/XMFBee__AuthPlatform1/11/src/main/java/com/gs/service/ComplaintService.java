package com.gs.service;
 import com.gs.bean.Complaint;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface ComplaintService extends BaseService<String, Complaint>{


public int countComplaintUser(String userId)
;

public int countName(Complaint complaint,User user)
;

public List<Complaint> queryByPagerName(Pager pager,Complaint complaint)
;

public List<Complaint> queryByPagerComplaintUser(Pager pager,String userId)
;

}