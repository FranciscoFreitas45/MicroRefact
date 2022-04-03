package run.halo.app.repository;
 import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.ThemeSetting;
import run.halo.app.repository.base.BaseRepository;
public interface ThemeSettingRepository extends BaseRepository<ThemeSetting, Integer>{


public long deleteByThemeIdAndKey(String themeId,String key)
;

public void deleteByThemeId(String themeId)
;

public void deleteByThemeIdIsNot(String activatedThemeId)
;

@NonNull
public List<ThemeSetting> findAllByThemeId(String themeId)
;

@NonNull
public Optional<ThemeSetting> findByThemeIdAndKey(String themeId,String key)
;

}