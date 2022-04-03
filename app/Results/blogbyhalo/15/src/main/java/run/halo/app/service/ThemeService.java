package run.halo.app.service;
 import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.handler.theme.config.support.Group;
import run.halo.app.handler.theme.config.support.ThemeProperty;
import run.halo.app.model.support.ThemeFile;
public interface ThemeService {

 private String[] SETTINGS_NAMES;

 private String[] CAN_EDIT_SUFFIX;

 private String THEME_FOLDER;

 private String THEMES_CACHE_KEY;

 private String CUSTOM_SHEET_PREFIX;

 private String CUSTOM_POST_PREFIX;


@NonNull
public List<Group> fetchConfig(String themeId)
;

public String getTemplateContent(String themeId,String absolutePath)
;

public void saveTemplateContent(String themeId,String absolutePath,String content)
;

public boolean templateExists(String template)
;

@NonNull
public String renderWithSuffix(String pageName)
;

@NonNull
public ThemeProperty activateTheme(String themeId)
;

@NonNull
public ThemeProperty upload(MultipartFile file)
;

@NonNull
public List<ThemeFile> listThemeFolderBy(String themeId)
;

@NonNull
public List<String> listCustomTemplates(String themeId,String prefix)
;

@NonNull
public String getActivatedThemeId()
;

public ThemeProperty update(String themeId,MultipartFile file)
;

@NonNull
public Optional<ThemeProperty> fetchActivatedTheme()
;

public Path getBasePath()
;

@NonNull
public Optional<ThemeProperty> fetchThemePropertyBy(String themeId)
;

@NonNull
public List<ThemeProperty> getThemes()
;

public boolean themeExists(String themeId)
;

@NonNull
public ThemeProperty getThemeOfNonNullBy(String themeId)
;

public void reload()
;

@NonNull
public ThemeProperty fetch(String uri)
;

@NonNull
public ThemeProperty getActivatedTheme()
;

@NonNull
public String render(String pageName)
;

public void deleteTheme(String themeId,Boolean deleteSettings)
;

}