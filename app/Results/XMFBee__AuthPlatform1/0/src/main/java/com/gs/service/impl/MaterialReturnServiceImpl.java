package com.gs.service.impl;
 import com.gs.bean.Accessories;
import com.gs.bean.Checkin;
import com.gs.bean.MaterialReturn;
import com.gs.bean.User;
import com.gs.dao.MaterialReturnDAO;
import com.gs.service.MaterialReturnService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.common.bean.Pager;
@Service
public class MaterialReturnServiceImpl implements MaterialReturnService{

@Resource
 private  MaterialReturnDAO materialReturnDAO;


public List<MaterialReturn> queryByPagerDisable(Pager pager){
    return materialReturnDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<MaterialReturn> list){
    return materialReturnDAO.batchInsert(list);
}


public int batchUpdate(List<MaterialReturn> list){
    return materialReturnDAO.batchUpdate(list);
}


public MaterialReturn query(MaterialReturn materialReturn){
    return materialReturnDAO.query(materialReturn);
}


public int count(User user){
    return materialReturnDAO.count(user);
}


public int insert(MaterialReturn materialReturn){
    return materialReturnDAO.insert(materialReturn);
}


public int update(MaterialReturn materialReturn){
    return materialReturnDAO.update(materialReturn);
}


public int active(String id){
    return materialReturnDAO.active(id);
}


@Override
public List<MaterialReturn> queryAll(String status){
    return materialReturnDAO.queryAll(status);
}


public List<MaterialReturn> blurredQuery(Pager pager,MaterialReturn materialReturn){
    return null;
}


public int delete(MaterialReturn materialReturn){
    return materialReturnDAO.delete(materialReturn);
}


public int batchDelete(List<MaterialReturn> list){
    return materialReturnDAO.batchDelete(list);
}


public List<MaterialReturn> queryByStatus(String status){
    return materialReturnDAO.queryAll(status);
}


public int inactive(String id){
    return materialReturnDAO.inactive(id);
}


public int countByBlurred(MaterialReturn materialReturn){
    return 0;
}


public int deleteById(String id){
    return materialReturnDAO.deleteById(id);
}


public int countByDisable(User user){
    return materialReturnDAO.countByDisable(user);
}


public MaterialReturn queryById(String id){
    return materialReturnDAO.queryById(id);
}


public List<MaterialReturn> queryByPager(Pager pager){
    return materialReturnDAO.queryByPager(pager);
}


@Override
public List<MaterialReturn> queryByCondition(String start,String end,String companyId,String accTypeId,String type){
    return materialReturnDAO.queryByCondition(start, end, companyId, accTypeId, type);
}


}