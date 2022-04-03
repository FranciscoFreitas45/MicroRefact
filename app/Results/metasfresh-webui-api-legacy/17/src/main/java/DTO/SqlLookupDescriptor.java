package DTO;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.annotation.concurrent.Immutable;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.ad.expression.api.ICachedStringExpression;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.TranslatableParameterizedStringExpression;
import org.adempiere.ad.expression.api.impl.CompositeStringExpression;
import org.adempiere.ad.expression.api.impl.ConstantStringExpression;
import org.adempiere.ad.validationRule.INamePairPredicate;
import org.adempiere.ad.validationRule.IValidationRule;
import org.adempiere.ad.validationRule.impl.CompositeValidationRule;
import org.adempiere.ad.validationRule.impl.NullValidationRule;
import org.adempiere.db.DBConstants;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_AD_Client;
import org.compiere.model.I_AD_Org;
import org.compiere.model.I_M_AttributeSetInstance;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MLookupInfo;
import org.compiere.util.DisplayType;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.TranslatableParameterizedString;
import de.metas.security.IUserRolePermissions;
import de.metas.security.impl.AccessSqlStringExpression;
import de.metas.security.permissions.Access;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.LookupDescriptorProvider;
import de.metas.ui.web.window.descriptor.LookupDescriptorProvider.LookupScope;
import de.metas.ui.web.window.descriptor.LookupDescriptorProviders;
import de.metas.ui.web.window.descriptor.factory.standard.DescriptorsFactoryHelper;
import de.metas.ui.web.window.model.lookup.GenericSqlLookupDataSourceFetcher;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFetcher;
import de.metas.ui.web.window.model.sql.DocActionValidationRule;
import de.metas.util.Check;
import lombok.ToString;
public class SqlLookupDescriptor implements ISqlLookupDescriptor{

 private  int WINDOWNO_Dummy;

 private  Optional<String> tableName;

 private  Optional<WindowId> zoomIntoWindowId;

 private  SqlForFetchingLookups sqlForFetchingExpression;

 private  SqlForFetchingLookupById sqlForFetchingLookupByIdExpression;

 private  int entityTypeIndex;

 private  INamePairPredicate postQueryPredicate;

 private  boolean highVolume;

 private  boolean numericKey;

 private  LookupSource lookupSourceType;

 private  ImmutableSet<String> dependsOnFieldNames;

 private  ImmutableSet<String> dependsOnTableNames;

 private  GenericSqlLookupDataSourceFetcher lookupDataSourceFetcher;

 private  String ctxColumnName;

 private  String ctxTableName;

 private  DocumentFieldWidgetType widgetType;

 private  Integer displayType;

 private  int AD_Reference_Value_ID;

 private  int AD_Val_Rule_ID;

 private  LookupScope scope;

 private  Access requiredAccess;

 private  boolean numericKey;

 private  Set<String> dependsOnFieldNames;

 private  List<IValidationRule> validationRules;

 private  IValidationRule validationRuleEffective;

 private  String sqlTableName;

 private  SqlForFetchingLookups sqlForFetchingExpression;

 private  SqlForFetchingLookupById sqlForFetchingLookupByIdExpression;

 private  int entityTypeIndex;

 private  AdWindowId zoomIntoAdWindowId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public LookupSource getLookupSourceType(){
    return DescriptorsFactoryHelper.extractLookupSource(displayType, AD_Reference_Value_ID);
}


public INamePairPredicate getPostQueryPredicate(){
    final INamePairPredicate postQueryPredicate = validationRuleEffective.getPostQueryFilter();
    if (postQueryPredicate == null) {
        return INamePairPredicate.NULL;
    }
    if (scope == LookupScope.DocumentFilter && !postQueryPredicate.getParameters().isEmpty()) {
        return INamePairPredicate.NULL;
    }
    return postQueryPredicate;
}


public SqlForFetchingLookups getSqlForFetchingExpression(){
    return sqlForFetchingExpression;
}


@Override
public Set<String> getDependsOnFieldNames(){
    return dependsOnFieldNames;
}


public int getEntityTypeIndex(){
    return entityTypeIndex;
}


@Override
public LookupDataSourceFetcher getLookupDataSourceFetcher(){
    return lookupDataSourceFetcher;
}


public Optional<WindowId> getZoomIntoWindowId(){
    return Optional.ofNullable(WindowId.ofNullable(zoomIntoAdWindowId));
}


@Override
public Optional<String> getTableName(){
    return tableName;
}


public Access getRequiredAccess(String tableName){
    if (requiredAccess != null) {
        return requiredAccess;
    }
    // AD_Client_ID/AD_Org_ID (security fields): shall display only those entries on which current user has read-write access
    if (I_AD_Client.Table_Name.equals(tableName) || I_AD_Org.Table_Name.equals(tableName)) {
        return Access.WRITE;
    }
    // Default: all entries on which current user has at least readonly access
    return Access.READ;
}


@Override
public SqlForFetchingLookupById getSqlForFetchingLookupByIdExpression(){
    return sqlForFetchingLookupByIdExpression;
}


public Set<String> getDependsOnTableNames(){
    return validationRuleEffective.getDependsOnTableNames();
}


public SqlLookupDescriptor cast(LookupDescriptor descriptor){
    return (SqlLookupDescriptor) descriptor;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/cast"))

.queryParam("descriptor",descriptor);
SqlLookupDescriptor aux = restTemplate.getForObject(builder.toUriString(),SqlLookupDescriptor.class);
return aux;
}


public Builder builder(){
    return new Builder();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))

Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public Builder setCtxTableName(String tableName){
    this.ctxTableName = tableName;
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCtxTableName"))

.queryParam("tableName",tableName);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public Builder setCtxColumnName(String columnName){
    this.ctxColumnName = columnName;
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCtxColumnName"))

.queryParam("columnName",columnName);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


}