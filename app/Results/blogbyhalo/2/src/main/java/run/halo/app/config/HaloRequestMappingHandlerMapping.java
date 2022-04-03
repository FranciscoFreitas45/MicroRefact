package run.halo.app.config;
 import run.halo.app.utils.HaloUtils.URL_SEPARATOR;
import run.halo.app.utils.HaloUtils.ensureBoth;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import run.halo.app.config.properties.HaloProperties;
import run.halo.app.event.StaticStorageChangedEvent;
@Slf4j
public class HaloRequestMappingHandlerMapping extends RequestMappingHandlerMappingimplements ApplicationListener<StaticStorageChangedEvent>{

 private  Set<String> blackPatterns;

 private  PathMatcher pathMatcher;

 private  HaloProperties haloProperties;

public HaloRequestMappingHandlerMapping(HaloProperties haloProperties) {
    this.haloProperties = haloProperties;
    this.initBlackPatterns();
    pathMatcher = new AntPathMatcher();
}
@Override
public HandlerMethod lookupHandlerMethod(String lookupPath,HttpServletRequest request){
    log.debug("Looking path: [{}]", lookupPath);
    for (String blackPattern : blackPatterns) {
        if (this.pathMatcher.match(blackPattern, lookupPath)) {
            log.debug("Skipped path [{}] with pattern: [{}]", lookupPath, blackPattern);
            return null;
        }
    }
    return super.lookupHandlerMethod(lookupPath, request);
}


@Override
public void onApplicationEvent(StaticStorageChangedEvent event){
    Path staticPath = event.getStaticPath();
    try (Stream<Path> rootPathStream = Files.list(staticPath)) {
        synchronized (this) {
            blackPatterns.clear();
            initBlackPatterns();
            rootPathStream.forEach(rootPath -> {
                if (Files.isDirectory(rootPath)) {
                    String directoryPattern = "/" + rootPath.getFileName().toString() + "/**";
                    blackPatterns.add(directoryPattern);
                    log.debug("Exclude for folder path pattern: [{}]", directoryPattern);
                } else {
                    String pathPattern = "/" + rootPath.getFileName().toString();
                    blackPatterns.add(pathPattern);
                    log.debug("Exclude for file path pattern: [{}]", pathPattern);
                }
            });
        }
    } catch (IOException e) {
        log.error("Failed to refresh static directory mapping", e);
    }
}


public void initBlackPatterns(){
    String uploadUrlPattern = ensureBoth(haloProperties.getUploadUrlPrefix(), URL_SEPARATOR) + "**";
    String adminPathPattern = ensureBoth(haloProperties.getAdminPath(), URL_SEPARATOR) + "?*/**";
    blackPatterns.add("/themes/**");
    blackPatterns.add("/js/**");
    blackPatterns.add("/images/**");
    blackPatterns.add("/fonts/**");
    blackPatterns.add("/css/**");
    blackPatterns.add("/assets/**");
    blackPatterns.add("/color.less");
    blackPatterns.add("/swagger-ui.html");
    blackPatterns.add("/swagger-ui/**");
    blackPatterns.add("/csrf");
    blackPatterns.add("/webjars/**");
    blackPatterns.add(uploadUrlPattern);
    blackPatterns.add(adminPathPattern);
}


}