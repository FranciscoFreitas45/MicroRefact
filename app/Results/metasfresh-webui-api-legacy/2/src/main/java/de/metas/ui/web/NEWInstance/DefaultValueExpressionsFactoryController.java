package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DefaultValueExpressionsFactoryController {

 private DefaultValueExpressionsFactory defaultvalueexpressionsfactory;


@GetMapping
("/newInstance")
public DefaultValueExpressionsFactory newInstance(@RequestParam(name = "tableName") String tableName,@RequestParam(name = "isDetailTab") boolean isDetailTab){
  return defaultvalueexpressionsfactory.newInstance(tableName,isDetailTab);
}


@GetMapping
("/extractDefaultValueExpression")
public Optional<IExpression<?>> extractDefaultValueExpression(@RequestParam(name = "defaultValueStr") String defaultValueStr,@RequestParam(name = "columnName") String columnName,@RequestParam(name = "widgetType") DocumentFieldWidgetType widgetType,@RequestParam(name = "fieldValueClass") Class<?> fieldValueClass,@RequestParam(name = "isMandatory") boolean isMandatory,@RequestParam(name = "allowUsingAutoSequence") boolean allowUsingAutoSequence){
  return defaultvalueexpressionsfactory.extractDefaultValueExpression(defaultValueStr,columnName,widgetType,fieldValueClass,isMandatory,allowUsingAutoSequence);
}


}