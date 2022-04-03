package com.gs.service.impl;
 import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.CheckinDAO;
import com.gs.service.CheckinService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class CheckinServiceImpl implements CheckinService{

@Resource
 private  CheckinDAO checkinDAO;


public List<Checkin> queryByPagerDisable(Pager pager){
    return checkinDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<Checkin> list){
    return checkinDAO.batchInsert(list);
}


public int batchUpdate(List<Checkin> list){
    return checkinDAO.batchUpdate(list);
}


public Checkin query(Checkin checkin){
    return checkinDAO.query(checkin);
}


public int count(User user){
    return checkinDAO.count(user);
}


public int insert(Checkin checkin){
    return checkinDAO.insert(checkin);
}


public int update(Checkin checkin){
    return checkinDAO.update(checkin);
}


public int active(String id){
    return checkinDAO.active(id);
}


public List<Checkin> queryAll(String status){
    return checkinDAO.queryAll(status);
}


public List<Checkin> blurredQuery(Pager pager,Checkin checkin){
    return checkinDAO.blurredQuery(pager, checkin);
}


public int delete(Checkin checkin){
    return checkinDAO.delete(checkin);
}


public int batchDelete(List<Checkin> list){
    return checkinDAO.batchDelete(list);
}


@Override
public List<Checkin> queryByPhone(String userPhone){
    return checkinDAO.queryByPhone(userPhone);
}


public int inactive(String id){
    return checkinDAO.inactive(id);
}


@Override
public void updateUserIds(String userId,String chIds){
    checkinDAO.updateUserIds(userId, chIds);
}


public int countByBlurred(Checkin checkin,User user){
    return checkinDAO.countByBlurred(checkin, user);
}


public int deleteById(String id){
    return checkinDAO.deleteById(id);
}


public int countByDisable(User user){
    return checkinDAO.countByDisable(user);
}


public Checkin queryById(String id){
    return checkinDAO.queryById(id);
}


public List<Checkin> queryByPager(Pager pager){
    return checkinDAO.queryByPager(pager);
}


}