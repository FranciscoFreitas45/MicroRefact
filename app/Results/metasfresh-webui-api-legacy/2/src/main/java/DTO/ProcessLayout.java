package DTO;
 import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.process.BarcodeScannerType;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor;
import de.metas.util.Check;
public class ProcessLayout {

 private  ProcessId processId;

 private  PanelLayoutType layoutType;

 private  BarcodeScannerType barcodeScannerType;

 private  ITranslatableString caption;

 private  ITranslatableString description;

 private  List<DocumentLayoutElementDescriptor> elements;

 private  ProcessId processId;

 private  PanelLayoutType layoutType;

 private  ITranslatableString caption;

 private  ITranslatableString description;

 private  List<DocumentLayoutElementDescriptor> elements;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public PanelLayoutType getLayoutType(){
    return layoutType;
}


public String getCaption(String adLanguage){
    return caption.translate(adLanguage);
}


public ITranslatableString getDescription(){
    return description;
}


public BarcodeScannerType getBarcodeScannerType(){
    return barcodeScannerType;
}


public List<DocumentLayoutElementDescriptor> getElements(){
    return elements;
}


public Builder builder(){
    return new Builder();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))

Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


}