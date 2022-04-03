package io.delivery.dao;
 import io.delivery.entity.News;
import java.util.List;
public interface NewsDao extends BasicDao<News>{


public List<News> findByName(String name)
;

}