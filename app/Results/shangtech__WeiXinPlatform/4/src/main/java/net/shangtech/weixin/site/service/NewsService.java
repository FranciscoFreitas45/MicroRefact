package net.shangtech.weixin.site.service;
 import java.util.Date;
import java.util.List;
import net.shangtech.ssh.core.base.BaseDao;
import net.shangtech.ssh.core.base.BaseService;
import net.shangtech.weixin.site.dao.NewsDao;
import net.shangtech.weixin.site.dao.NewsTypeDao;
import net.shangtech.weixin.site.entity.News;
import net.shangtech.weixin.site.entity.NewsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.shangtech.Interface.NewsDao;
import net.shangtech.Interface.NewsTypeDao;
@Service
@Transactional
public class NewsService extends BaseService<News>{

@Autowired
 private  NewsDao dao;

@Autowired
 private  NewsTypeDao typeDao;


public List<News> findPublishedNewsByType(int type){
    return dao.find("where type=? and isPublish=?", type, true);
}


public List<NewsType> findNewsTypeByUserId(int sysUserId){
    return typeDao.find("where sysUserId=?", sysUserId);
}


public void saveType(NewsType type){
    if (type.getId() != null) {
        NewsType o = typeDao.find(type.getId());
        o.setMemo(type.getMemo());
        o.setName(type.getName());
        o.setSort(type.getSort());
        // o.setUrl(type.getUrl());
        typeDao.update(o);
    } else {
        typeDao.insert(type);
    }
}


public void saveNews(News news){
    if (news.getId() != null) {
        News o = dao.find(news.getId());
        o.setContent(news.getContent());
        if (news.getImage() != null)
            o.setImage(news.getImage());
        o.setIsPublish(news.getIsPublish());
        o.setSort(news.getSort());
        o.setTitle(news.getTitle());
        o.setType(news.getType());
        dao.update(o);
    } else {
        news.setCreateTime(new Date());
        dao.insert(news);
    }
}


@Override
public BaseDao<News> dao(){
    return dao;
}


public List<News> findAllNewsByType(int type){
    return dao.find("where type=?", type);
}


public List<News> findAllNewsBySysUser(int sysUserId){
    return dao.find("where sysUserId=?", sysUserId);
}


public NewsType findType(int id){
    return typeDao.find(id);
}


public void deleteType(int id){
    typeDao.delete(id);
}


}