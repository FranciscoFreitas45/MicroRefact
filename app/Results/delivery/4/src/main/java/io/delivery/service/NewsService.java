package io.delivery.service;
 import io.delivery.entity.News;
import java.util.List;
public interface NewsService {


public News deleteNews(long id)
;

public News updateNews(News news)
;

public News findById(long id)
;

public List<News> findByName(String name)
;

public News create(News news)
;

public List<News> getNewsList()
;

}