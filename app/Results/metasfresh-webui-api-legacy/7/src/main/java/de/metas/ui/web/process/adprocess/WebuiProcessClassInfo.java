package de.metas.ui.web.process.adprocess;
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


public WebuiProcessClassInfo createWebuiProcessClassInfo(Class<?> processClass){
    final ProcessClassInfo processClassInfo = ProcessClassInfo.of(processClass);
    final WebuiProcess webuiProcessAnn = processClass.getAnnotation(WebuiProcess.class);
    @SuppressWarnings("unchecked")
    final Set<Method> lookupValuesProviderMethods = ReflectionUtils.getAllMethods(processClass, ReflectionUtils.withAnnotation(ProcessParamLookupValuesProvider.class));
    final ImmutableMap<String, LookupDescriptorProvider> paramLookupValuesProviders = lookupValuesProviderMethods.stream().map(method -> createParamLookupValuesProvider(method)).collect(GuavaCollectors.toImmutableMap());
    // 
    // Check is there were no settings at all so we could return our NULL instance
    if (ProcessClassInfo.isNull(processClassInfo) && paramLookupValuesProviders.isEmpty()) {
        return NULL;
    }
    return new WebuiProcessClassInfo(processClassInfo, webuiProcessAnn, paramLookupValuesProviders);
}


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


public boolean isForwardValueToJavaProcessInstance(String parameterName){
    return !processClassInfo.getParameterInfos(parameterName).isEmpty();
}


public void resetCache(){
    cache.invalidateAll();
    cache.cleanUp();
}


@Override
public WebuiProcessClassInfo load(Class<?> processClass){
    try {
        return createWebuiProcessClassInfo(processClass);
    } catch (final Throwable e) {
        logger.error("Failed introspecting process class info: {}. Fallback to defaults: {}", processClass, NULL, e);
        return NULL;
    }
}


public PanelLayoutType getLayoutType(){
    return layoutType;
}


public Map.Entry<String,LookupDescriptorProvider> createParamLookupValuesProvider(Method method){
    final ProcessParamLookupValuesProvider ann = method.getAnnotation(ProcessParamLookupValuesProvider.class);
    if (!LookupValuesList.class.isAssignableFrom(method.getReturnType())) {
        throw new AdempiereException("Method's return type shall be " + LookupValuesList.class + ": " + method);
    }
    final ImmutableList<Function<LookupDataSourceContext, Object>> parameterValueProviders = Stream.of(method.getParameterTypes()).map(parameterType -> {
        final Function<LookupDataSourceContext, Object> parameterValueProvider;
        if (LookupDataSourceContext.class.isAssignableFrom(parameterType)) {
            parameterValueProvider = evalCtx -> evalCtx;
        } else {
            throw new AdempiereException("Parameter " + parameterType + " not supported for " + method);
        }
        return parameterValueProvider;
    }).collect(ImmutableList.toImmutableList());
    // FIXME: holding a hard reference to method may introduce ClassLoader memory leaks
    final Method methodToInvoke = method;
    final LookupDescriptor lookupDescriptor = ListLookupDescriptor.builder().setLookupTableName(ann.lookupTableName()).setDependsOnFieldNames(ann.dependsOn()).setLookupSourceType(ann.lookupSource()).setLookupValues(ann.numericKey(), evalCtx -> retriveLookupValues(methodToInvoke, parameterValueProviders, evalCtx)).build();
    final LookupDescriptorProvider lookupDescriptorProvider = LookupDescriptorProviders.singleton(lookupDescriptor);
    return GuavaCollectors.entry(ann.parameterName(), lookupDescriptorProvider);
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
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("paramLookupValuesProviders", paramLookupValuesProviders).add("processClassInfo", processClassInfo).toString();
}


public LookupDescriptorProvider getLookupDescriptorProviderOrNull(String parameterName){
    return paramLookupValuesProviders.get(parameterName);
}


public LookupValuesList retriveLookupValues(Method method,List<Function<LookupDataSourceContext,Object>> parameterValueProviders,LookupDataSourceContext evalCtx){
    Check.assumeNotNull(method, "Parameter method is not null");
    final JavaProcess processClassInstance = JavaProcess.currentInstance();
    final Object[] methodParams = parameterValueProviders.stream().map(paramValueProvider -> paramValueProvider.apply(evalCtx)).toArray();
    try {
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        final LookupValuesList lookupValues = (LookupValuesList) method.invoke(processClassInstance, methodParams);
        return lookupValues;
    } catch (IllegalAccessException | InvocationTargetException e) {
        final Throwable cause = AdempiereException.extractCause(e);
        throw new AdempiereException("Failed invoking " + method + " using " + methodParams, cause);
    } catch (final Exception e) {
        throw AdempiereException.wrapIfNeeded(e);
    }
}


}