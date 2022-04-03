package de.metas.ui.web.view.descriptor;
 import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.slf4j.Logger;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.metas.cache.CCache;
import de.metas.logging.LogManager;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterDecorator;
import de.metas.ui.web.view.DefaultViewInvalidationAdvisor;
import de.metas.ui.web.view.IViewInvalidationAdvisor;
import de.metas.ui.web.view.SqlViewCustomizer;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.ViewRowCustomizer;
import de.metas.ui.web.view.descriptor.SqlViewRowFieldBinding.SqlViewRowFieldLoader;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import de.metas.ui.web.window.descriptor.sql.DocumentFieldValueLoader;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentEntityDataBindingDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlDocumentFieldDataBindingDescriptor;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
public class SqlViewBindingFactory {

 private  Logger logger;

 private  DocumentDescriptorFactory documentDescriptorFactory;

 private  ImmutableMap<WindowId,SqlDocumentFilterConverterDecorator> windowId2SqlDocumentFilterConverterDecorator;

 private  ImmutableMap<WindowId,IViewInvalidationAdvisor> viewInvalidationAdvisorsByWindowId;

 private  SqlViewCustomizerMap viewCustomizers;

 private  CCache<SqlViewBindingKey,SqlViewBinding> cache;

@NonNull
 private  DocumentFieldValueLoader fieldValueLoader;

 private  boolean isDisplayColumnAvailable;

@NonNull
 private  WindowId windowId;

@Nullable
 private  Characteristic requiredFieldCharacteristic;

@Nullable
 private  ViewProfileId profileId;


public SqlViewBinding.Builder prepareSqlViewBinding(SqlDocumentEntityDataBindingDescriptor entityBinding){
    return SqlViewBinding.builder().tableName(entityBinding.getTableName()).tableAlias(entityBinding.getTableAlias()).sqlWhereClause(entityBinding.getSqlWhereClause()).defaultOrderBys(entityBinding.getDefaultOrderBys());
}


public IViewInvalidationAdvisor getViewInvalidationAdvisor(WindowId windowId){
    return viewInvalidationAdvisorsByWindowId.getOrDefault(windowId, DefaultViewInvalidationAdvisor.instance);
}


public ImmutableMap<WindowId,SqlDocumentFilterConverterDecorator> makeDecoratorsMapAndHandleDuplicates(Collection<SqlDocumentFilterConverterDecorator> providers){
    try {
        return Maps.uniqueIndex(providers, SqlDocumentFilterConverterDecorator::getWindowId);
    } catch (IllegalArgumentException e) {
        final String message = "The given collection of SqlDocumentFilterConverterDecoratorProvider implementors contains more than one element with the same window-id";
        throw new AdempiereException(message, e).setParameter("sqlDocumentFilterConverterDecoratorProviders", providers).appendParametersToMessage();
    }
}


public SqlViewBinding getViewBinding(WindowId windowId,Characteristic requiredFieldCharacteristic,ViewProfileId profileId){
    final SqlViewBindingKey key = new SqlViewBindingKey(windowId, requiredFieldCharacteristic, profileId);
    return cache.getOrLoad(key, this::createViewBinding);
}


public SqlViewBinding.Builder createBuilderForEntityBindingAndFieldNames(SqlDocumentEntityDataBindingDescriptor entityBinding,Set<String> displayFieldNames){
    final SqlViewBinding.Builder builder = prepareSqlViewBinding(entityBinding);
    entityBinding.getFields().stream().map(documentField -> createViewFieldBinding(documentField, displayFieldNames)).forEach(builder::field);
    builder.displayFieldNames(displayFieldNames);
    return builder;
}


@Override
public Object retrieveValue(ResultSet rs,String adLanguage){
    return fieldValueLoader.retrieveFieldValue(rs, isDisplayColumnAvailable, adLanguage, (LookupDescriptor) null);
}


public SqlViewRowFieldBinding createViewFieldBinding(SqlDocumentFieldDataBindingDescriptor documentField,Collection<String> availableDisplayColumnNames){
    final String fieldName = documentField.getFieldName();
    final boolean isDisplayColumnAvailable = documentField.getSqlSelectDisplayValue() != null && availableDisplayColumnNames.contains(fieldName);
    return SqlViewRowFieldBinding.builder().fieldName(fieldName).columnName(documentField.getColumnName()).keyColumn(documentField.isKeyColumn()).widgetType(documentField.getWidgetType()).virtualColumn(documentField.isVirtualColumn()).sqlValueClass(documentField.getSqlValueClass()).sqlSelectValue(documentField.getSqlSelectValue()).sqlSelectDisplayValue(isDisplayColumnAvailable ? documentField.getSqlSelectDisplayValue() : null).sqlOrderBy(documentField.getSqlOrderBy()).fieldLoader(new DocumentFieldValueLoaderAsSqlViewRowFieldLoader(documentField.getDocumentFieldValueLoader(), isDisplayColumnAvailable)).build();
}


public ImmutableMap<WindowId,IViewInvalidationAdvisor> makeViewInvalidationAdvisorsMap(List<IViewInvalidationAdvisor> viewInvalidationAdvisors){
    try {
        return Maps.uniqueIndex(viewInvalidationAdvisors, advisor -> {
            final WindowId windowId = advisor.getWindowId();
            Check.assumeNotNull(windowId, "windowId shall not be null for {}", advisor);
            return windowId;
        });
    } catch (IllegalArgumentException e) {
        final String message = "The given collection of " + IViewInvalidationAdvisor.class + " implementors contains more than one element with the same window-id";
        throw new AdempiereException(message, e).setParameter("viewInvalidationAdvisors", viewInvalidationAdvisors).appendParametersToMessage();
    }
}


public SqlViewBinding createViewBinding(SqlViewBindingKey key){
    final WindowId windowId = key.getWindowId();
    final DocumentEntityDescriptor entityDescriptor = documentDescriptorFactory.getDocumentEntityDescriptor(windowId);
    final Set<String> displayFieldNames = entityDescriptor.getFieldNamesWithCharacteristic(key.getRequiredFieldCharacteristic());
    final SqlDocumentEntityDataBindingDescriptor entityBinding = SqlDocumentEntityDataBindingDescriptor.cast(entityDescriptor.getDataBinding());
    final DocumentFilterDescriptorsProvider filterDescriptors = entityDescriptor.getFilterDescriptors();
    final SqlViewBinding.Builder builder = createBuilderForEntityBindingAndFieldNames(entityBinding, displayFieldNames).filterDescriptors(filterDescriptors).refreshViewOnChangeEvents(entityDescriptor.isRefreshViewOnChangeEvents()).viewInvalidationAdvisor(getViewInvalidationAdvisor(windowId));
    if (windowId2SqlDocumentFilterConverterDecorator.containsKey(windowId)) {
        builder.filterConverterDecorator(windowId2SqlDocumentFilterConverterDecorator.get(windowId));
    }
    final SqlViewCustomizer sqlViewCustomizer = viewCustomizers.getOrNull(windowId, key.getProfileId());
    if (sqlViewCustomizer != null) {
        final ViewRowCustomizer rowCustomizer = sqlViewCustomizer;
        builder.rowCustomizer(rowCustomizer);
        sqlViewCustomizer.customizeSqlViewBinding(builder);
    }
    return builder.build();
}


}