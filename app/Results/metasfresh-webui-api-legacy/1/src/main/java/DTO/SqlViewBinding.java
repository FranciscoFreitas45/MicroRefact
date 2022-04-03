package DTO;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.impl.ConstantStringExpression;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.fullTextSearch.FullTextSearchSqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterDecorator;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverters;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConvertersList;
import de.metas.ui.web.document.geo_location.GeoLocationFilterConverter;
import de.metas.ui.web.view.DefaultViewInvalidationAdvisor;
import de.metas.ui.web.view.IViewInvalidationAdvisor;
import de.metas.ui.web.view.ViewEvaluationCtx;
import de.metas.ui.web.view.ViewRowCustomizer;
import de.metas.ui.web.view.descriptor.SqlViewRowFieldBinding.SqlViewRowFieldLoader;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.sql.SqlEntityBinding;
import de.metas.ui.web.window.descriptor.sql.SqlSelectValue;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
public class SqlViewBinding implements SqlEntityBinding{

 private  String _tableName;

 private  String _tableAlias;

 private  ImmutableMap<String,SqlViewRowFieldBinding> _fieldsByFieldName;

 private  ImmutableMap<String,DocumentFieldWidgetType> widgetTypesByFieldName;

 private  SqlViewKeyColumnNamesMap keyColumnNamesMap;

 private  SqlViewSelectData sqlViewSelect;

 private  IStringExpression sqlWhereClause;

 private  List<SqlViewRowFieldLoader> rowFieldLoaders;

 private  ViewRowCustomizer rowCustomizer;

 private  DocumentQueryOrderByList defaultOrderBys;

 private  OrderByFieldNameAliasMap orderByFieldNameAliasMap;

 private  DocumentFilterDescriptorsProvider filterDescriptors;

 private  SqlDocumentFilterConvertersList filterConverters;

 private  boolean refreshViewOnChangeEvents;

 private  SqlViewRowIdsConverter rowIdsConverter;

 private  SqlViewGroupingBinding groupingBinding;

 private  Optional<SqlDocumentFilterConverterDecorator> filterConverterDecorator;

 private  IViewInvalidationAdvisor viewInvalidationAdvisor;

 private  ImmutableMap<String,ImmutableList<String>> orderByAliasFieldNames;

 private  String _sqlTableName;

 private  String _tableAlias;

 private  IStringExpression sqlWhereClause;

 private  Collection<String> displayFieldNames;

 private  Map<String,SqlViewRowFieldBinding> _fieldsByFieldName;

 private  ViewRowCustomizer rowCustomizer;

 private  ArrayList<DocumentQueryOrderBy> defaultOrderBys;

 private  OrderByFieldNameAliasMap.OrderByFieldNameAliasMapBuilder orderByFieldNameAliasMap;

 private  DocumentFilterDescriptorsProvider filterDescriptors;

 private  SqlDocumentFilterConvertersList.Builder filterConverters;

 private  boolean refreshViewOnChangeEvents;

 private  SqlViewRowIdsConverter rowIdsConverter;

 private  SqlViewGroupingBinding groupingBinding;

 private  SqlDocumentFilterConverterDecorator sqlDocumentFilterConverterDecorator;

