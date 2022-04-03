package com.gs.service.impl;
 import com.gs.bean.User;
import com.gs.bean.WorkInfo;
import com.gs.common.bean.Pager;
import com.gs.dao.WorkInfoDAO;
import com.gs.service.WorkInfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class WorkInfoServiceImpl implements WorkInfoService{

@Resource
 private  WorkInfoDAO workInfoDAO;


@Override
public List<WorkInfo> queryByPagerschelude(Pager pager){
    return workInfoDAO.queryByPagerschelude(pager);
}


@Override
public int countByFront(User frontUser){
    return workInfoDAO.countByFront(frontUser);
}


@Override
public List<WorkInfo> queryByPagerDisable(Pager pager){
    return workInfoDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<WorkInfo> list){
    return workInfoDAO.batchInsert(list);
}


public int batchUpdate(List<WorkInfo> list){
    return workInfoDAO.batchUpdate(list);
}


public WorkInfo query(WorkInfo workInfo){
    return workInfoDAO.query(workInfo);
}


@Override
public int count(User user,String status){
    Map param = new HashMap();
    param.put("user", user);
    param.put("status", status);
    return workInfoDAO.count(param);
}


public int insert(WorkInfo workInfo){
    return workInfoDAO.insert(workInfo);
}


public int update(WorkInfo workInfo){
    return workInfoDAO.update(workInfo);
}


public int active(String id){
    return workInfoDAO.active(id);
}


@Override
public List<WorkInfo> blurredQuery(Pager pager,WorkInfo workInfo){
    return workInfoDAO.blurredQuery(pager, workInfo);
}


@Override
public List<WorkInfo> queryAll(String status){
    return null;
}


public int delete(WorkInfo workInfo){
    return workInfoDAO.delete(workInfo);
}


public int batchDelete(List<WorkInfo> list){
    return workInfoDAO.batchDelete(list);
}


public List<WorkInfo> queryByStatus(String status){
    return workInfoDAO.queryAll(status);
}


public int inactive(String id){
    return workInfoDAO.inactive(id);
}


@Override
public int countByBlurred(WorkInfo workInfo,User user){
    return workInfoDAO.countByBlurred(workInfo, user);
}


public int deleteById(String id){
    return workInfoDAO.deleteById(id);
}


@Override
public int countByDisable(User user){
    return 0;
}


@Override
public List<WorkInfo> queryByFront(Pager pager){
    return workInfoDAO.queryByFront(pager);
}


public WorkInfo queryById(String id){
    return workInfoDAO.queryById(id);
}


public List<WorkInfo> queryByPager(Pager pager,String status){
    Map paramMap = new HashMap();
    paramMap.put("pager", pager);
    paramMap.put("status", status);
    return workInfoDAO.queryByPager(paramMap);
}


@Override
public List<WorkInfo> queryByCondition(String start,String end,String companyId,String maintainOrFix,String type){
    return workInfoDAO.queryByCondition(start, end, companyId, maintainOrFix, type);
}


}