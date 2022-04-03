package Interface;
public interface DefaultValueExpressionsFactory {

   public DefaultValueExpressionsFactory newInstance(String tableName,boolean isDetailTab);
   public Optional<IExpression<?>> extractDefaultValueExpression(String defaultValueStr,String columnName,DocumentFieldWidgetType widgetType,Class<?> fieldValueClass,boolean isMandatory,boolean allowUsingAutoSequence);
}