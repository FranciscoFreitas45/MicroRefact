package com.gs.service.impl;
 import com.gs.bean.MaintainDetail;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.MaintainDetailDAO;
import com.gs.service.MaintainDetailService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class MaintainDetailServiceImpl implements MaintainDetailService{

@Resource
 private  MaintainDetailDAO maintainDetailDAO;


@Override
public int countByDetail(String maintainRecordId){
    return maintainDetailDAO.countByDetail(maintainRecordId);
}


@Override
public List<MaintainDetail> queryByOwner(Pager pager,String recordId,String userId){
    return maintainDetailDAO.queryByOwner(pager, recordId, userId);
}


public List<MaintainDetail> queryByPagerDisable(Pager pager){
    return maintainDetailDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<MaintainDetail> list){
    return maintainDetailDAO.batchInsert(list);
}


public int batchUpdate(List<MaintainDetail> list){
    return maintainDetailDAO.batchUpdate(list);
}


public int insert(MaintainDetail maintainDetail){
    return maintainDetailDAO.insert(maintainDetail);
}


public int update(MaintainDetail maintainDetail){
    return maintainDetailDAO.update(maintainDetail);
}


@Override
public List<MaintainDetail> queryAll(String status){
    return maintainDetailDAO.queryAll(status);
}


public int delete(MaintainDetail maintainDetail){
    return maintainDetailDAO.delete(maintainDetail);
}


@Override
public int countByOwner(String recordId,String userId){
    return maintainDetailDAO.countByOwner(recordId, userId);
}


public int batchDelete(List<MaintainDetail> list){
    return maintainDetailDAO.batchDelete(list);
}


public int inactive(String id){
    return maintainDetailDAO.inactive(id);
}


public int countByBlurred(MaintainDetail maintainDetail,User user){
    return 0;
}


@Override
public List<MaintainDetail> queryByCondition(String start,String end,String companyId,String maintainId,String type){
    return maintainDetailDAO.queryByCondition(start, end, companyId, maintainId, type);
}


public MaintainDetail query(MaintainDetail maintainDetail){
    return maintainDetailDAO.query(maintainDetail);
}


public int count(User user){
    return maintainDetailDAO.count(user);
}


public int active(String id){
    return maintainDetailDAO.active(id);
}


public List<MaintainDetail> blurredQuery(Pager pager,MaintainDetail maintainDetail){
    return null;
}


@Override
public List<MaintainDetail> queryByDetailByPager(Pager pager,String maintainRecordId){
    return maintainDetailDAO.queryByDetailByPager(pager, maintainRecordId);
}


@Override
public List<MaintainDetail> queryByRecordId(String maintainRecordId){
    return maintainDetailDAO.queryByRecordId(maintainRecordId);
}


public List<MaintainDetail> queryByStatus(String status){
    return maintainDetailDAO.queryAll(status);
}


public int deleteById(String id){
    return maintainDetailDAO.deleteById(id);
}


public int countByDisable(User user){
    return maintainDetailDAO.countByDisable(user);
}


@Override
public List<MaintainDetail> queryByFrontpage(Pager pager){
    return maintainDetailDAO.queryByFrontpage(pager);
}


public MaintainDetail queryById(String id){
    return maintainDetailDAO.queryById(id);
}


public List<MaintainDetail> queryByPager(Pager pager){
    return maintainDetailDAO.queryByPager(pager);
}


}