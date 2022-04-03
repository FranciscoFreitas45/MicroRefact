package de.metas.ui.web.view.descriptor.annotation;
 import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewRowFieldNameAndJsonValues;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.TranslationSource;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.ViewColumnLayout;
import de.metas.ui.web.view.descriptor.annotation.ViewColumn.ViewColumnLayout.Displayed;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.MediaType;
import de.metas.ui.web.window.datatypes.json.JSONNullValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
import de.metas.ui.web.window.descriptor.ViewEditorRenderMode;
import de.metas.ui.web.window.descriptor.WidgetSize;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import de.metas.util.StringUtils;
import de.metas.util.lang.ReferenceListAwareEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.UtilityClass;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.mm.attributes.api.IAttributeDAO;
import org.adempiere.service.ISysConfigBL;
import org.adempiere.util.reflect.FieldReference;
import org.compiere.Adempiere;
import org.compiere.util.Env;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.stream.Stream;
@UtilityClass
public class ViewColumnHelper {

 private  Logger logger;

 private  LoadingCache<Class<?>,ClassViewDescriptor> descriptorsByClass;

@NonNull
 private  String fieldName;

 private  WidgetSize widgetSize;

@Singular
 private  ImmutableSet<MediaType> restrictToMediaTypes;

 public  ClassViewDescriptor EMPTY;

 private  ImmutableMap<String,ClassViewColumnDescriptor> columnsByName;

@Getter
 private  ImmutableMap<String,DocumentFieldWidgetType> widgetTypesByFieldName;

@NonNull
 private  String fieldName;

@NonNull
@Getter(AccessLevel.NONE)
 private  FieldReference fieldReference;

@NonNull
 private  ITranslatableString caption;

@NonNull
 private  DocumentFieldWidgetType widgetType;

 private  int listReferenceId;

@Nullable
 private  WidgetSize widgetSize;

@NonNull
 private  ViewEditorRenderMode editorRenderMode;

 private  boolean allowSorting;

@NonNull
 private  ImmutableMap<JSONViewDataType,ClassViewColumnLayoutDescriptor> layoutsByViewType;

@NonNull
 private  ImmutableSet<MediaType> restrictToMediaTypes;

@NonNull
 private  JSONViewDataType viewType;

 private  boolean displayed;

