package run.halo.app.service.impl;
 import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.config.properties.HaloProperties;
import run.halo.app.event.theme.ThemeActivatedEvent;
import run.halo.app.event.theme.ThemeUpdatedEvent;
import run.halo.app.exception.BadRequestException;
import run.halo.app.exception.ForbiddenException;
import run.halo.app.exception.NotFoundException;
import run.halo.app.exception.ServiceException;
import run.halo.app.exception.ThemeNotSupportException;
import run.halo.app.exception.ThemePropertyMissingException;
import run.halo.app.exception.ThemeUpdateException;
import run.halo.app.handler.theme.config.ThemeConfigResolver;
import run.halo.app.handler.theme.config.support.Group;
import run.halo.app.handler.theme.config.support.ThemeProperty;
import run.halo.app.model.support.HaloConst;
import run.halo.app.model.support.ThemeFile;
import run.halo.app.repository.ThemeRepository;
import run.halo.app.repository.ThemeSettingRepository;
import run.halo.app.service.ThemeService;
import run.halo.app.theme.GitThemeFetcher;
import run.halo.app.theme.GitThemeUpdater;
import run.halo.app.theme.MultipartFileThemeUpdater;
import run.halo.app.theme.MultipartZipFileThemeFetcher;
import run.halo.app.theme.ThemeFetcherComposite;
import run.halo.app.theme.ThemeFileScanner;
import run.halo.app.theme.ThemePropertyScanner;
import run.halo.app.theme.ZipThemeFetcher;
import run.halo.app.utils.FileUtils;
@Slf4j
@Service
public class ThemeServiceImpl implements ThemeService{

 private  Path themeWorkDir;

 private  ThemeConfigResolver themeConfigResolver;

 private  RestTemplate restTemplate;

 private  ApplicationEventPublisher eventPublisher;

 private  ThemeSettingRepository themeSettingRepository;

 private  ThemeFetcherComposite fetcherComposite;

