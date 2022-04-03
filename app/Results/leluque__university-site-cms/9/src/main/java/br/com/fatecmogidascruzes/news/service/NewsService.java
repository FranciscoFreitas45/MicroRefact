package br.com.fatecmogidascruzes.news.service;
 import java.util.List;
import org.springframework.data.domain.Page;
import br.com.fatecmogidascruzes.data.SearchCriteria;
import br.com.fatecmogidascruzes.news.News;
import br.com.fatecmogidascruzes.service.BaseService;
public interface NewsService extends BaseService<News, Long>{


public List<News> getEnabledAndVisibleHomeNews()
;

public Page<News> getEnabledByFilter(SearchCriteria searchCriteria)
;

public List<News> getEnabledVisibleAndHighlighted()
;

}