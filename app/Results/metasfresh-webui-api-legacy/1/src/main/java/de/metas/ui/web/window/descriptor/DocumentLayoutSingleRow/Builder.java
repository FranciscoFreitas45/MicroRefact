package de.metas.ui.web.window.descriptor.DocumentLayoutSingleRow;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Check;
import lombok.NonNull;
public class Builder {

 public  WindowId windowId;

 private  ITranslatableString caption;

 private  ITranslatableString description;

 private  List<DocumentLayoutSectionDescriptor.Builder> sectionBuilders;


public Builder addSections(Collection<DocumentLayoutSectionDescriptor.Builder> sectionBuildersToAdd){
    sectionBuildersToAdd.forEach(sectionBuilders::add);
    return this;
}


public List<DocumentLayoutSectionDescriptor> buildSections(){
    return sectionBuilders.stream().filter(sectionBuilder -> sectionBuilder.isValid()).map(sectionBuilder -> sectionBuilder.build()).filter(section -> section.hasColumns()).collect(ImmutableList.toImmutableList());
}


public DocumentLayoutSingleRow build(){
    return new DocumentLayoutSingleRow(this);
}


public Builder addSection(DocumentLayoutSectionDescriptor.Builder sectionBuilderToAdd){
    sectionBuilders.add(sectionBuilderToAdd);
    return this;
}


public boolean isEmpty(){
    return sectionBuilders.isEmpty();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("caption", caption).add("sections-count", sectionBuilders.size()).toString();
}


public Builder setWindowId(WindowId windowId){
    this.windowId = windowId;
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


}