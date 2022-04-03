package com.gs.dao;
 import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CheckinDAO extends BaseDAO<String, Checkin>{


public List<Checkin> queryByPhone(String userPhone)
;

public void updateUserIds(String userId,String chIds)
;

public int countByBlurred(Checkin checkin,User user)
;

public List<Checkin> blurredQuery(Pager pager,Checkin checkin)
;

}