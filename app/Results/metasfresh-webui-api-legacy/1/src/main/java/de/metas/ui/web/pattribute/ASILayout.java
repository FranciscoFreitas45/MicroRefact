package de.metas.ui.web.pattribute;
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


public ASILayout build(){
    return new ASILayout(this);
}


public Builder setASIDescriptorId(DocumentId asiDescriptorId){
    this.asiDescriptorId = asiDescriptorId;
    return this;
}


public List<DocumentLayoutElementDescriptor> getElements(){
    return elements;
}


public Builder builder(){
    return new Builder();
}


public DocumentId getASIDescriptorId(){
    return asiDescriptorId;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("asiDescriptorId", asiDescriptorId).add("caption", caption).add("elements-count", elementBuilders.size()).toString();
}


public Builder setCaption(ITranslatableString caption){
    this.caption = caption;
    return this;
}


public Builder setDescription(ITranslatableString description){
    this.description = description;
    return this;
}


public Builder addElement(DocumentLayoutElementDescriptor.Builder elementBuilder){
    Check.assumeNotNull(elementBuilder, "Parameter elementBuilder is not null");
    elementBuilders.add(elementBuilder);
    return this;
}


public String getCaption(String adLanguage){
    return caption.translate(adLanguage);
}


public String getDescription(String adLanguage){
    return description.translate(adLanguage);
}


public List<DocumentLayoutElementDescriptor> buildElements(){
    return elementBuilders.stream().map(elementBuilder -> elementBuilder.build()).collect(GuavaCollectors.toImmutableList());
}


}