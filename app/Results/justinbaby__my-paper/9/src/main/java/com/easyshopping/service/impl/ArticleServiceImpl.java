package com.easyshopping.service.impl;
 import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.dao.ArticleDao;
import com.easyshopping.entity.Article;
import com.easyshopping.entity.ArticleCategory;
import com.easyshopping.entity.Tag;
import com.easyshopping.service.ArticleService;
import com.easyshopping.service.StaticService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
@Service("articleServiceImpl")
public class ArticleServiceImpl extends BaseServiceImpl<Article, Long>implements ArticleService,DisposableBean{

 private  long viewHitsTime;

@Resource(name = "ehCacheManager")
 private  CacheManager cacheManager;

@Resource(name = "articleDaoImpl")
 private  ArticleDao articleDao;

@Resource(name = "staticServiceImpl")
 private  StaticService staticService;


public long viewHits(Long id){
    Ehcache cache = cacheManager.getEhcache(Article.HITS_CACHE_NAME);
    Element element = cache.get(id);
    Long hits;
    if (element != null) {
        hits = (Long) element.getObjectValue();
    } else {
        Article article = articleDao.find(id);
        if (article == null) {
            return 0L;
        }
        hits = article.getHits();
    }
    hits++;
    cache.put(new Element(id, hits));
    long time = System.currentTimeMillis();
    if (time > viewHitsTime + Article.HITS_CACHE_INTERVAL) {
        viewHitsTime = time;
        updateHits();
        cache.removeAll();
    }
    return hits;
}


@Resource(name = "articleDaoImpl")
public void setBaseDao(ArticleDao articleDao){
    super.setBaseDao(articleDao);
}


@Transactional(readOnly = true)
public List<Article> findList(ArticleCategory articleCategory,Date beginDate,Date endDate,Integer first,Integer count){
    return articleDao.findList(articleCategory, beginDate, endDate, first, count);
}


@Override
@Transactional
@CacheEvict(value = { "article", "articleCategory" }, allEntries = true)
public void save(Article article){
    Assert.notNull(article);
    super.save(article);
    articleDao.flush();
    staticService.build(article);
}


public void destroy(){
    updateHits();
}


@Override
@Transactional
@CacheEvict(value = { "article", "articleCategory" }, allEntries = true)
public Article update(Article article,String ignoreProperties){
    return super.update(article, ignoreProperties);
}


@Transactional(readOnly = true)
public Page<Article> findPage(ArticleCategory articleCategory,List<Tag> tags,Pageable pageable){
    return articleDao.findPage(articleCategory, tags, pageable);
}


@Override
@Transactional
@CacheEvict(value = { "article", "articleCategory" }, allEntries = true)
public void delete(Article article){
    if (article != null) {
        staticService.delete(article);
    }
    super.delete(article);
}


@SuppressWarnings("unchecked")
public void updateHits(){
    Ehcache cache = cacheManager.getEhcache(Article.HITS_CACHE_NAME);
    List<Long> ids = cache.getKeys();
    for (Long id : ids) {
        Article article = articleDao.find(id);
        if (article != null) {
            Element element = cache.get(id);
            long hits = (Long) element.getObjectValue();
            article.setHits(hits);
            articleDao.merge(article);
        }
    }
}


}