 private  ThemeRepository themeRepository;

public ThemeServiceImpl(HaloProperties haloProperties, ThemeConfigResolver themeConfigResolver, RestTemplate restTemplate, ApplicationEventPublisher eventPublisher, ThemeSettingRepository themeSettingRepository, ThemeRepository themeRepository) {
    this.themeConfigResolver = themeConfigResolver;
    this.restTemplate = restTemplate;
    this.themeWorkDir = Paths.get(haloProperties.getWorkDir(), THEME_FOLDER);
    this.eventPublisher = eventPublisher;
    this.themeSettingRepository = themeSettingRepository;
    this.themeRepository = themeRepository;
    this.fetcherComposite = new ThemeFetcherComposite();
    this.fetcherComposite.addFetcher(new ZipThemeFetcher());
    this.fetcherComposite.addFetcher(new GitThemeFetcher());
    this.fetcherComposite.addFetcher(new MultipartZipFileThemeFetcher());
}
@Override
@NonNull
public List<Group> fetchConfig(String themeId){
    Assert.hasText(themeId, "Theme id must not be blank");
    // Get theme property
    ThemeProperty themeProperty = getThemeOfNonNullBy(themeId);
    if (!themeProperty.isHasOptions()) {
        // If this theme dose not has an option, then return empty list
        return Collections.emptyList();
    }
    try {
        for (String optionsName : SETTINGS_NAMES) {
            // Resolve the options path
            Path optionsPath = Paths.get(themeProperty.getThemePath(), optionsName);
            log.debug("Finding options in: [{}]", optionsPath.toString());
            // Check existence
            if (!Files.exists(optionsPath)) {
                continue;
            }
            // Read the yaml file
            String optionContent = Files.readString(optionsPath);
            // Resolve it
            return themeConfigResolver.resolve(optionContent);
        }
        return Collections.emptyList();
    } catch (IOException e) {
        throw new ServiceException("读取主题配置文件失败", e);
    }
}


@Override
@NonNull
public String getTemplateContent(String themeId,String absolutePath){
    checkDirectory(themeId, absolutePath);
    // Read file
    Path path = Paths.get(absolutePath);
    try {
        return Files.readString(path);
    } catch (IOException e) {
        throw new ServiceException("读取模板内容失败 " + absolutePath, e);
    }
}


@Override
public boolean templateExists(String template){
    if (StringUtils.isBlank(template)) {
        return false;
    }
    return fetchActivatedTheme().map(themeProperty -> {
        // Resolve template path
        Path templatePath = Paths.get(themeProperty.getThemePath(), template);
        // Check the directory
        checkDirectory(templatePath.toString());
        // Check existence
        return Files.exists(templatePath);
    }).orElse(false);
}


@Override
@NonNull
public ThemeProperty upload(MultipartFile file){
    Assert.notNull(file, "Multipart file must not be null");
    final var newThemeProperty = this.fetcherComposite.fetch(file);
    return this.themeRepository.attemptToAdd(newThemeProperty);
}


@Override
@NonNull
public List<ThemeFile> listThemeFolderBy(String themeId){
    return fetchThemePropertyBy(themeId).map(themeProperty -> ThemeFileScanner.INSTANCE.scan(themeProperty.getThemePath())).orElse(Collections.emptyList());
}


@Override
@NonNull
public List<String> listCustomTemplates(String themeId,String prefix){
    return fetchThemePropertyBy(themeId).map(themeProperty -> {
        // Get the theme path
        Path themePath = Paths.get(themeProperty.getThemePath());
        try (Stream<Path> pathStream = Files.list(themePath)) {
            return pathStream.filter(path -> StringUtils.startsWithIgnoreCase(path.getFileName().toString(), prefix)).map(path -> {
                // Remove prefix
                final var customTemplate = StringUtils.removeStartIgnoreCase(path.getFileName().toString(), prefix);
                // Remove suffix
                return StringUtils.removeEndIgnoreCase(customTemplate, HaloConst.SUFFIX_FTL);
            }).distinct().collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Failed to list files of path " + themePath, e);
        }
    }).orElse(Collections.emptyList());
}


@Override
public ThemeProperty update(String themeId,MultipartFile file){
    Assert.hasText(themeId, "Theme id must not be blank");
    Assert.notNull(file, "Theme file must not be null");
    final var themeUpdater = new MultipartFileThemeUpdater(file, fetcherComposite, themeRepository);
    try {
        return themeUpdater.update(themeId);
    } catch (IOException e) {
        throw new ServiceException("更新主题失败：" + e.getMessage(), e);
    }
}


@Override
@NonNull
public Optional<ThemeProperty> fetchActivatedTheme(){
    return Optional.of(themeRepository.getActivatedThemeProperty());
}


public void checkDirectory(String themeId,String absoluteName){
    ThemeProperty themeProperty = getThemeOfNonNullBy(themeId);
    FileUtils.checkDirectoryTraversal(themeProperty.getThemePath(), absoluteName);
}


@Override
@NonNull
public Optional<ThemeProperty> fetchThemePropertyBy(String themeId){
    return themeRepository.fetchThemePropertyByThemeId(themeId);
}


@Override
public void reload(){
    eventPublisher.publishEvent(new ThemeUpdatedEvent(this));
}


@Override
@NonNull
public ThemeProperty getActivatedTheme(){
    return fetchActivatedTheme().orElseThrow();
}


@Override
public String render(String pageName){
    var folderName = getActivatedTheme().getFolderName();
    return "themes/" + folderName + "/" + pageName;
}


@Transactional
@Override
public void deleteTheme(String themeId,Boolean deleteSettings){
    // Get the theme property
    ThemeProperty themeProperty = getThemeOfNonNullBy(themeId);
    if (themeId.equals(getActivatedThemeId())) {
        // Prevent to delete the activated theme
        throw new BadRequestException("无法删除正在使用的主题！").setErrorData(themeId);
    }
    try {
        // Delete the folder
        FileUtils.deleteFolder(Paths.get(themeProperty.getThemePath()));
        if (deleteSettings) {
            // Delete theme settings
            themeSettingRepository.deleteByThemeId(themeId);
        }
        // Delete theme cache
        eventPublisher.publishEvent(new ThemeUpdatedEvent(this));
    } catch (Exception e) {
        throw new ServiceException("主题删除失败", e).setErrorData(themeId);
    }
}


@Override
public void saveTemplateContent(String themeId,String absolutePath,String content){
    // Check the path
    checkDirectory(themeId, absolutePath);
    // Write file
    Path path = Paths.get(absolutePath);
    try {
        Files.write(path, content.getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
        throw new ServiceException("保存模板内容失败 " + absolutePath, e);
    }
}


@NonNull
public ThemeProperty getProperty(Path themePath){
    return ThemePropertyScanner.INSTANCE.fetchThemeProperty(themePath).orElseThrow(() -> new ThemePropertyMissingException(themePath + " 没有说明文件").setErrorData(themePath));
}


@Override
public String renderWithSuffix(String pageName){
    var folderName = getActivatedTheme().getFolderName();
    return "themes/" + folderName + "/" + pageName + ".ftl";
}


@Override
@NonNull
public ThemeProperty activateTheme(String themeId){
    // set activated theme
    themeRepository.setActivatedTheme(themeId);
    // Clear the cache
    eventPublisher.publishEvent(new ThemeUpdatedEvent(this));
    // Publish a theme activated event
    eventPublisher.publishEvent(new ThemeActivatedEvent(this));
    return themeRepository.getActivatedThemeProperty();
}


public void downloadZipAndUnzip(String zipUrl,Path targetPath){
    Assert.hasText(zipUrl, "Zip url must not be blank");
    log.debug("Downloading [{}]", zipUrl);
    // Download it
    ResponseEntity<byte[]> downloadResponse = restTemplate.getForEntity(zipUrl, byte[].class);
    log.debug("Download response: [{}]", downloadResponse.getStatusCode());
    if (downloadResponse.getStatusCode().isError() || downloadResponse.getBody() == null) {
        throw new ServiceException("下载失败 " + zipUrl + ", 状态码: " + downloadResponse.getStatusCode());
    }
    log.debug("Downloaded [{}]", zipUrl);
    // Unzip it
    FileUtils.unzip(downloadResponse.getBody(), targetPath);
}


@Override
@NonNull
public String getActivatedThemeId(){
    return themeRepository.getActivatedThemeId();
}


@Override
public Path getBasePath(){
    return themeWorkDir;
}


@Override
@NonNull
public List<ThemeProperty> getThemes(){
    return themeRepository.listAll();
}


@Override
public boolean themeExists(String themeId){
    return fetchThemePropertyBy(themeId).isPresent();
}


@Override
@NonNull
public ThemeProperty getThemeOfNonNullBy(String themeId){
    return fetchThemePropertyBy(themeId).orElseThrow(() -> new NotFoundException(themeId + " 主题不存在或已删除！").setErrorData(themeId));
}


@Override
public ThemeProperty fetch(String uri){
    Assert.hasText(uri, "Theme remote uri must not be blank");
    final var themeProperty = fetcherComposite.fetch(uri);
    return this.themeRepository.attemptToAdd(themeProperty);
}


}