 private  IViewInvalidationAdvisor viewInvalidationAdvisor;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public Map<String,SqlViewRowFieldBinding> getFieldsByFieldName(){
    return _fieldsByFieldName;
}


public List<SqlViewRowFieldLoader> getRowFieldLoaders(){
    return rowFieldLoaders;
}


@Override
public SqlViewRowFieldBinding getFieldByFieldName(String fieldName){
    final SqlViewRowFieldBinding field = _fieldsByFieldName.get(fieldName);
    if (field == null) {
        throw new IllegalArgumentException("No field found for '" + fieldName + "' in " + this);
    }
    return field;
}


@Override
public SqlDocumentFilterConvertersList getFilterConverters(){
    return filterConverters;
}


@Override
public DocumentFilterDescriptorsProvider getFilterDescriptors(){
    return getViewFilterDescriptors();
}


public String getTableName(){
    Check.assumeNotEmpty(_sqlTableName, "sqlTableName is not empty");
    return _sqlTableName;
}


public SqlViewSelectData getSqlViewSelect(){
    return sqlViewSelect;
}


public DocumentFilterDescriptorsProvider getViewFilterDescriptors(){
    return filterDescriptors;
}


public Map<String,DocumentFieldWidgetType> getWidgetTypesByFieldName(){
    return widgetTypesByFieldName;
}


public SqlViewKeyColumnNamesMap getSqlViewKeyColumnNamesMap(){
    final ImmutableList<SqlViewRowFieldBinding> keyFields = getKeyFields();
    if (keyFields.isEmpty()) {
        throw new AdempiereException("No key columns defined for " + getTableName());
    }
    return SqlViewKeyColumnNamesMap.ofKeyFields(keyFields);
}


public Collection<SqlViewRowFieldBinding> getFields(){
    return _fieldsByFieldName.values();
}


public SqlViewRowIdsConverter getRowIdsConverter(){
    if (rowIdsConverter != null) {
        return rowIdsConverter;
    }
    if (groupingBinding != null) {
        return groupingBinding.getRowIdsConverter();
    }
    return SqlViewRowIdsConverters.TO_INT_STRICT;
}


@Override
public Optional<SqlDocumentFilterConverterDecorator> getFilterConverterDecorator(){
    return filterConverterDecorator;
}


public Map<String,String> getSqlOrderBysIndexedByFieldName(ViewEvaluationCtx viewEvalCtx){
    final ImmutableMap.Builder<String, String> sqlOrderBysIndexedByFieldName = ImmutableMap.builder();
    for (final SqlViewRowFieldBinding fieldBinding : getFields()) {
        final String fieldOrderBy = fieldBinding.getSqlOrderBy().toSqlString(viewEvalCtx.toEvaluatee());
        if (Check.isEmpty(fieldOrderBy, true)) {
            continue;
        }
        final String fieldName = fieldBinding.getFieldName();
        sqlOrderBysIndexedByFieldName.put(fieldName, fieldOrderBy);
    }
    return sqlOrderBysIndexedByFieldName.build();
}


public ImmutableList<SqlViewRowFieldBinding> getKeyFields(){
    return getFieldsByFieldName().values().stream().filter(SqlViewRowFieldBinding::isKeyColumn).collect(ImmutableList.toImmutableList());
}


public IStringExpression getSqlWhereClause(){
    return sqlWhereClause;
}


public ViewRowCustomizer getRowCustomizer(){
    return rowCustomizer;
}


public String getTableAlias(){
    if (_tableAlias == null) {
        return getTableName();
    }
    return _tableAlias;
}


public DocumentQueryOrderByList getDefaultOrderBys(){
    return DocumentQueryOrderByList.ofList(defaultOrderBys);
}


public IViewInvalidationAdvisor getViewInvalidationAdvisor(){
    return viewInvalidationAdvisor;
}


public Set<String> getGroupByFieldNames(){
    if (groupingBinding == null) {
        return ImmutableSet.of();
    }
    return groupingBinding.getGroupByFieldNames();
}


@Nullable
public SqlSelectValue getSqlAggregatedColumn(String fieldName){
    if (groupingBinding == null) {
        return null;
    }
    return groupingBinding.getColumnSqlByFieldName(fieldName);
}


public Collection<String> getDisplayFieldNames(){
    if (displayFieldNames == null || displayFieldNames.isEmpty()) {
        throw new IllegalStateException("No display field names configured for " + this);
    }
    return displayFieldNames;
}


public Builder builder(){
    return new Builder();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))

Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public Builder tableName(String sqlTableName){
    _sqlTableName = sqlTableName;
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/tableName"))

.queryParam("sqlTableName",sqlTableName);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


}