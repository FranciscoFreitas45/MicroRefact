package run.halo.app.service.impl;
 import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import run.halo.app.exception.ServiceException;
import run.halo.app.handler.theme.config.support.Group;
import run.halo.app.handler.theme.config.support.Item;
import run.halo.app.model.entity.ThemeSetting;
import run.halo.app.repository.ThemeSettingRepository;
import run.halo.app.service.ThemeService;
import run.halo.app.service.ThemeSettingService;
import run.halo.app.service.base.AbstractCrudService;
import run.halo.app.utils.ServiceUtils;
@Slf4j
@Service
public class ThemeSettingServiceImpl extends AbstractCrudService<ThemeSetting, Integer>implements ThemeSettingService{

 private  ThemeSettingRepository themeSettingRepository;

 private  ThemeService themeService;

 private  Configuration configuration;

public ThemeSettingServiceImpl(ThemeSettingRepository themeSettingRepository, ThemeService themeService, Configuration configuration) {
    super(themeSettingRepository);
    this.themeSettingRepository = themeSettingRepository;
    this.themeService = themeService;
    this.configuration = configuration;
}
@Override
@Transactional
public void deleteInactivated(){
    themeSettingRepository.deleteByThemeIdIsNot(themeService.getActivatedThemeId());
}


@Override
public void save(Map<String,Object> settings,String themeId){
    assertThemeIdHasText(themeId);
    if (CollectionUtils.isEmpty(settings)) {
        return;
    }
    // Save the settings
    settings.forEach((key, value) -> save(key, value.toString(), themeId));
    try {
        configuration.setSharedVariable("settings", listAsMapBy(themeService.getActivatedThemeId()));
    } catch (TemplateModelException e) {
        throw new ServiceException("主题设置保存失败", e);
    }
}


@Override
public List<ThemeSetting> listBy(String themeId){
    assertThemeIdHasText(themeId);
    return themeSettingRepository.findAllByThemeId(themeId);
}


@Override
public Map<String,Object> listAsMapBy(String themeId){
    // Convert to item map(key: item name, value: item)
    Map<String, Item> itemMap = getConfigItemMap(themeId);
    // Get theme setting
    List<ThemeSetting> themeSettings = listBy(themeId);
    Map<String, Object> result = new HashMap<>();
    // Build settings from user-defined
    themeSettings.forEach(themeSetting -> {
        String key = themeSetting.getKey();
        Item item = itemMap.get(key);
        if (item == null) {
            return;
        }
        Object convertedValue = item.getDataType().convertTo(themeSetting.getValue());
        log.debug("Converted user-defined data from [{}] to [{}], type: [{}]", themeSetting.getValue(), convertedValue, item.getDataType());
        result.put(key, convertedValue);
    });
    // Build settings from pre-defined
    itemMap.forEach((name, item) -> {
        log.debug("Name: [{}], item: [{}]", name, item);
        if (item.getDefaultValue() == null || result.containsKey(name)) {
            return;
        }
        // Set default value
        Object convertedDefaultValue = item.getDataType().convertTo(item.getDefaultValue());
        log.debug("Converted pre-defined data from [{}] to [{}], type: [{}]", item.getDefaultValue(), convertedDefaultValue, item.getDataType());
        result.put(name, convertedDefaultValue);
    });
    return result;
}


public Map<String,Item> getConfigItemMap(String themeId){
    // Get theme configuration
    List<Group> groups = themeService.fetchConfig(themeId);
    // Mix all items
    Set<Item> items = new LinkedHashSet<>();
    groups.forEach(group -> items.addAll(group.getItems()));
    // Convert to item map(key: item name, value: item)
    return ServiceUtils.convertToMap(items, Item::getName);
}


public void assertThemeIdHasText(String themeId){
    Assert.hasText(themeId, "Theme id must not be null");
}


}