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
public class ASILayout {

 private  DocumentId asiDescriptorId;

 private  ITranslatableString caption;

 private  ITranslatableString description;

 private  List<DocumentLayoutElementDescriptor> elements;

 public  DocumentId asiDescriptorId;

 private  ITranslatableString caption;

 private  ITranslatableString description;

 private  List<DocumentLayoutElementDescriptor.Builder> elementBuilders;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public List<DocumentLayoutElementDescriptor> getElements(){
    return elements;
}


public DocumentId getASIDescriptorId(){
    return asiDescriptorId;
}


public String getCaption(String adLanguage){
    return caption.translate(adLanguage);
}


public String getDescription(String adLanguage){
    return description.translate(adLanguage);
}


public Builder builder(){
    return new Builder();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))

Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


}