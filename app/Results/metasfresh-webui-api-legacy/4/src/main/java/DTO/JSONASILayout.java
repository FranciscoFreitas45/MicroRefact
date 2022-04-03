package DTO;
 import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.pattribute.ASILayout;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutElement;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
public class JSONASILayout implements Serializable{

 private  String id;

 private  String caption;

 private  String description;

 private  List<JSONDocumentLayoutElement> elements;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public List<JSONDocumentLayoutElement> getElements(){
    return elements;
}


public String getCaption(){
    return caption;
}


public String getDescription(){
    return description;
}


public JSONASILayout of(ASILayout layout,JSONDocumentLayoutOptions options){
    return new JSONASILayout(layout, options);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("layout",layout);
.queryParam("options",options);
JSONASILayout aux = restTemplate.getForObject(builder.toUriString(),JSONASILayout.class);
return aux;
}


}