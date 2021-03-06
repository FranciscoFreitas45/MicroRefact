package com.gs.service.impl;
 import com.gs.bean.MaintainRemind;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.MaintainRemindDAO;
import com.gs.service.MaintainRemindService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class MaintainRemindServiceImpl implements MaintainRemindService{

@Resource
 private  MaintainRemindDAO maintainRemindDAO;


public List<MaintainRemind> queryByPagerDisable(Pager pager){
    return maintainRemindDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<MaintainRemind> list){
    return maintainRemindDAO.batchInsert(list);
}


public int batchUpdate(List<MaintainRemind> list){
    return maintainRemindDAO.batchUpdate(list);
}


public MaintainRemind query(MaintainRemind maintainRemind){
    return maintainRemindDAO.query(maintainRemind);
}


public int count(User user){
    return maintainRemindDAO.count(user);
}


@Override
public List<MaintainRemind> queryByPagerNull(Pager pager){
    return maintainRemindDAO.queryByPagerNull(pager);
}


@Override
public int countUser(String userId){
    return maintainRemindDAO.countUser(userId);
}


public int insert(MaintainRemind maintainRemind){
    return maintainRemindDAO.insert(maintainRemind);
}


public int update(MaintainRemind maintainRemind){
    return maintainRemindDAO.update(maintainRemind);
}


public int active(String id){
    return maintainRemindDAO.active(id);
}


@Override
public List<MaintainRemind> queryByPagerUser(Pager pager,String userId){
    return maintainRemindDAO.queryByPagerUser(pager, userId);
}


@Override
public List<MaintainRemind> queryAll(String status){
    return maintainRemindDAO.queryAll(status);
}


public List<MaintainRemind> blurredQuery(Pager pager,MaintainRemind maintainRemind){
    return maintainRemindDAO.blurredQuery(pager, maintainRemind);
}


public int delete(MaintainRemind maintainRemind){
    return maintainRemindDAO.delete(maintainRemind);
}


public int batchDelete(List<MaintainRemind> list){
    return maintainRemindDAO.batchDelete(list);
}


public List<MaintainRemind> queryByStatus(String status){
    return maintainRemindDAO.queryAll(status);
}


public int inactive(String id){
    return maintainRemindDAO.inactive(id);
}


public int countByBlurred(MaintainRemind maintainRemind){
    return 0;
}


public int deleteById(String id){
    return maintainRemindDAO.deleteById(id);
}


public int countByDisable(User user){
    return maintainRemindDAO.countByDisable(user);
}


@Override
public int countNull(User user){
    return maintainRemindDAO.countNull(user);
}


@Override
public void insertBatch(List<MaintainRemind> maintainReminds){
    maintainRemindDAO.insertBatch(maintainReminds);
}


public MaintainRemind queryById(String id){
    return maintainRemindDAO.queryById(id);
}


public List<MaintainRemind> queryByPager(Pager pager){
    return maintainRemindDAO.queryByPager(pager);
}


}