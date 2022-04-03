package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DefaultValueExpressionsFactoryImpl implements DefaultValueExpressionsFactory{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public DefaultValueExpressionsFactory newInstance(String tableName,boolean isDetailTab){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/newInstance"))
    .queryParam("tableName",tableName)
    .queryParam("isDetailTab",isDetailTab);
  DefaultValueExpressionsFactory aux = restTemplate.getForObject(builder.toUriString(), DefaultValueExpressionsFactory.class);

 return aux;
}


public Optional<IExpression<?>> extractDefaultValueExpression(String defaultValueStr,String columnName,DocumentFieldWidgetType widgetType,Class<?> fieldValueClass,boolean isMandatory,boolean allowUsingAutoSequence){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/extractDefaultValueExpression"))
    .queryParam("defaultValueStr",defaultValueStr)
    .queryParam("columnName",columnName)
    .queryParam("widgetType",widgetType)
    .queryParam("fieldValueClass",fieldValueClass)
    .queryParam("isMandatory",isMandatory)
    .queryParam("allowUsingAutoSequence",allowUsingAutoSequence);
  Optional<IExpression<?>> aux = restTemplate.getForObject(builder.toUriString(), Optional<IExpression<?>>.class);

 return aux;
}


}