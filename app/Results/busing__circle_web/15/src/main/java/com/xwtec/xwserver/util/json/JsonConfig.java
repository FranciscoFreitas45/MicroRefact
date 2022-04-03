package com.xwtec.xwserver.util.json;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.map.MultiKeyMap;
import org.apache.commons.lang.StringUtils;
import com.xwtec.xwserver.util.json.processors.DefaultDefaultValueProcessor;
import com.xwtec.xwserver.util.json.processors.DefaultValueProcessor;
import com.xwtec.xwserver.util.json.processors.DefaultValueProcessorMatcher;
import com.xwtec.xwserver.util.json.processors.JsonBeanProcessor;
import com.xwtec.xwserver.util.json.processors.JsonBeanProcessorMatcher;
import com.xwtec.xwserver.util.json.processors.JsonValueProcessor;
import com.xwtec.xwserver.util.json.processors.JsonValueProcessorMatcher;
import com.xwtec.xwserver.util.json.processors.PropertyNameProcessor;
import com.xwtec.xwserver.util.json.processors.PropertyNameProcessorMatcher;
import com.xwtec.xwserver.util.json.util.CycleDetectionStrategy;
import com.xwtec.xwserver.util.json.util.JavaIdentifierTransformer;
import com.xwtec.xwserver.util.json.util.JsonEventListener;
import com.xwtec.xwserver.util.json.util.NewBeanInstanceStrategy;
import com.xwtec.xwserver.util.json.util.PropertyExclusionClassMatcher;
import com.xwtec.xwserver.util.json.util.PropertyFilter;
import com.xwtec.xwserver.util.json.util.PropertySetStrategy;
import com.Interface.JavaIdentifierTransformer;
import com.Interface.JavaIdentifierTransformer;
import com.Interface.PropertyFilter;
import com.Interface.PropertyFilter;
@SuppressWarnings("unchecked")
public class JsonConfig {

 public  DefaultValueProcessorMatcher DEFAULT_DEFAULT_VALUE_PROCESSOR_MATCHER;

 public  JsonBeanProcessorMatcher DEFAULT_JSON_BEAN_PROCESSOR_MATCHER;

 public  JsonValueProcessorMatcher DEFAULT_JSON_VALUE_PROCESSOR_MATCHER;

 public  NewBeanInstanceStrategy DEFAULT_NEW_BEAN_INSTANCE_STRATEGY;

 public  PropertyExclusionClassMatcher DEFAULT_PROPERTY_EXCLUSION_CLASS_MATCHER;

 public  PropertyNameProcessorMatcher DEFAULT_PROPERTY_NAME_PROCESSOR_MATCHER;

 public  int MODE_LIST;

 public  int MODE_OBJECT_ARRAY;

 public  int MODE_SET;

 private  Class DEFAULT_COLLECTION_TYPE;

 private  CycleDetectionStrategy DEFAULT_CYCLE_DETECTION_STRATEGY;

 private  String[] DEFAULT_EXCLUDES;

 private  JavaIdentifierTransformer DEFAULT_JAVA_IDENTIFIER_TRANSFORMER;

 private  DefaultValueProcessor DEFAULT_VALUE_PROCESSOR;

 private  String[] EMPTY_EXCLUDES;

 private  int arrayMode;

 private  MultiKeyMap beanKeyMap;

 private  Map beanProcessorMap;

 private  MultiKeyMap beanTypeMap;

 private  Map classMap;

 private  Class collectionType;

 private  CycleDetectionStrategy cycleDetectionStrategy;

 private  Map defaultValueMap;

 private  DefaultValueProcessorMatcher defaultValueProcessorMatcher;

 private  Class enclosedType;

 private  List eventListeners;

 private  String[] excludes;

 private  Map exclusionMap;

 private  boolean handleJettisonEmptyElement;

 private  boolean handleJettisonSingleElementArray;

 private  boolean ignoreDefaultExcludes;

 private  boolean ignoreTransientFields;

