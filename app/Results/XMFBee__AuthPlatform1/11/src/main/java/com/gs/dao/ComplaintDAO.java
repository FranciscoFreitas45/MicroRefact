package com.gs.dao;
 import com.gs.bean.Complaint;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ComplaintDAO extends BaseDAO<String, Complaint>{


public int countComplaintUser(String userId)
;

public int countName(Complaint complaint,User user)
;

public List<Complaint> queryByPagerName(Pager pager,Complaint complaint)
;

public List<Complaint> queryByPagerComplaintUser(Pager pager,String userId)
;

}