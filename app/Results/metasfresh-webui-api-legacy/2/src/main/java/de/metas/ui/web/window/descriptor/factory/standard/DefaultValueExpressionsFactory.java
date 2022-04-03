package de.metas.ui.web.window.descriptor.factory.standard;
 import java.math.BigDecimal;
import java.util.Optional;
import javax.annotation.Nullable;
import org.adempiere.ad.expression.api.IExpression;
import org.adempiere.ad.expression.api.IExpressionFactory;
import org.adempiere.ad.expression.api.IStringExpression;
import org.adempiere.ad.expression.api.impl.BigDecimalStringExpressionSupport.BigDecimalStringExpression;
import org.adempiere.ad.expression.api.impl.BooleanStringExpressionSupport.BooleanStringExpression;
import org.adempiere.ad.expression.api.impl.DateStringExpressionSupport.DateStringExpression;
import org.adempiere.ad.expression.api.impl.IntegerStringExpressionSupport.IntegerStringExpression;
import org.adempiere.ad.expression.api.impl.SysDateDateExpression;
import org.adempiere.mm.attributes.api.AttributeConstants;
import org.compiere.util.DisplayType;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.sql.AutoSequenceDefaultValueExpression;
import de.metas.ui.web.window.descriptor.sql.SqlDefaultValueExpression;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
public class DefaultValueExpressionsFactory {

 private  Logger logger;

 private  IExpressionFactory expressionFactory;

 private  String HARDCODED_DEFAUL_EXPRESSION_STRING_NULL;

 private  Optional<IExpression<?>> DEFAULT_VALUE_EXPRESSION_Yes;

 private  Optional<IExpression<?>> DEFAULT_VALUE_EXPRESSION_No;

 private  Optional<IExpression<?>> DEFAULT_VALUE_EXPRESSION_Zero_BigDecimal;

 private  Optional<IExpression<?>> DEFAULT_VALUE_EXPRESSION_Zero_Integer;

 private  Optional<IExpression<?>> DEFAULT_VALUE_EXPRESSION_M_AttributeSetInstance_ID;

 private  Optional<IExpression<?>> DEFAULT_VALUE_EXPRESSION_NextLineNo;

 private  Optional<IExpression<?>> DEFAULT_VALUE_AD_Client_ID;

 private  Optional<IExpression<?>> DEFAULT_VALUE_AD_Org_ID;

 private  Optional<IExpression<?>> DEFAULT_VALUE_ContextDate;

 private  Optional<IExpression<?>> DEFAULT_VALUE_ContextUser_ID;

@Nullable
 private  String _tableName;

