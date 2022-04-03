package com.gs.service.impl;
 import com.gs.bean.MaintainSchedule;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.MaintainScheduleDAO;
import com.gs.service.MaintainScheduleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class MaintainScheduleServiceImpl implements MaintainScheduleService{

@Resource
 private  MaintainScheduleDAO maintainScheduleDAO;


@Override
public List<MaintainSchedule> queryByPagerDisable(Pager pager){
    return maintainScheduleDAO.queryByPagerDisable(pager);
}


@Override
public int batchInsert(List<MaintainSchedule> list){
    return 0;
}


@Override
public int batchUpdate(List<MaintainSchedule> list){
    return 0;
}


@Override
public MaintainSchedule query(MaintainSchedule maintainSchedule){
    return null;
}


@Override
public int count(User user){
    return 0;
}


@Override
public int insert(MaintainSchedule maintainSchedule){
    return maintainScheduleDAO.insert(maintainSchedule);
}


@Override
public int update(MaintainSchedule maintainSchedule){
    return maintainScheduleDAO.update(maintainSchedule);
}


@Override
public int active(String id){
    return maintainScheduleDAO.active(id);
}


@Override
public List<MaintainSchedule> blurredQuery(Pager pager,MaintainSchedule maintainSchedule){
    return null;
}


@Override
public List<MaintainSchedule> queryAll(String status){
    return null;
}


@Override
public int delete(MaintainSchedule maintainSchedule){
    return 0;
}


@Override
public int batchDelete(List<MaintainSchedule> list){
    return 0;
}


@Override
public void updateById(String maintainScheduleId){
    maintainScheduleDAO.updateById(maintainScheduleId);
}


@Override
public List<MaintainSchedule> queryScheduleByRecord(String recordId){
    return maintainScheduleDAO.queryScheduleByRecord(recordId);
}


@Override
public int inactive(String id){
    return maintainScheduleDAO.inactive(id);
}


@Override
public int countByBlurred(MaintainSchedule maintainSchedule,User user){
    return 0;
}


@Override
public int deleteById(String id){
    return 0;
}


@Override
public int countByDisable(User user){
    return 0;
}


@Override
public MaintainSchedule queryById(String id){
    return null;
}


@Override
public List<MaintainSchedule> queryByPager(Pager pager){
    return maintainScheduleDAO.queryByPager(pager);
}


}