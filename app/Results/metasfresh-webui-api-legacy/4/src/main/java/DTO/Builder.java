package DTO;
 import java.util.ArrayList;
import java.util.List;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
public class Builder {

 public  DocumentId asiDescriptorId;

 private  ITranslatableString caption;

 private  ITranslatableString description;

 private  List<DocumentLayoutElementDescriptor.Builder> elementBuilders;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public Builder setCaption(ITranslatableString caption){
    this.caption = caption;
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCaption"))

.queryParam("caption",caption);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public Builder addElement(DocumentLayoutElementDescriptor.Builder elementBuilder){
    Check.assumeNotNull(elementBuilder, "Parameter elementBuilder is not null");
    elementBuilders.add(elementBuilder);
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addElement"))

.queryParam("elementBuilder",elementBuilder);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public ASILayout build(){
    return new ASILayout(this);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/build"))

ASILayout aux = restTemplate.getForObject(builder.toUriString(),ASILayout.class);
return aux;
}


public String getSqlParentLinkColumnName(){
    return _sqlParentLinkColumnName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSqlParentLinkColumnName"))

String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


public Builder addField(DocumentFieldDataBindingDescriptor field){
    assertNotBuilt();
    final SqlDocumentFieldDataBindingDescriptor sqlField = SqlDocumentFieldDataBindingDescriptor.castOrNull(field);
    _fieldsByFieldName.put(sqlField.getFieldName(), sqlField);
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addField"))

.queryParam("field",field);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public POInfo getPOInfo(){
    return POInfo.getPOInfo(getTableName());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPOInfo"))

POInfo aux = restTemplate.getForObject(builder.toUriString(),POInfo.class);
return aux;
}


public String getTableAlias(){
    return _tableAlias;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTableAlias"))

String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


public String getTableName(){
    return _sqlTableName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTableName"))

String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


}