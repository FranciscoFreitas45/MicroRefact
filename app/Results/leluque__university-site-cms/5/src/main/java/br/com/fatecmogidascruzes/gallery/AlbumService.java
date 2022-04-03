package br.com.fatecmogidascruzes.gallery;
 import java.util.List;
import br.com.fatecmogidascruzes.service.BaseService;
public interface AlbumService extends BaseService<Album, Long>{


public List<Album> getEnabledByFilter(String filter)
;

}