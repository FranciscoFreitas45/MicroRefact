package com.gs.service.impl;
 import com.gs.bean.Checkin;
import com.gs.bean.MaintainFix;
import com.gs.bean.MaintainFixAcc;
import com.gs.bean.User;
import com.gs.dao.MaintainFixAccDAO;
import com.gs.service.MaintainFixAccService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.common.bean.Pager;
@Service
public class MaintainFixAccServiceImpl implements MaintainFixAccService{

@Resource
 private  MaintainFixAccDAO maintainFixAccDAO;


public List<MaintainFixAcc> queryByPagerDisable(Pager pager){
    return maintainFixAccDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<MaintainFixAcc> list){
    return maintainFixAccDAO.batchInsert(list);
}


public int batchUpdate(List<MaintainFixAcc> list){
    return maintainFixAccDAO.batchUpdate(list);
}


public MaintainFixAcc query(MaintainFixAcc maintainFixAcc){
    return maintainFixAccDAO.query(maintainFixAcc);
}


@Override
public int count(User user){
    return maintainFixAccDAO.count(user);
}


public List<MaintainFixAcc> queryByRecord(String fixId){
    return maintainFixAccDAO.queryByRecord(fixId);
}


public int insert(MaintainFixAcc maintainFixAcc){
    return maintainFixAccDAO.insert(maintainFixAcc);
}


public int update(MaintainFixAcc maintainFixAcc){
    return maintainFixAccDAO.update(maintainFixAcc);
}


public int active(String id){
    return maintainFixAccDAO.active(id);
}


@Override
public int countByDetails(String maintainId,User user){
    return maintainFixAccDAO.countByDetails(maintainId, user);
}


@Override
public List<MaintainFixAcc> queryAll(String status){
    return maintainFixAccDAO.queryAll(status);
}


public List<MaintainFixAcc> blurredQuery(Pager pager,MaintainFixAcc maintainFixAcc){
    return null;
}


public int delete(MaintainFixAcc maintainFixAcc){
    return maintainFixAccDAO.delete(maintainFixAcc);
}


public int batchDelete(List<MaintainFixAcc> list){
    return maintainFixAccDAO.batchDelete(list);
}


public List<MaintainFixAcc> queryByStatus(String status){
    return maintainFixAccDAO.queryAll(status);
}


public int inactive(String id){
    return maintainFixAccDAO.inactive(id);
}


public int countByBlurred(MaintainFixAcc maintainFixAcc){
    return 0;
}


public int deleteById(String id){
    return maintainFixAccDAO.deleteById(id);
}


@Override
public int countByDisable(User user){
    return maintainFixAccDAO.countByDisable(user);
}


public MaintainFixAcc queryById(String id){
    return maintainFixAccDAO.queryById(id);
}


public List<MaintainFixAcc> queryByPager(Pager pager){
    return maintainFixAccDAO.queryByPager(pager);
}


@Override
public List<MaintainFixAcc> queryByDetailsByPager(Pager pager,String maintainId){
    return maintainFixAccDAO.queryByDetailsByPager(pager, maintainId);
}


}