 private  boolean _isDetailTab;


public Optional<IExpression<?>> extractDefaultValueExpression(String defaultValueStr,String columnName,DocumentFieldWidgetType widgetType,Class<?> fieldValueClass,boolean isMandatory,boolean allowUsingAutoSequence){
    final boolean isDetailTab = isDetailTab();
    // 
    // Case: "Line" field in included tabs
    if (WindowConstants.FIELDNAME_Line.equals(columnName) && // only on included tabs
    isDetailTab) {
        return DEFAULT_VALUE_EXPRESSION_NextLineNo;
    }
    // 
    // If there is no default value expression, use some defaults
    if (Check.isEmpty(defaultValueStr)) {
        if (WindowConstants.FIELDNAME_AD_Client_ID.equals(columnName)) {
            return DEFAULT_VALUE_AD_Client_ID;
        } else if (WindowConstants.FIELDNAME_AD_Org_ID.equals(columnName)) {
            return DEFAULT_VALUE_AD_Org_ID;
        } else if (WindowConstants.FIELDNAME_Created.equals(columnName) || WindowConstants.FIELDNAME_Updated.equals(columnName)) {
            return DEFAULT_VALUE_ContextDate;
        } else if (WindowConstants.FIELDNAME_CreatedBy.equals(columnName) || WindowConstants.FIELDNAME_UpdatedBy.equals(columnName)) {
            return DEFAULT_VALUE_ContextUser_ID;
        } else // 
        if (WindowConstants.FIELDNAME_Value.equals(columnName) && getTableName() != null && allowUsingAutoSequence) {
            return Optional.of(AutoSequenceDefaultValueExpression.of(getTableName()));
        } else if (Boolean.class.equals(fieldValueClass)) {
            if (WindowConstants.FIELDNAME_IsActive.equals(columnName)) {
                return DEFAULT_VALUE_EXPRESSION_Yes;
            } else {
                return DEFAULT_VALUE_EXPRESSION_No;
            }
        } else if (Integer.class.equals(fieldValueClass)) {
            if (isMandatory) {
                return DEFAULT_VALUE_EXPRESSION_Zero_Integer;
            }
        } else if (BigDecimal.class.equals(fieldValueClass)) {
            if (isMandatory) {
                // e.g. C_OrderLine.QtyReserved
                return DEFAULT_VALUE_EXPRESSION_Zero_BigDecimal;
            }
        } else if (widgetType == DocumentFieldWidgetType.ProductAttributes) {
            return DEFAULT_VALUE_EXPRESSION_M_AttributeSetInstance_ID;
        }
        return Optional.empty();
    } else // Explicit NULL
    if ("null".equalsIgnoreCase(defaultValueStr.trim())) {
        return Optional.of(IStringExpression.NULL);
    } else // If it's a SQL expression => compile it as SQL expression
    if (defaultValueStr.startsWith("@SQL=")) {
        final String sqlTemplate = defaultValueStr.substring(5).trim();
        final IStringExpression sqlTemplateStringExpression = expressionFactory.compile(sqlTemplate, IStringExpression.class);
        return Optional.of(SqlDefaultValueExpression.of(sqlTemplateStringExpression, fieldValueClass));
    } else // Regular default value expression
    {
        return buildExpression(expressionFactory, defaultValueStr, fieldValueClass);
    }
}


@Nullable
public String getTableName(){
    return _tableName;
}


public boolean isDetailTab(){
    return _isDetailTab;
}


public Optional<IExpression<?>> buildExpression(IExpressionFactory expressionFactory,String expressionStr,Class<?> fieldValueClass){
    // Hardcoded: NULL
    if (HARDCODED_DEFAUL_EXPRESSION_STRING_NULL.equalsIgnoreCase(expressionStr)) {
        return Optional.empty();
    }
    // Hardcoded: SysDate
    if (SysDateDateExpression.EXPRESSION_STRING.equals(expressionStr)) {
        return SysDateDateExpression.optionalInstance;
    }
    final IExpression<?> expression;
    if (Integer.class.equals(fieldValueClass)) {
        expression = expressionFactory.compile(expressionStr, IntegerStringExpression.class);
    } else if (BigDecimal.class.equals(fieldValueClass)) {
        expression = expressionFactory.compile(expressionStr, BigDecimalStringExpression.class);
    } else if (IntegerLookupValue.class.equals(fieldValueClass)) {
        expression = expressionFactory.compile(expressionStr, IntegerStringExpression.class);
    } else if (StringLookupValue.class.equals(fieldValueClass)) {
        final String expressionStrNorm = stripDefaultValueQuotes(expressionStr);
        expression = expressionFactory.compile(expressionStrNorm, IStringExpression.class);
    } else if (TimeUtil.isDateOrTimeClass(fieldValueClass)) {
        expression = expressionFactory.compile(expressionStr, DateStringExpression.class);
    } else if (Boolean.class.equals(fieldValueClass)) {
        final String expressionStrNorm = stripDefaultValueQuotes(expressionStr);
        expression = expressionFactory.compile(expressionStrNorm, BooleanStringExpression.class);
    } else // 
    // Fallback
    {
        expression = expressionFactory.compile(expressionStr, IStringExpression.class);
    }
    if (expression.isNullExpression()) {
        return Optional.empty();
    }
    return Optional.of(expression);
}


public DefaultValueExpressionsFactory newInstance(String tableName,boolean isDetailTab){
    return new DefaultValueExpressionsFactory(tableName, isDetailTab);
}


public String stripDefaultValueQuotes(String expressionStr){
    if (expressionStr == null || expressionStr.isEmpty()) {
        return expressionStr;
    }
    if (expressionStr.startsWith("'") && expressionStr.endsWith("'")) {
        final String expressionStrNorm = expressionStr.substring(1, expressionStr.length() - 1);
        logger.warn("Normalized string expression: [{}] -> [{}]", expressionStr, expressionStrNorm);
    }
    return expressionStr;
}


}