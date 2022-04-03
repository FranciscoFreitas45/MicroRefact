package run.halo.app.service;
 import java.util.List;
import java.util.Map;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import run.halo.app.model.entity.ThemeSetting;
import run.halo.app.service.base.CrudService;
public interface ThemeSettingService extends CrudService<ThemeSetting, Integer>{


public void deleteInactivated()
;

@Transactional
public void save(Map<String,Object> settings,String themeId)
;

@NonNull
public List<ThemeSetting> listBy(String themeId)
;

@NonNull
public Map<String,Object> listAsMapBy(String themeId)
;

}