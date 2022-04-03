package run.halo.app.controller.admin.api;
 import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.annotation.DisableOnCondition;
import run.halo.app.cache.lock.CacheLock;
import run.halo.app.handler.theme.config.support.Group;
import run.halo.app.handler.theme.config.support.ThemeProperty;
import run.halo.app.model.params.ThemeContentParam;
import run.halo.app.model.support.BaseResponse;
import run.halo.app.model.support.ThemeFile;
import run.halo.app.service.ThemeService;
import run.halo.app.service.ThemeSettingService;
@RestController
@RequestMapping("/api/admin/themes")
public class ThemeController {

 private  ThemeService themeService;

 private  ThemeSettingService themeSettingService;

public ThemeController(ThemeService themeService, ThemeSettingService themeSettingService) {
    this.themeService = themeService;
    this.themeSettingService = themeSettingService;
}
@GetMapping("{themeId:.+}/configurations")
@ApiOperation("Fetches theme configuration by theme id")
public List<Group> fetchConfig(String themeId){
    return themeService.fetchConfig(themeId);
}


@PutMapping("fetching/{themeId:.+}")
@ApiOperation("Upgrades theme from remote")
public ThemeProperty updateThemeByFetching(String themeId){
    return themeService.update(themeId);
}


@PostMapping("{themeId:.+}/activation")
@ApiOperation("Activates a theme")
public ThemeProperty active(String themeId){
    return themeService.activateTheme(themeId);
}


@GetMapping("{themeId:.+}/settings")
@ApiOperation("Lists theme settings by theme id")
public Map<String,Object> listSettingsBy(String themeId){
    return themeSettingService.listAsMapBy(themeId);
}


@PostMapping("{themeId:.+}/settings")
@ApiOperation("Saves theme settings")
@CacheLock(prefix = "save_theme_setting_by_themeId")
public void saveSettingsBy(String themeId,Map<String,Object> settings){
    themeSettingService.save(settings, themeId);
}


@PutMapping("{themeId:.+}/files/content")
@ApiOperation("Updates template content by theme id")
@DisableOnCondition
public void updateContentBy(String themeId,ThemeContentParam param){
    themeService.saveTemplateContent(themeId, param.getPath(), param.getContent());
}


@GetMapping("activation")
@ApiOperation("Gets activate theme")
public ThemeProperty getActivateTheme(){
    return themeService.getThemeOfNonNullBy(themeService.getActivatedThemeId());
}


@PostMapping("upload")
@ApiOperation("Uploads a theme")
public ThemeProperty uploadTheme(MultipartFile file){
    return themeService.upload(file);
}


@GetMapping("{themeId:.+}/files/content")
@ApiOperation("Gets template content by theme id")
public BaseResponse<String> getContentBy(String themeId,String path){
    return BaseResponse.ok(HttpStatus.OK.getReasonPhrase(), themeService.getTemplateContent(themeId, path));
}


@GetMapping("activation/template/custom/sheet")
@ApiOperation("Gets custom sheet templates")
public List<String> customSheetTemplate(){
    return themeService.listCustomTemplates(themeService.getActivatedThemeId(), ThemeService.CUSTOM_SHEET_PREFIX);
}


@PostMapping("fetching")
@ApiOperation("Fetches a new theme")
public ThemeProperty fetchTheme(String uri){
    return themeService.fetch(uri);
}


@PostMapping("reload")
@ApiOperation("Reloads themes")
public void reload(){
    themeService.reload();
}


@GetMapping("{themeId:.+}")
@ApiOperation("Gets theme property by theme id")
public ThemeProperty getBy(String themeId){
    return themeService.getThemeOfNonNullBy(themeId);
}


@PutMapping("upload/{themeId:.+}")
@ApiOperation("Upgrades theme by file")
public ThemeProperty updateThemeByUpload(String themeId,MultipartFile file){
    return themeService.update(themeId, file);
}


@DeleteMapping("{themeId:.+}")
@ApiOperation("Deletes a theme")
@DisableOnCondition
public void deleteBy(String themeId,Boolean deleteSettings){
    themeService.deleteTheme(themeId, deleteSettings);
}


@GetMapping(value = "activation/template/exists")
@ApiOperation("Determines if template exists")
public BaseResponse<Boolean> exists(String template){
    return BaseResponse.ok(themeService.templateExists(template));
}


@GetMapping
@ApiOperation("Lists all themes")
public List<ThemeProperty> listAll(){
    return themeService.getThemes();
}


@GetMapping("{themeId:.+}/files")
@ApiOperation("Lists theme files by theme id")
public List<ThemeFile> listFiles(String themeId){
    return themeService.listThemeFolderBy(themeId);
}


@GetMapping("activation/template/custom/post")
@ApiOperation("Gets custom post templates")
public List<String> customPostTemplate(){
    return themeService.listCustomTemplates(themeService.getActivatedThemeId(), ThemeService.CUSTOM_POST_PREFIX);
}


}