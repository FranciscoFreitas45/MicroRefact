package DTO;
 import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import java.util.Objects;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.logging.LogManager;
import de.metas.process.BarcodeScannerType;
import de.metas.process.JavaProcess;
import de.metas.process.ProcessClassInfo;
import de.metas.process.ProcessClassParamInfo;
import de.metas.ui.web.process.descriptor.ProcessParamLookupValuesProvider;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.ui.web.window.descriptor.ListLookupDescriptor;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.LookupDescriptorProvider;
import de.metas.ui.web.window.descriptor.LookupDescriptorProviders;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
public class WebuiProcessClassInfo {

 private  Logger logger;

 private  LoadingCache<Class<?>,WebuiProcessClassInfo> cache;

 private  WebuiProcessClassInfo NULL;

 private  ProcessClassInfo processClassInfo;

 private  ImmutableMap<String,LookupDescriptorProvider> paramLookupValuesProviders;

 private  PanelLayoutType layoutType;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public BarcodeScannerType getBarcodeScannerTypeOrNull(String parameterName){
    final ImmutableSet<BarcodeScannerType> barcodeScannerTypes = processClassInfo.getParameterInfos(parameterName).stream().map(ProcessClassParamInfo::getBarcodeScannerType).filter(Objects::nonNull).collect(ImmutableSet.toImmutableSet());
    if (barcodeScannerTypes.isEmpty()) {
        return null;
    } else if (barcodeScannerTypes.size() == 1) {
        return barcodeScannerTypes.iterator().next();
    } else {
        logger.warn("More than one BarcodeScannerType defined for '{}': {}. Returning null.", parameterName, barcodeScannerTypes);
        return null;
    }
}


public PanelLayoutType getLayoutType(){
    return layoutType;
}


public LookupDescriptorProvider getLookupDescriptorProviderOrNull(String parameterName){
    return paramLookupValuesProviders.get(parameterName);
}


public WebuiProcessClassInfo of(String processClassname){
    if (Check.isEmpty(processClassname, true)) {
        return NULL;
    }
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    if (classLoader == null) {
        classLoader = WebuiProcessClassInfo.class.getClassLoader();
    }
    try {
        final Class<?> processClass = classLoader.loadClass(processClassname.trim());
        return of(processClass);
    } catch (final ClassNotFoundException e) {
        logger.info("Could not load process class for {}. IGNORED", processClassname);
        return NULL;
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("processClassname",processClassname);
WebuiProcessClassInfo aux = restTemplate.getForObject(builder.toUriString(),WebuiProcessClassInfo.class);
return aux;
}


public boolean isForwardValueToJavaProcessInstance(String parameterName){
    return !processClassInfo.getParameterInfos(parameterName).isEmpty();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isForwardValueToJavaProcessInstance"))

.queryParam("parameterName",parameterName);
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}