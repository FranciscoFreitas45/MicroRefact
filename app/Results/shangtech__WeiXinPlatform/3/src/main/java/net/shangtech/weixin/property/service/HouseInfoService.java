package net.shangtech.weixin.property.service;
 import net.shangtech.ssh.core.base.BaseDao;
import net.shangtech.ssh.core.base.BaseService;
import net.shangtech.weixin.property.dao.HouseInfoDao;
import net.shangtech.weixin.property.entity.HouseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class HouseInfoService extends BaseService<HouseInfo>{

@Autowired
 private HouseInfoDao dao;


@Override
public BaseDao<HouseInfo> dao(){
    return dao;
}


}