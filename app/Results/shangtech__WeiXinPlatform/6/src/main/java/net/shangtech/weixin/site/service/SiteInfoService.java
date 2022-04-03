package net.shangtech.weixin.site.service;
 import java.util.Date;
import net.shangtech.ssh.core.base.BaseDao;
import net.shangtech.ssh.core.base.BaseService;
import net.shangtech.weixin.site.dao.SiteInfoDao;
import net.shangtech.weixin.site.entity.SiteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class SiteInfoService extends BaseService<SiteInfo>{

@Autowired
 private  SiteInfoDao dao;


@Override
public BaseDao<SiteInfo> dao(){
    return dao;
}


public void save(SiteInfo info){
    if (info.getId() != null) {
        SiteInfo o = dao.find(info.getId());
        o.setContent(info.getContent());
        o.setImage(info.getImage());
        o.setTitle(info.getTitle());
        dao.update(o);
    } else {
        info.setCreateTime(new Date());
        dao.insert(info);
    }
}


}