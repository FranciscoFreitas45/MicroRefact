package run.halo.app.repository;
 import java.util.List;
import java.util.Optional;
import run.halo.app.handler.theme.config.support.ThemeProperty;
public interface ThemeRepository {


public void setActivatedTheme(String themeId)
;

public Optional<ThemeProperty> fetchThemePropertyByThemeId(String themeId)
;

public ThemeProperty getActivatedThemeProperty()
;

public boolean checkThemePropertyCompatibility(ThemeProperty themeProperty)
;

public String getActivatedThemeId()
;

public List<ThemeProperty> listAll()
;

public ThemeProperty attemptToAdd(ThemeProperty newThemeProperty)
;

public void deleteTheme(ThemeProperty themeProperty)
;

}