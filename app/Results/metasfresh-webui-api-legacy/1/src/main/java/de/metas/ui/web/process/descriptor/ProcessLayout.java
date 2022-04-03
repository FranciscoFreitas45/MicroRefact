package de.metas.ui.web.process.descriptor;
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


public PanelLayoutType getLayoutType(){
    return layoutType;
}


public Builder setLayoutType(PanelLayoutType layoutType){
    this.layoutType = layoutType;
    return this;
}


public Builder setProcessId(ProcessId processId){
    this.processId = processId;
    return this;
}


public Builder setCaption(ITranslatableString caption){
    this.caption = caption;
    return this;
}


public Builder setDescription(ITranslatableString description){
    this.description = description;
    return this;
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


public ProcessLayout build(){
    return new ProcessLayout(this);
}


public Builder addElements(DocumentEntityDescriptor parametersDescriptor){
    if (parametersDescriptor != null) {
        parametersDescriptor.getFields().forEach(this::addElement);
    }
    return this;
}


public List<DocumentLayoutElementDescriptor> getElements(){
    return elements;
}


public Builder builder(){
    return new Builder();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("processId", processId).add("caption", caption).add("elements-count", elements.size()).toString();
}


public Builder addElement(DocumentFieldDescriptor processParaDescriptor){
    Check.assumeNotNull(processParaDescriptor, "Parameter processParaDescriptor is not null");
    final DocumentLayoutElementDescriptor element = DocumentLayoutElementDescriptor.builder().setCaption(processParaDescriptor.getCaption()).setDescription(processParaDescriptor.getDescription()).setWidgetType(processParaDescriptor.getWidgetType()).setAllowShowPassword(processParaDescriptor.isAllowShowPassword()).barcodeScannerType(processParaDescriptor.getBarcodeScannerType()).addField(DocumentLayoutElementFieldDescriptor.builder(processParaDescriptor.getFieldName()).setLookupInfos(processParaDescriptor.getLookupDescriptor().orElse(null)).setPublicField(true).setSupportZoomInto(processParaDescriptor.isSupportZoomInto())).build();
    addElement(element);
    return this;
}


}