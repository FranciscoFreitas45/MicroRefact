package com.gs.service.impl;
 import com.gs.bean.MaintainDetail;
import com.gs.bean.MaintainFix;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.MaintainFixDAO;
import com.gs.service.MaintainFixService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class MaintainFixServiceImpl implements MaintainFixService{

@Resource
 private  MaintainFixDAO maintainFixDAO;


public List<MaintainFix> queryByPagerDisable(Pager pager){
    return maintainFixDAO.queryByPagerDisable(pager);
}


@Override
public int countByDisableService(User user){
    return maintainFixDAO.countByDisableService(user);
}


public int batchInsert(List<MaintainFix> list){
    return maintainFixDAO.batchInsert(list);
}


public int batchUpdate(List<MaintainFix> list){
    return maintainFixDAO.batchUpdate(list);
}


public int insert(MaintainFix maintainFix){
    return maintainFixDAO.insert(maintainFix);
}


public int update(MaintainFix maintainFix){
    return maintainFixDAO.update(maintainFix);
}


@Override
public int countMaintain(User user){
    return maintainFixDAO.countMaintain(user);
}


@Override
public List<MaintainFix> queryAll(String status){
    return maintainFixDAO.queryAll(status);
}


public int delete(MaintainFix maintainFix){
    return maintainFixDAO.delete(maintainFix);
}


public int batchDelete(List<MaintainFix> list){
    return maintainFixDAO.batchDelete(list);
}


public int inactive(String id){
    return maintainFixDAO.inactive(id);
}


@Override
public int countByBlurred(MaintainFix maintainFix,User user){
    return 0;
}


@Override
public List<MaintainFix> queryByPagerMaintain(Pager pager){
    return maintainFixDAO.queryByPagerMaintain(pager);
}


@Override
public List<MaintainFix> queryByCompanyId(String companyId){
    return maintainFixDAO.queryByCompanyId(companyId);
}


@Override
public int querymaintainNameMaintain(String maintainName,String maintainId){
    return maintainFixDAO.querymaintainNameMaintain(maintainName, maintainId);
}


@Override
public List<MaintainFix> queryByCondition(String start,String end,String maintainId,String companyId,String maintainOrFix,String type){
    return maintainFixDAO.queryByCondition(start, end, maintainId, companyId, maintainOrFix, type);
}


@Override
public int querymaintainName(String maintainName,String maintainId){
    return maintainFixDAO.querymaintainName(maintainName, maintainId);
}


public MaintainFix query(MaintainFix maintainFix){
    return maintainFixDAO.query(maintainFix);
}


@Override
public int count(User user){
    return maintainFixDAO.count(user);
}


public int active(String id){
    return maintainFixDAO.active(id);
}


public List<MaintainFix> blurredQuery(Pager pager,MaintainFix maintainFix){
    return null;
}


@Override
public int countqueryByPagerAll(User user){
    return maintainFixDAO.countqueryByPagerAll(user);
}


public List<MaintainFix> queryByStatus(String status){
    return maintainFixDAO.queryAll(status);
}


@Override
public List<MaintainFix> queryByPagerDisableService(Pager pager){
    return maintainFixDAO.queryByPagerDisableService(pager);
}


public int deleteById(String id){
    return maintainFixDAO.deleteById(id);
}


@Override
public int countByDisable(User user){
    return maintainFixDAO.countByDisable(user);
}


@Override
public List<MaintainFix> queryByPagerAll(Pager pager){
    return maintainFixDAO.queryByPagerAll(pager);
}


@Override
public List<MaintainFix> queryByMaintainName(String companyId,String maintainOrFix){
    return maintainFixDAO.queryByMaintainName(companyId, maintainOrFix);
}


public MaintainFix queryById(String id){
    return maintainFixDAO.queryById(id);
}


public List<MaintainFix> queryByPager(Pager pager){
    return maintainFixDAO.queryByPager(pager);
}


}