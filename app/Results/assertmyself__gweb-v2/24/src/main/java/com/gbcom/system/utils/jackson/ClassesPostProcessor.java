package com.gbcom.system.utils.jackson;
 import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class ClassesPostProcessor implements InitializingBean,ResourceLoaderAware{

 private  List<String> packages;

 private  Set<Class<?>> achiveClasses;

 private  String CLASS_RESOURCE_PATTERN;

 private  ResourcePatternResolver resourcePatternResolver;

 private  MetadataReaderFactory metadataReaderFactory;


public void setAchiveClasses(Set<Class<?>> achiveClasses){
    this.achiveClasses = achiveClasses;
}


public Set<Class<?>> getAchiveClasses(){
    return achiveClasses;
}


public void afterPropertiesSet(){
    if (packages != null) {
        achiveClasses = new HashSet<Class<?>>();
        try {
            for (String p : packages) {
                String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(p)) + "/" + CLASS_RESOURCE_PATTERN;
                Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
                for (Resource resource : resources) {
                    if (resource.isReadable()) {
                        // 将注解过滤功能注释
                        MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                        achiveClasses.add(Class.forName(metadataReader.getAnnotationMetadata().getClassName()));
                    }
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException("I/O failure during classpath scanning", ex);
        }
    }
}


public void setResourceLoader(ResourceLoader resourceLoader){
    this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
    this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
}


public void setPackages(List<String> packages){
    this.packages = packages;
}


}