 private  int seqNo;


public ClassViewDescriptor getDescriptor(Class<?> dataType){
    try {
        return descriptorsByClass.get(dataType);
    } catch (final ExecutionException e) {
        throw AdempiereException.wrapIfNeeded(e).setParameter("dataType", dataType);
    }
}


public ClassViewColumnDescriptor createClassViewColumnDescriptor(Field field){
    final String fieldName = extractFieldName(field);
    final ViewColumn viewColumnAnn = field.getAnnotation(ViewColumn.class);
    final ImmutableMap<JSONViewDataType, ClassViewColumnLayoutDescriptor> layoutsByViewType = createViewColumnLayoutDescriptors(viewColumnAnn, fieldName);
    return ClassViewColumnDescriptor.builder().fieldName(fieldName).caption(extractCaption(field)).widgetType(viewColumnAnn.widgetType()).listReferenceId(viewColumnAnn.listReferenceId()).editorRenderMode(viewColumnAnn.editor()).allowSorting(viewColumnAnn.sorting()).fieldReference(FieldReference.of(field)).layoutsByViewType(layoutsByViewType).widgetSize(viewColumnAnn.widgetSize()).restrictToMediaTypes(ImmutableSet.copyOf(viewColumnAnn.restrictToMediaTypes())).build();
}


public ImmutableSet<String> extractFieldNames(Class<T> rowType){
    return getDescriptor(rowType).getFieldNames();
}


public ClassViewDescriptor createClassViewDescriptor(Class<?> dataType){
    @SuppressWarnings("unchecked")
    final Set<Field> fields = ReflectionUtils.getAllFields(dataType, ReflectionUtils.withAnnotations(ViewColumn.class));
    final ImmutableList<ClassViewColumnDescriptor> columns = fields.stream().map(field -> createClassViewColumnDescriptor(field)).collect(ImmutableList.toImmutableList());
    if (columns.isEmpty()) {
        return ClassViewDescriptor.EMPTY;
    }
    return ClassViewDescriptor.builder().columns(columns).build();
}


public ClassViewColumnOverrides ofFieldName(String fieldName){
    return builder(fieldName).build();
}


public Object normalizeAndResolveValue(Object valueParam,ClassViewColumnDescriptor column){
    Object result = valueParam;
    if (column.getWidgetType().isLookup()) {
        if (column.getListReferenceId() > 0) {
            result = resolveListValueByCode(column.getListReferenceId(), result);
        }
    }
    if (result == null) {
        return JSONNullValue.instance;
    }
    return result;
}


public ImmutableSet<String> getFieldNames(){
    return columnsByName.keySet();
}


public void cacheReset(){
    descriptorsByClass.invalidateAll();
    descriptorsByClass.cleanUp();
}


public Object extractFieldValueAsJsonObject(T row,ClassViewColumnDescriptor column){
    try {
        final Field field = column.getField();
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        final Object value = field.get(row);
        if (value instanceof Supplier<?>) {
            final Supplier<?> supplier = (Supplier<?>) value;
            return normalizeAndResolveValue(supplier.get(), column);
        } else {
            return normalizeAndResolveValue(value, column);
        }
    } catch (final Exception ex) {
        throw AdempiereException.wrapIfNeeded(ex).setParameter("column", column).setParameter("row", row);
    }
}


@Override
public ClassViewDescriptor load(Class<?> dataType){
    return createClassViewDescriptor(dataType);
}


public ClassViewColumnOverridesBuilder builder(String fieldName){
    return new ClassViewColumnOverridesBuilder().fieldName(fieldName);
}


public ITranslatableString extractCaption(Field field){
    final ViewColumn viewColumnAnn = field.getAnnotation(ViewColumn.class);
    final String captionKey = !Check.isEmpty(viewColumnAnn.captionKey()) ? viewColumnAnn.captionKey() : extractFieldName(field);
    final TranslationSource captionTranslationSource = viewColumnAnn.captionTranslationSource();
    if (captionTranslationSource == TranslationSource.DEFAULT) {
        final IMsgBL msgBL = Services.get(IMsgBL.class);
        return msgBL.translatable(captionKey);
    } else if (captionTranslationSource == TranslationSource.ATTRIBUTE_NAME) {
        final IAttributeDAO attributesRepo = Services.get(IAttributeDAO.class);
        return attributesRepo.getAttributeDisplayNameByValue(captionKey).orElseGet(() -> TranslatableStrings.anyLanguage(captionKey));
    } else {
        logger.warn("Unknown TranslationSource={} for {}. Returning the captionKey={}", captionTranslationSource, field, captionKey);
        return TranslatableStrings.anyLanguage(captionKey);
    }
}


public ImmutableMap<JSONViewDataType,ClassViewColumnLayoutDescriptor> createViewColumnLayoutDescriptors(ViewColumn viewColumnAnn,String fieldName){
    final int defaultSeqNo = viewColumnAnn.seqNo();
    if (viewColumnAnn.layouts().length > 0) {
        return Stream.of(viewColumnAnn.layouts()).map(layoutAnn -> ClassViewColumnLayoutDescriptor.builder().viewType(layoutAnn.when()).displayed(extractDisplayedValue(layoutAnn, fieldName)).seqNo(layoutAnn.seqNo() >= 0 ? layoutAnn.seqNo() : defaultSeqNo).build()).collect(GuavaCollectors.toImmutableMapByKey(ClassViewColumnLayoutDescriptor::getViewType));
    } else if (defaultSeqNo >= 0) {
        return Stream.of(JSONViewDataType.values()).map(viewType -> ClassViewColumnLayoutDescriptor.builder().viewType(viewType).displayed(viewColumnAnn.displayed()).seqNo(defaultSeqNo).build()).collect(GuavaCollectors.toImmutableMapByKey(ClassViewColumnLayoutDescriptor::getViewType));
    } else {
        return ImmutableMap.of();
    }
}


public ClassViewColumnDescriptor createClassViewColumnDescriptorEffective(ClassViewColumnDescriptor column,ClassViewColumnOverrides overrides){
    final ClassViewColumnDescriptor.ClassViewColumnDescriptorBuilder columnBuilder = column.toBuilder();
    if (overrides.getWidgetSize() != null) {
        columnBuilder.widgetSize(overrides.getWidgetSize());
    }
    if (overrides.getRestrictToMediaTypes() != null) {
        columnBuilder.restrictToMediaTypes(overrides.getRestrictToMediaTypes());
    }
    return columnBuilder.build();
}


public boolean extractDisplayedValue(ViewColumnLayout viewColumnLayout,String fieldName){
    if (viewColumnLayout.displayed() == Displayed.FALSE) {
        return false;
    } else if (viewColumnLayout.displayed() == Displayed.SYSCONFIG) {
        final String displayedSysConfigPrefix = viewColumnLayout.displayedSysConfigPrefix();
        if (Check.isEmpty(displayedSysConfigPrefix, true)) {
            return viewColumnLayout.defaultDisplaySysConfig();
        }
        final String sysConfigKey = StringUtils.appendIfNotEndingWith(displayedSysConfigPrefix, ".") + fieldName + ".IsDisplayed";
        return Services.get(ISysConfigBL.class).getBooleanValue(sysConfigKey, viewColumnLayout.defaultDisplaySysConfig(), Env.getAD_Client_ID(), Env.getAD_Org_ID(Env.getCtx()));
    } else if (viewColumnLayout.displayed() == Displayed.TRUE) {
        return true;
    }
    Check.fail("ViewColumnLayout.displayed value={}; viewColumnLayout={}", viewColumnLayout.displayed(), viewColumnLayout);
    return false;
}


public ClassViewColumnDescriptor getColumnByName(String fieldName){
    final ClassViewColumnDescriptor column = columnsByName.get(fieldName);
    if (column == null) {
        throw new AdempiereException("No column found for " + fieldName + " in " + this);
    }
    return column;
}


public List<DocumentLayoutElementDescriptor.Builder> createLayoutElementsForClass(Class<?> dataType,JSONViewDataType viewType){
    return getDescriptor(dataType).streamColumns().filter(column -> column.isDisplayed(viewType)).sorted(Comparator.comparing(column -> column.getSeqNo(viewType))).map(column -> createLayoutElement(column)).collect(ImmutableList.toImmutableList());
}


public boolean isDisplayed(JSONViewDataType viewType){
    final ClassViewColumnLayoutDescriptor layout = layoutsByViewType.get(viewType);
    return layout != null && layout.isDisplayed();
}


public Field getField(){
    return fieldReference.getField();
}


public LookupValue resolveListValueByCode(int listReferenceId,Object code){
    if (code == null) {
        return null;
    }
    if (Adempiere.isUnitTestMode()) {
        if (code instanceof ReferenceListAwareEnum) {
            return StringLookupValue.of(((ReferenceListAwareEnum) code).getCode(), code.toString());
        } else {
            return StringLookupValue.unknown(code.toString());
        }
    }
    final LookupValue lookupValue = LookupDataSourceFactory.instance.listByAD_Reference_Value_ID(listReferenceId).findById(code);
    if (lookupValue == null) {
        return StringLookupValue.unknown(code.toString());
    } else {
        return lookupValue;
    }
}


public List<DocumentLayoutElementDescriptor.Builder> createLayoutElementsForClassAndFieldNames(Class<?> dataType,JSONViewDataType viewDataType,ClassViewColumnOverrides columns){
    Check.assumeNotEmpty(columns, "columnOverrides is not empty");
    final ClassViewDescriptor descriptor = getDescriptor(dataType);
    return Stream.of(columns).map(columnOverride -> {
        final ClassViewColumnDescriptor columnDescriptor = descriptor.getColumnByName(columnOverride.getFieldName());
        return createClassViewColumnDescriptorEffective(columnDescriptor, columnOverride);
    }).map(ViewColumnHelper::createLayoutElement).collect(ImmutableList.toImmutableList());
}


public Map.Entry<String,Object> extractFieldNameAndValueAsJsonObject(T row,ClassViewColumnDescriptor column){
    final Object value = extractFieldValueAsJsonObject(row, column);
    if (JSONNullValue.isNull(value)) {
        return null;
    }
    return GuavaCollectors.entry(column.getFieldName(), value);
}


public int getSeqNo(JSONViewDataType viewType){
    final ClassViewColumnLayoutDescriptor layout = layoutsByViewType.get(viewType);
    if (layout == null) {
        return Integer.MAX_VALUE;
    }
    final int seqNo = layout.getSeqNo();
    return seqNo >= 0 ? seqNo : Integer.MAX_VALUE;
}


public ImmutableMap<String,DocumentFieldWidgetType> getWidgetTypesByFieldName(Class<T> rowClass){
    return getDescriptor(rowClass).getWidgetTypesByFieldName();
}


public Stream<ClassViewColumnDescriptor> streamColumns(){
    return columnsByName.values().stream();
}


public DocumentLayoutElementDescriptor.Builder createLayoutElement(ClassViewColumnDescriptor column){
    return DocumentLayoutElementDescriptor.builder().setGridElement().setCaption(column.getCaption()).setWidgetType(column.getWidgetType()).setWidgetSize(column.getWidgetSize()).setViewEditorRenderMode(column.getEditorRenderMode()).setViewAllowSorting(column.isAllowSorting()).restrictToMediaTypes(column.getRestrictToMediaTypes()).addField(DocumentLayoutElementFieldDescriptor.builder(column.getFieldName()));
}


public String extractFieldName(Field field){
    final ViewColumn viewColumnAnn = field.getAnnotation(ViewColumn.class);
    final String fieldName = !Check.isEmpty(viewColumnAnn.fieldName(), true) ? viewColumnAnn.fieldName().trim() : field.getName();
    return fieldName;
}


public ViewRowFieldNameAndJsonValues extractJsonMap(T row){
    final Class<? extends IViewRow> rowClass = row.getClass();
    final ImmutableMap<String, Object> map = getDescriptor(rowClass).streamColumns().map(column -> extractFieldNameAndValueAsJsonObject(row, column)).filter(Objects::nonNull).collect(GuavaCollectors.toImmutableMap());
    return ViewRowFieldNameAndJsonValues.ofMap(map);
}


}