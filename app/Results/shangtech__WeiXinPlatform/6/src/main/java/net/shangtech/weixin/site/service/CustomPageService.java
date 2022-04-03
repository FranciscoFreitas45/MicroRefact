package net.shangtech.weixin.site.service;
 import net.shangtech.ssh.core.base.BaseDao;
import net.shangtech.ssh.core.base.BaseService;
import net.shangtech.weixin.site.dao.CustomPageDao;
import net.shangtech.weixin.site.entity.CustomPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class CustomPageService extends BaseService<CustomPage>{

@Autowired
 private  CustomPageDao dao;


@Override
public BaseDao<CustomPage> dao(){
    return dao;
}


}