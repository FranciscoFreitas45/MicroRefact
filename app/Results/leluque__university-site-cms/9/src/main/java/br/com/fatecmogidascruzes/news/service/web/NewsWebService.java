package br.com.fatecmogidascruzes.news.service.web;
 import java.util.List;
import java.util.UUID;
import br.com.fatecmogidascruzes.data.SearchCriteria;
import br.com.fatecmogidascruzes.dto.TableDTO;
import br.com.fatecmogidascruzes.exception.web.BadRequestException;
import br.com.fatecmogidascruzes.exception.web.ForbiddenException;
import br.com.fatecmogidascruzes.exception.web.InternalErrorException;
import br.com.fatecmogidascruzes.news.News;
public interface NewsWebService {


public List<NewsHomeDTO> getHomeNews()
;

public TableDTO<NewsTableRowDTO> getTable(SearchCriteria searchCriteria,int draw)
;

public void save(NewsEditDTO newsEditDTO) throws BadRequestException
;

public List<NewsBannerDTO> getHighlights()
;

public News abrirLink(UUID hash)
;

public NewsDetailDTO getNewsDetail(UUID hash)
;

public List<NewsHomeDTO> getEnabledNews()
;

}