 private  boolean ignorePublicFields;

 private  boolean javascriptCompliant;

 private  JavaIdentifierTransformer javaIdentifierTransformer;

 private  PropertyFilter javaPropertyFilter;

 private  Map javaPropertyNameProcessorMap;

 private  PropertyNameProcessorMatcher javaPropertyNameProcessorMatcher;

 private  JsonBeanProcessorMatcher jsonBeanProcessorMatcher;

 private  PropertyFilter jsonPropertyFilter;

 private  Map jsonPropertyNameProcessorMap;

 private  PropertyNameProcessorMatcher jsonPropertyNameProcessorMatcher;

 private  JsonValueProcessorMatcher jsonValueProcessorMatcher;

 private  Map keyMap;

 private  NewBeanInstanceStrategy newBeanInstanceStrategy;

 private  PropertyExclusionClassMatcher propertyExclusionClassMatcher;

 private  PropertySetStrategy propertySetStrategy;

 private  Class rootClass;

 private  boolean skipJavaIdentifierTransformationInMapKeys;

 private  boolean triggerEvents;

 private  Map typeMap;

 private  List ignoreFieldAnnotations;

 private  boolean allowNonStringKeys;

public JsonConfig() {
}
public void setIgnoreDefaultExcludes(boolean ignoreDefaultExcludes){
    this.ignoreDefaultExcludes = ignoreDefaultExcludes;
}


public Class getEnclosedType(){
    return enclosedType;
}


public PropertyNameProcessorMatcher getJavaPropertyNameProcessorMatcher(){
    return javaPropertyNameProcessorMatcher;
}


public void unregisterPropertyExclusions(Class target){
    if (target != null) {
        Set set = (Set) exclusionMap.get(target);
        if (set != null) {
            set.clear();
        }
    }
}


public void clearJsonValueProcessors(){
    beanKeyMap.clear();
    beanTypeMap.clear();
    keyMap.clear();
    typeMap.clear();
}


public void clearPropertyExclusions(){
    exclusionMap.clear();
}


public void registerPropertyExclusions(Class target,String[] properties){
    if (target != null && properties != null && properties.length > 0) {
        Set set = (Set) exclusionMap.get(target);
        if (set == null) {
            set = new HashSet();
            exclusionMap.put(target, set);
        }
        for (int i = 0; i < properties.length; i++) {
            if (!set.contains(properties[i])) {
                set.add(properties[i]);
            }
        }
    }
}


public void setJavaIdentifierTransformer(JavaIdentifierTransformer javaIdentifierTransformer){
    this.javaIdentifierTransformer = javaIdentifierTransformer == null ? DEFAULT_JAVA_IDENTIFIER_TRANSFORMER : javaIdentifierTransformer;
}


public void unregisterDefaultValueProcessor(Class target){
    if (target != null) {
        defaultValueMap.remove(target);
    }
}


public PropertyNameProcessorMatcher getJsonPropertyNameProcessorMatcher(){
    return javaPropertyNameProcessorMatcher;
}


public List getIgnoreFieldAnnotations(){
    return Collections.unmodifiableList(ignoreFieldAnnotations);
}


public void setJsonPropertyNameProcessorMatcher(PropertyNameProcessorMatcher propertyNameProcessorMatcher){
    this.jsonPropertyNameProcessorMatcher = propertyNameProcessorMatcher == null ? DEFAULT_PROPERTY_NAME_PROCESSOR_MATCHER : propertyNameProcessorMatcher;
}


public boolean isJavascriptCompliant(){
    return javascriptCompliant;
}


public void removeJsonEventListener(JsonEventListener listener){
    eventListeners.remove(listener);
}


public void registerJsonPropertyNameProcessor(Class target,PropertyNameProcessor propertyNameProcessor){
    if (target != null && propertyNameProcessor != null) {
        jsonPropertyNameProcessorMap.put(target, propertyNameProcessor);
    }
}


public Map getClassMap(){
    return classMap;
}


public void registerJsonBeanProcessor(Class target,JsonBeanProcessor jsonBeanProcessor){
    if (target != null && jsonBeanProcessor != null) {
        beanProcessorMap.put(target, jsonBeanProcessor);
    }
}


public JsonValueProcessor findJsonValueProcessor(Class propertyType,String key){
    JsonValueProcessor jsonValueProcessor = null;
    jsonValueProcessor = (JsonValueProcessor) keyMap.get(key);
    if (jsonValueProcessor != null) {
        return jsonValueProcessor;
    }
    Object tkey = jsonValueProcessorMatcher.getMatch(propertyType, typeMap.keySet());
    jsonValueProcessor = (JsonValueProcessor) typeMap.get(tkey);
    if (jsonValueProcessor != null) {
        return jsonValueProcessor;
    }
    return null;
}


public NewBeanInstanceStrategy getNewBeanInstanceStrategy(){
    return newBeanInstanceStrategy;
}


public void setNewBeanInstanceStrategy(NewBeanInstanceStrategy newBeanInstanceStrategy){
    this.newBeanInstanceStrategy = newBeanInstanceStrategy == null ? DEFAULT_NEW_BEAN_INSTANCE_STRATEGY : newBeanInstanceStrategy;
}


public Class getRootClass(){
    return rootClass;
}


public void setSkipJavaIdentifierTransformationInMapKeys(boolean skipJavaIdentifierTransformationInMapKeys){
    this.skipJavaIdentifierTransformationInMapKeys = skipJavaIdentifierTransformationInMapKeys;
}


public void unregisterJsonBeanProcessor(Class target){
    if (target != null) {
        beanProcessorMap.remove(target);
    }
}


public void clearJavaPropertyNameProcessors(){
    javaPropertyNameProcessorMap.clear();
}


public void disableEventTriggering(){
    triggerEvents = false;
}


public void setExcludes(String[] excludes){
    this.excludes = excludes == null ? EMPTY_EXCLUDES : excludes;
}


public void setIgnoreTransientFields(boolean ignoreTransientFields){
    this.ignoreTransientFields = ignoreTransientFields;
}


public PropertyNameProcessor findPropertyNameProcessor(Class beanClass){
    return findJavaPropertyNameProcessor(beanClass);
}


public void reset(){
    excludes = EMPTY_EXCLUDES;
    ignoreDefaultExcludes = false;
    ignoreTransientFields = false;
    ignorePublicFields = true;
    javascriptCompliant = false;
    javaIdentifierTransformer = DEFAULT_JAVA_IDENTIFIER_TRANSFORMER;
    cycleDetectionStrategy = DEFAULT_CYCLE_DETECTION_STRATEGY;
    skipJavaIdentifierTransformationInMapKeys = false;
    triggerEvents = false;
    handleJettisonEmptyElement = false;
    handleJettisonSingleElementArray = false;
    arrayMode = MODE_LIST;
    rootClass = null;
    classMap = null;
    keyMap.clear();
    typeMap.clear();
    beanKeyMap.clear();
    beanTypeMap.clear();
    jsonPropertyFilter = null;
    javaPropertyFilter = null;
    jsonBeanProcessorMatcher = DEFAULT_JSON_BEAN_PROCESSOR_MATCHER;
    newBeanInstanceStrategy = DEFAULT_NEW_BEAN_INSTANCE_STRATEGY;
    defaultValueProcessorMatcher = DEFAULT_DEFAULT_VALUE_PROCESSOR_MATCHER;
    defaultValueMap.clear();
    propertySetStrategy = null;
    // ignoreJPATransient = false;
    collectionType = DEFAULT_COLLECTION_TYPE;
    enclosedType = null;
    jsonValueProcessorMatcher = DEFAULT_JSON_VALUE_PROCESSOR_MATCHER;
    javaPropertyNameProcessorMap.clear();
    javaPropertyNameProcessorMatcher = DEFAULT_PROPERTY_NAME_PROCESSOR_MATCHER;
    jsonPropertyNameProcessorMap.clear();
    jsonPropertyNameProcessorMatcher = DEFAULT_PROPERTY_NAME_PROCESSOR_MATCHER;
    beanProcessorMap.clear();
    propertyExclusionClassMatcher = DEFAULT_PROPERTY_EXCLUSION_CLASS_MATCHER;
    exclusionMap.clear();
    ignoreFieldAnnotations.clear();
    allowNonStringKeys = false;
}


public void setHandleJettisonSingleElementArray(boolean handleJettisonSingleElementArray){
    this.handleJettisonSingleElementArray = handleJettisonSingleElementArray;
}


public void clearJsonEventListeners(){
    eventListeners.clear();
}


public PropertyNameProcessorMatcher getPropertyNameProcessorMatcher(){
    return getJavaPropertyNameProcessorMatcher();
}


public boolean isIgnoreTransientFields(){
    return ignoreTransientFields;
}


public PropertyNameProcessor findJavaPropertyNameProcessor(Class beanClass){
    if (!javaPropertyNameProcessorMap.isEmpty()) {
        Object key = javaPropertyNameProcessorMatcher.getMatch(beanClass, javaPropertyNameProcessorMap.keySet());
        return (PropertyNameProcessor) javaPropertyNameProcessorMap.get(key);
    }
    return null;
}


public void setIgnoreJPATransient(boolean ignoreJPATransient){
    if (ignoreJPATransient) {
        addIgnoreFieldAnnotation("javax.persistence.Transient");
    } else {
        removeIgnoreFieldAnnotation("javax.persistence.Transient");
    }
}


public void unregisterJsonPropertyNameProcessor(Class target){
    if (target != null) {
        jsonPropertyNameProcessorMap.remove(target);
    }
}


public void setJavaPropertyFilter(PropertyFilter javaPropertyFilter){
    this.javaPropertyFilter = javaPropertyFilter;
}


public Collection getMergedExcludes(Class target){
    if (target == null) {
        return getMergedExcludes();
    }
    Collection exclusionSet = getMergedExcludes();
    if (!exclusionMap.isEmpty()) {
        Object key = propertyExclusionClassMatcher.getMatch(target, exclusionMap.keySet());
        Set set = (Set) exclusionMap.get(key);
        if (set != null && !set.isEmpty()) {
            for (Iterator i = set.iterator(); i.hasNext(); ) {
                Object e = i.next();
                if (!exclusionSet.contains(e)) {
                    exclusionSet.add(e);
                }
            }
        }
    }
    return exclusionSet;
}


public void removeIgnoreFieldAnnotation(Class annotationClass){
    if (annotationClass != null)
        ignoreFieldAnnotations.remove(annotationClass.getName());
}


public JsonConfig copy(){
    JsonConfig jsc = new JsonConfig();
    jsc.beanKeyMap.putAll(beanKeyMap);
    jsc.beanTypeMap.putAll(beanTypeMap);
    jsc.classMap = new HashMap();
    if (classMap != null) {
        jsc.classMap.putAll(classMap);
    }
    jsc.cycleDetectionStrategy = cycleDetectionStrategy;
    if (eventListeners != null) {
        jsc.eventListeners.addAll(eventListeners);
    }
    if (excludes != null) {
        jsc.excludes = new String[excludes.length];
        System.arraycopy(excludes, 0, jsc.excludes, 0, excludes.length);
    }
    jsc.handleJettisonEmptyElement = handleJettisonEmptyElement;
    jsc.handleJettisonSingleElementArray = handleJettisonSingleElementArray;
    jsc.ignoreDefaultExcludes = ignoreDefaultExcludes;
    jsc.ignoreTransientFields = ignoreTransientFields;
    jsc.ignorePublicFields = ignorePublicFields;
    jsc.javaIdentifierTransformer = javaIdentifierTransformer;
    jsc.javascriptCompliant = javascriptCompliant;
    jsc.keyMap.putAll(keyMap);
    jsc.beanProcessorMap.putAll(beanProcessorMap);
    jsc.rootClass = rootClass;
    jsc.skipJavaIdentifierTransformationInMapKeys = skipJavaIdentifierTransformationInMapKeys;
    jsc.triggerEvents = triggerEvents;
    jsc.typeMap.putAll(typeMap);
    jsc.jsonPropertyFilter = jsonPropertyFilter;
    jsc.javaPropertyFilter = javaPropertyFilter;
    jsc.jsonBeanProcessorMatcher = jsonBeanProcessorMatcher;
    jsc.newBeanInstanceStrategy = newBeanInstanceStrategy;
    jsc.defaultValueProcessorMatcher = defaultValueProcessorMatcher;
    jsc.defaultValueMap.putAll(defaultValueMap);
    jsc.propertySetStrategy = propertySetStrategy;
    // jsc.ignoreJPATransient = ignoreJPATransient;
    jsc.collectionType = collectionType;
    jsc.enclosedType = enclosedType;
    jsc.jsonValueProcessorMatcher = jsonValueProcessorMatcher;
    jsc.javaPropertyNameProcessorMatcher = javaPropertyNameProcessorMatcher;
    jsc.javaPropertyNameProcessorMap.putAll(javaPropertyNameProcessorMap);
    jsc.jsonPropertyNameProcessorMatcher = jsonPropertyNameProcessorMatcher;
    jsc.jsonPropertyNameProcessorMap.putAll(jsonPropertyNameProcessorMap);
    jsc.propertyExclusionClassMatcher = propertyExclusionClassMatcher;
    jsc.exclusionMap.putAll(exclusionMap);
    jsc.ignoreFieldAnnotations.addAll(ignoreFieldAnnotations);
    jsc.allowNonStringKeys = allowNonStringKeys;
    return jsc;
}


public void setEnclosedType(Class enclosedType){
    this.enclosedType = enclosedType;
}


public boolean isIgnoreJPATransient(){
    return ignoreFieldAnnotations.contains("javax.persistence.Transient");
}


public void setJavaPropertyNameProcessorMatcher(PropertyNameProcessorMatcher propertyNameProcessorMatcher){
    this.javaPropertyNameProcessorMatcher = propertyNameProcessorMatcher == null ? DEFAULT_PROPERTY_NAME_PROCESSOR_MATCHER : propertyNameProcessorMatcher;
}


public void setJavascriptCompliant(boolean javascriptCompliant){
    this.javascriptCompliant = javascriptCompliant;
}


public void setPropertyNameProcessorMatcher(PropertyNameProcessorMatcher propertyNameProcessorMatcher){
    setJavaPropertyNameProcessorMatcher(propertyNameProcessorMatcher);
}


public JavaIdentifierTransformer getJavaIdentifierTransformer(){
    return javaIdentifierTransformer;
}


public boolean isHandleJettisonEmptyElement(){
    return handleJettisonEmptyElement;
}


public void registerPropertyExclusion(Class target,String propertyName){
    if (target != null && propertyName != null) {
        Set set = (Set) exclusionMap.get(target);
        if (set == null) {
            set = new HashSet();
            exclusionMap.put(target, set);
        }
        if (!set.contains(propertyName)) {
            set.add(propertyName);
        }
    }
}


public void registerPropertyNameProcessor(Class target,PropertyNameProcessor propertyNameProcessor){
    registerJavaPropertyNameProcessor(target, propertyNameProcessor);
}


public void setHandleJettisonEmptyElement(boolean handleJettisonEmptyElement){
    this.handleJettisonEmptyElement = handleJettisonEmptyElement;
}


public void setJsonBeanProcessorMatcher(JsonBeanProcessorMatcher jsonBeanProcessorMatcher){
    this.jsonBeanProcessorMatcher = jsonBeanProcessorMatcher == null ? DEFAULT_JSON_BEAN_PROCESSOR_MATCHER : jsonBeanProcessorMatcher;
}


public void unregisterPropertyExclusion(Class target,String propertyName){
    if (target != null && propertyName != null) {
        Set set = (Set) exclusionMap.get(target);
        if (set == null) {
            set = new HashSet();
            exclusionMap.put(target, set);
        }
        set.remove(propertyName);
    }
}


public void setCycleDetectionStrategy(CycleDetectionStrategy cycleDetectionStrategy){
    this.cycleDetectionStrategy = cycleDetectionStrategy == null ? DEFAULT_CYCLE_DETECTION_STRATEGY : cycleDetectionStrategy;
}


public void registerDefaultValueProcessor(Class target,DefaultValueProcessor defaultValueProcessor){
    if (target != null && defaultValueProcessor != null) {
        defaultValueMap.put(target, defaultValueProcessor);
    }
}


public void setJsonPropertyFilter(PropertyFilter jsonPropertyFilter){
    this.jsonPropertyFilter = jsonPropertyFilter;
}


public JsonBeanProcessorMatcher getJsonBeanProcessorMatcher(){
    return jsonBeanProcessorMatcher;
}


public void unregisterJsonValueProcessor(String key){
    if (key != null) {
        keyMap.remove(key);
    }
}


public void clearJsonPropertyNameProcessors(){
    jsonPropertyNameProcessorMap.clear();
}


public void setArrayMode(int arrayMode){
    if (arrayMode == MODE_OBJECT_ARRAY) {
        this.arrayMode = arrayMode;
    } else if (arrayMode == MODE_SET) {
        this.arrayMode = arrayMode;
        this.collectionType = Set.class;
    } else {
        this.arrayMode = MODE_LIST;
        this.enclosedType = DEFAULT_COLLECTION_TYPE;
    }
}


public void setDefaultValueProcessorMatcher(DefaultValueProcessorMatcher defaultValueProcessorMatcher){
    this.defaultValueProcessorMatcher = defaultValueProcessorMatcher == null ? DEFAULT_DEFAULT_VALUE_PROCESSOR_MATCHER : defaultValueProcessorMatcher;
}


public void registerJavaPropertyNameProcessor(Class target,PropertyNameProcessor propertyNameProcessor){
    if (target != null && propertyNameProcessor != null) {
        javaPropertyNameProcessorMap.put(target, propertyNameProcessor);
    }
}


public void clearPropertyNameProcessors(){
    clearJavaPropertyNameProcessors();
}


public DefaultValueProcessorMatcher getDefaultValueProcessorMatcher(){
    return defaultValueProcessorMatcher;
}


public boolean isEventTriggeringEnabled(){
    return triggerEvents;
}


public int getArrayMode(){
    return arrayMode;
}


public PropertyFilter getJavaPropertyFilter(){
    return javaPropertyFilter;
}


public void setClassMap(Map classMap){
    this.classMap = classMap;
}


public PropertyExclusionClassMatcher getPropertyExclusionClassMatcher(){
    return propertyExclusionClassMatcher;
}


public void registerJsonValueProcessor(String key,JsonValueProcessor jsonValueProcessor){
    if (key != null && jsonValueProcessor != null) {
        keyMap.put(key, jsonValueProcessor);
    }
}


public JsonValueProcessorMatcher getJsonValueProcessorMatcher(){
    return jsonValueProcessorMatcher;
}


public boolean isHandleJettisonSingleElementArray(){
    return handleJettisonSingleElementArray;
}


public boolean isIgnoreDefaultExcludes(){
    return ignoreDefaultExcludes;
}


public void clearJsonBeanProcessors(){
    beanProcessorMap.clear();
}


public void setIgnorePublicFields(boolean ignorePublicFields){
    this.ignorePublicFields = ignorePublicFields;
}


public PropertyFilter getJsonPropertyFilter(){
    return jsonPropertyFilter;
}


public void setPropertySetStrategy(PropertySetStrategy propertySetStrategy){
    this.propertySetStrategy = propertySetStrategy;
}


public void unregisterJavaPropertyNameProcessor(Class target){
    if (target != null) {
        javaPropertyNameProcessorMap.remove(target);
    }
}


public void addJsonEventListener(JsonEventListener listener){
    if (!eventListeners.contains(listener)) {
        eventListeners.add(listener);
    }
}


public boolean isIgnorePublicFields(){
    return ignorePublicFields;
}


public PropertySetStrategy getPropertySetStrategy(){
    return propertySetStrategy;
}


public PropertyNameProcessor findJsonPropertyNameProcessor(Class beanClass){
    if (!jsonPropertyNameProcessorMap.isEmpty()) {
        Object key = jsonPropertyNameProcessorMatcher.getMatch(beanClass, jsonPropertyNameProcessorMap.keySet());
        return (PropertyNameProcessor) jsonPropertyNameProcessorMap.get(key);
    }
    return null;
}


public Class getCollectionType(){
    return collectionType;
}


public void setPropertyExclusionClassMatcher(PropertyExclusionClassMatcher propertyExclusionClassMatcher){
    this.propertyExclusionClassMatcher = propertyExclusionClassMatcher == null ? DEFAULT_PROPERTY_EXCLUSION_CLASS_MATCHER : propertyExclusionClassMatcher;
}


public CycleDetectionStrategy getCycleDetectionStrategy(){
    return cycleDetectionStrategy;
}


public JsonBeanProcessor findJsonBeanProcessor(Class target){
    if (!beanProcessorMap.isEmpty()) {
        Object key = jsonBeanProcessorMatcher.getMatch(target, beanProcessorMap.keySet());
        return (JsonBeanProcessor) beanProcessorMap.get(key);
    }
    return null;
}


public boolean isSkipJavaIdentifierTransformationInMapKeys(){
    return skipJavaIdentifierTransformationInMapKeys;
}


public void addIgnoreFieldAnnotation(Class annotationClass){
    if (annotationClass != null && !ignoreFieldAnnotations.contains(annotationClass.getName())) {
        ignoreFieldAnnotations.add(annotationClass.getName());
    }
}


public void setCollectionType(Class collectionType){
    if (collectionType != null) {
        if (!Collection.class.isAssignableFrom(collectionType)) {
            throw new JSONException("The configured collectionType is not a Collection: " + collectionType.getName());
        }
        this.collectionType = collectionType;
    } else {
        collectionType = DEFAULT_COLLECTION_TYPE;
    }
}


public void unregisterPropertyNameProcessor(Class target){
    unregisterJavaPropertyNameProcessor(target);
}


public void setRootClass(Class rootClass){
    this.rootClass = rootClass;
}


public void setAllowNonStringKeys(boolean allowNonStringKeys){
    this.allowNonStringKeys = allowNonStringKeys;
}


public List getJsonEventListeners(){
    return eventListeners;
}


public boolean isAllowNonStringKeys(){
    return allowNonStringKeys;
}


public String[] getExcludes(){
    return excludes;
}


public void setJsonValueProcessorMatcher(JsonValueProcessorMatcher jsonValueProcessorMatcher){
    this.jsonValueProcessorMatcher = jsonValueProcessorMatcher == null ? DEFAULT_JSON_VALUE_PROCESSOR_MATCHER : jsonValueProcessorMatcher;
}


public DefaultValueProcessor findDefaultValueProcessor(Class target){
    if (!defaultValueMap.isEmpty()) {
        Object key = defaultValueProcessorMatcher.getMatch(target, defaultValueMap.keySet());
        DefaultValueProcessor processor = (DefaultValueProcessor) defaultValueMap.get(key);
        if (processor != null) {
            return processor;
        }
    }
    return DEFAULT_VALUE_PROCESSOR;
}


public void enableEventTriggering(){
    triggerEvents = true;
}


}