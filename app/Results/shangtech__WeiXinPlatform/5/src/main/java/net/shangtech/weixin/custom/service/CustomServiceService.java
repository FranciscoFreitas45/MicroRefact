package net.shangtech.weixin.custom.service;
 import java.util.List;
import net.shangtech.ssh.core.base.BaseDao;
import net.shangtech.ssh.core.base.BaseService;
import net.shangtech.weixin.custom.dao.CustomServiceDao;
import net.shangtech.weixin.custom.dao.CustomServiceGroupDao;
import net.shangtech.weixin.custom.entity.CustomService;
import net.shangtech.weixin.custom.entity.CustomServiceGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.shangtech.Interface.CustomServiceDao;
import net.shangtech.Interface.CustomServiceGroupDao;
@Service
@Transactional
public class CustomServiceService extends BaseService<CustomService>{

@Autowired
 private  CustomServiceDao dao;

@Autowired
 private  CustomServiceGroupDao groupDao;


public List<CustomService> findServiceByGroup(int groupId){
    return dao.find("where groupId=?", groupId);
}


public CustomServiceGroup findGroupById(int id){
    return groupDao.find(id);
}


public void saveServiceGroup(CustomServiceGroup group){
    if (group.getId() != null) {
        CustomServiceGroup o = groupDao.find(group.getId());
        o.setName(group.getName());
        o.setSort(group.getSort());
        groupDao.update(o);
    } else {
        groupDao.insert(group);
    }
}


@Override
public BaseDao<CustomService> dao(){
    return dao;
}


public void saveCustomService(CustomService customService){
    if (customService.getId() != null) {
        CustomService o = dao.find(customService.getId());
        o.setDescription(customService.getDescription());
        if (customService.getImage() != null)
            o.setImage(customService.getImage());
        o.setRealname(customService.getRealname());
        o.setSort(customService.getSort());
        o.setTel(customService.getTel());
        dao.update(o);
    } else {
        dao.insert(customService);
    }
}


public List<CustomServiceGroup> findGroupsByUser(int sysUserId){
    return groupDao.find("where sysUserId=?", sysUserId);
}


public void deleteGroup(int id){
    groupDao.delete(id);
}


}