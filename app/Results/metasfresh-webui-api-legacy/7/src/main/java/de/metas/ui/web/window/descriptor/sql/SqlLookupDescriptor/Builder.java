package de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
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
@ToString(of = { "sqlTableName", "ctxTableName", "ctxColumnName", "widgetType" })
public class Builder {

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


public LookupSource getLookupSourceType(){
    return DescriptorsFactoryHelper.extractLookupSource(displayType, AD_Reference_Value_ID);
}


public IStringExpression buildSqlWhere(MLookupInfo lookupInfo,LookupScope scope,IValidationRule validationRuleEffective){
    final String tableName = lookupInfo.getTableName();
    final String lookup_SqlWhere = lookupInfo.getWhereClauseSqlPart();
    final TranslatableParameterizedString displayColumnSql = lookupInfo.getDisplayColumnSql();
    final CompositeStringExpression.Builder sqlWhereFinal = IStringExpression.composer();
    // Static lookup's WHERE
    if (!Check.isEmpty(lookup_SqlWhere, true)) {
        sqlWhereFinal.append(" /* lookup where clause */ ").append("(").append(lookup_SqlWhere).append(")");
    }
    // Validation Rule's WHERE
    final IStringExpression validationRuleWhereClause = buildSqlWhereClauseFromValidationRule(validationRuleEffective, scope);
    if (!validationRuleWhereClause.isNullExpression()) {
        sqlWhereFinal.appendIfNotEmpty("\n AND ");
        sqlWhereFinal.append(" /* validation rule */ ").append("(\n").append(validationRuleWhereClause).append("\n)\n");
    }
    // Filter's WHERE
    sqlWhereFinal.appendIfNotEmpty("\n AND ");
    sqlWhereFinal.append(" /* filter */ ").append(DBConstants.FUNCNAME_unaccent_string).append("(").append(displayColumnSql).append(", 1)").append(" ILIKE ").append(DBConstants.FUNCNAME_unaccent_string).append("(").append(LookupDataSourceContext.PARAM_FilterSql).append(", 1)");
    // IsActive WHERE
    sqlWhereFinal.appendIfNotEmpty("\n AND ");
    sqlWhereFinal.append(" /* active */ ('").append(SqlForFetchingLookupById.SQL_PARAM_ShowInactive).append("'='Y' OR ").append(tableName).append(".IsActive='Y')");
    return sqlWhereFinal.build();
}


public void setSqlExpressions_PAttribute(){
    final String tableName = I_M_AttributeSetInstance.Table_Name;
    final String keyColumnNameFQ = tableName + "." + I_M_AttributeSetInstance.COLUMNNAME_M_AttributeSetInstance_ID;
    final String displayColumnSql = tableName + "." + I_M_AttributeSetInstance.COLUMNNAME_Description;
    final CompositeStringExpression.Builder sqlSelectFrom = IStringExpression.composer().append("SELECT ").append(" ").append(// Key
    keyColumnNameFQ).append(// Value
    ", NULL").append(",").append(// DisplayName
    displayColumnSql).append(// IsActive
    ", M_AttributeSetInstance.IsActive").append(// EntityType
    ", NULL").append(" FROM ").append(tableName);
    // 
    // WHERE
    final CompositeStringExpression.Builder sqlWhereFinal = IStringExpression.composer();
    {
        // Validation Rule's WHERE
        final IStringExpression validationRuleWhereClause = buildSqlWhereClauseFromValidationRule(validationRuleEffective, scope);
        if (!validationRuleWhereClause.isNullExpression()) {
            sqlWhereFinal.appendIfNotEmpty("\n AND ");
            sqlWhereFinal.append(" /* validation rule */ ").append("(").append(validationRuleWhereClause).append(")");
        }
        // Filter's WHERE
        sqlWhereFinal.appendIfNotEmpty("\n AND ");
        sqlWhereFinal.append(" /* filter */ ").append("(").append(displayColumnSql).append(") ILIKE ").append(LookupDataSourceContext.PARAM_FilterSql);
    }
    // 
    // ORDER BY
    String lookup_SqlOrderBy = I_M_AttributeSetInstance.COLUMNNAME_Description;
    if (Check.isEmpty(lookup_SqlOrderBy, true)) {
        lookup_SqlOrderBy = String.valueOf(MLookupFactory.COLUMNINDEX_DisplayName);
    }
    // 
    // Assemble the SQLs
    final SqlForFetchingLookups sqlForFetching = SqlForFetchingLookups.builder().sql(IStringExpression.composer().append(// SELECT ... FROM ...
    sqlSelectFrom).append("\n WHERE \n").append(// WHERE
    sqlWhereFinal).append("\n ORDER BY ").append(// ORDER BY
    lookup_SqlOrderBy).append("\n OFFSET ").append(SqlForFetchingLookups.PARAM_Offset.toStringWithMarkers()).append("\n LIMIT ").append(// LIMIT
    SqlForFetchingLookups.PARAM_Limit.toStringWithMarkers()).wrap(// security
    AccessSqlStringExpression.wrapper(tableName, IUserRolePermissions.SQL_FULLYQUALIFIED, getRequiredAccess(tableName))).build().caching()).build();
    final SqlForFetchingLookupById sqlForFetchingLookupById = SqlForFetchingLookupById.builder().sql(IStringExpression.composer().append("SELECT ").append("ARRAY[").append(displayColumnSql).append(", NULL]").append("\n FROM ").append(// FROM
    tableName).append("\n WHERE ").append(keyColumnNameFQ).append("=").append(SqlForFetchingLookupById.SQL_PARAM_KeyId).build().caching()).build();
    // 
    // Set the SQLs
    {
        sqlTableName = tableName;
        sqlForFetchingExpression = sqlForFetching;
        sqlForFetchingLookupByIdExpression = sqlForFetchingLookupById;
    }
}


public LookupDescriptor buildForDefaultScope(){
    return buildProvider().provide().orElseThrow(() -> new AdempiereException("No lookup for " + this));
}


public Builder setAD_Val_Rule_ID(int AD_Val_Rule_ID){
    this.AD_Val_Rule_ID = AD_Val_Rule_ID;
    return this;
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


public Builder addValidationRules(Collection<IValidationRule> validationRules){
    this.validationRules.addAll(validationRules);
    return this;
}


public SqlForFetchingLookupById buildSqlForFetchingById(MLookupInfo lookupInfo){
    final IStringExpression displayColumnSQL = TranslatableParameterizedStringExpression.of(lookupInfo.getDisplayColumnSql());
    final IStringExpression descriptionColumnSqlOrNull = TranslatableParameterizedStringExpression.of(lookupInfo.getDescriptionColumnSQL());
    final IStringExpression descriptionColumnSQL;
    if (descriptionColumnSqlOrNull == null || descriptionColumnSqlOrNull.isNullExpression()) {
        descriptionColumnSQL = ConstantStringExpression.of("NULL");
    } else {
        descriptionColumnSQL = descriptionColumnSqlOrNull;
    }
    final IStringExpression validationMsgColumnSqlOrNull = TranslatableParameterizedStringExpression.of(lookupInfo.getValidationMsgColumnSQL());
    final IStringExpression validationMsgColumnSQL;
    if (validationMsgColumnSqlOrNull == null || validationMsgColumnSqlOrNull.isNullExpression()) {
        validationMsgColumnSQL = ConstantStringExpression.of("NULL");
    } else {
        validationMsgColumnSQL = validationMsgColumnSqlOrNull;
    }
    final IStringExpression fromSqlPart = TranslatableParameterizedStringExpression.of(lookupInfo.getFromSqlPart());
    final String keyColumnFQ = lookupInfo.getKeyColumnFQ();
    final int displayType = lookupInfo.getDisplayType();
    // assuming this is constant!
    final String whereClauseSqlPart = lookupInfo.getWhereClauseSqlPart();
    final CompositeStringExpression.Builder sqlBuilder = IStringExpression.composer().append("SELECT ").append("\n ARRAY[").append(displayColumnSQL).append(", ").append(descriptionColumnSQL).append(",").append(lookupInfo.getActiveColumnSQL()).append(", ").append(validationMsgColumnSQL).append("]").append("\n FROM ").append(fromSqlPart).append("\n WHERE ").append(keyColumnFQ).append("=").append(SqlForFetchingLookupById.SQL_PARAM_KeyId).append(" ");
    final boolean listOrButton = DisplayType.List == displayType || DisplayType.Button == displayType;
    if (listOrButton) {
        // FIXME: make it better: this is actually adding the AD_Ref_List.AD_Reference_ID=....
        sqlBuilder.append(" AND " + whereClauseSqlPart);
    }
    final ICachedStringExpression sql = sqlBuilder.build().caching();
    return SqlForFetchingLookupById.builder().sql(sql).build();
}


public Builder setAD_Reference_Value_ID(int AD_Reference_Value_ID){
    this.AD_Reference_Value_ID = AD_Reference_Value_ID;
    return this;
}


public IValidationRule extractValidationRule(MLookupInfo lookupInfo){
    final CompositeValidationRule.Builder validationRuleBuilder = CompositeValidationRule.builder();
    validationRuleBuilder.add(lookupInfo.getValidationRule());
    // 
    // Case: DocAction button => inject the DocActionValidationRule
    // FIXME: hardcoded
    if (displayType == DisplayType.Button && WindowConstants.FIELDNAME_DocAction.equals(ctxColumnName)) {
        validationRuleBuilder.add(DocActionValidationRule.instance);
    }
    // Additional validation rules registered
    validationRules.forEach(validationRuleBuilder::add);
    return validationRuleBuilder.build();
}


public Builder setCtxColumnName(String columnName){
    this.ctxColumnName = columnName;
    return this;
}


public LookupDescriptorProvider buildProvider(String sqlTableName,String sqlColumnName,DocumentFieldWidgetType widgetType,int displayType,int AD_Reference_Value_ID,int AD_Val_Rule_ID,List<IValidationRule> additionalValidationRules){
    if (widgetType == DocumentFieldWidgetType.ProcessButton) {
        return LookupDescriptorProviders.NULL;
    } else if (DisplayType.isAnyLookup(displayType) || DisplayType.Button == displayType && AD_Reference_Value_ID > 0) {
        return LookupDescriptorProviders.fromMemoizingFunction(scope -> SqlLookupDescriptor.builder().setCtxTableName(sqlTableName).setCtxColumnName(sqlColumnName).setDisplayType(displayType).setAD_Reference_Value_ID(AD_Reference_Value_ID).setAD_Val_Rule_ID(AD_Val_Rule_ID).setScope(scope).addValidationRules(additionalValidationRules).build());
    } else {
        return LookupDescriptorProviders.NULL;
    }
}


public boolean isHighVolume(){
    return DisplayType.TableDir != displayType && DisplayType.Table != displayType && DisplayType.List != displayType && DisplayType.Button != displayType;
}


public Optional<WindowId> getZoomIntoWindowId(){
    return Optional.ofNullable(WindowId.ofNullable(zoomIntoAdWindowId));
}


public SqlForFetchingLookups buildSqlForFetching(MLookupInfo lookupInfo,IStringExpression sqlWhere,String sqlOrderBy){
    final String tableName = lookupInfo.getTableName();
    return SqlForFetchingLookups.builder().sql(IStringExpression.composer().append(// SELECT .. FROM ...
    lookupInfo.getSelectSqlPart()).append("\n WHERE \n").append(// WHERE
    sqlWhere).append("\n ORDER BY ").append(// ORDER BY
    sqlOrderBy).append("\n OFFSET ").append(// OFFSET
    SqlForFetchingLookups.PARAM_Offset).append("\n LIMIT ").append(// LIMIT
    SqlForFetchingLookups.PARAM_Limit).wrapIfTrue(!lookupInfo.isSecurityDisabled(), // security
    AccessSqlStringExpression.wrapper(tableName, IUserRolePermissions.SQL_FULLYQUALIFIED, getRequiredAccess(tableName))).build().caching()).build();
}


public IStringExpression buildSqlWhereClauseFromValidationRule(IValidationRule validationRule,LookupScope scope){
    final IStringExpression validationRuleWhereClause = validationRule.getPrefilterWhereClause();
    if (validationRuleWhereClause.isNullExpression()) {
        return IStringExpression.NULL;
    }
    if (scope == LookupScope.DocumentFilter && !validationRuleWhereClause.getParameters().isEmpty()) {
        // don't add the validation rule if it has parameters
        return IStringExpression.NULL;
    }
    return validationRuleWhereClause;
}


public Builder setDisplayType(int displayType){
    this.displayType = displayType;
    return this;
}


public Builder setReadOnlyAccess(){
    this.requiredAccess = Access.READ;
    return this;
}


public Builder setCtxTableName(String tableName){
    this.ctxTableName = tableName;
    return this;
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


public Builder addValidationRule(IValidationRule validationRule){
    validationRules.add(validationRule);
    return this;
}


public SqlLookupDescriptor build(){
    final boolean IsParent = false;
    if (displayType == DisplayType.PAttribute && AD_Reference_Value_ID <= 0) {
        numericKey = true;
        validationRuleEffective = NullValidationRule.instance;
        dependsOnFieldNames = ImmutableSet.of();
        setSqlExpressions_PAttribute();
    } else {
        final MLookupInfo lookupInfo = MLookupFactory.getLookupInfo(WINDOWNO_Dummy, displayType, ctxTableName, ctxColumnName, AD_Reference_Value_ID, IsParent, AD_Val_Rule_ID);
        numericKey = lookupInfo.isNumericKey();
        validationRuleEffective = extractValidationRule(lookupInfo);
        dependsOnFieldNames = ImmutableSet.<String>builder().addAll(validationRuleEffective.getPrefilterWhereClause().getParameterNames()).addAll(validationRuleEffective.getPostQueryFilter().getParameters()).build();
        setSqlExpressions(lookupInfo);
    }
    return new SqlLookupDescriptor(this);
}


public Builder setWidgetType(DocumentFieldWidgetType widgetType){
    this.widgetType = widgetType;
    return this;
}


public void setSqlExpressions(MLookupInfo lookupInfo){
    // 
    // WHERE
    final IStringExpression sqlWhereFinal = buildSqlWhere(lookupInfo, scope, validationRuleEffective);
    // 
    // ORDER BY
    String lookup_SqlOrderBy = lookupInfo.getOrderBySqlPart();
    if (Check.isEmpty(lookup_SqlOrderBy, true)) {
        lookup_SqlOrderBy = String.valueOf(MLookupFactory.COLUMNINDEX_DisplayName);
    }
    // 
    // Set the SQLs
    {
        sqlTableName = lookupInfo.getTableName();
        zoomIntoAdWindowId = lookupInfo.getZoomAD_Window_ID_Override();
        sqlForFetchingExpression = buildSqlForFetching(lookupInfo, sqlWhereFinal, lookup_SqlOrderBy);
        sqlForFetchingLookupByIdExpression = buildSqlForFetchingById(lookupInfo);
        if (lookupInfo.isQueryHasEntityType()) {
            entityTypeIndex = MLookupFactory.COLUMNINDEX_EntityType;
        }
    }
}


public Builder setScope(LookupScope scope){
    Check.assumeNotNull(scope, "Parameter scope is not null");
    this.scope = scope;
    return this;
}


public Set<String> getDependsOnTableNames(){
    return validationRuleEffective.getDependsOnTableNames();
}


}