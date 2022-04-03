package de.metas.ui.web.window.descriptor.DocumentLayoutDetailDescriptor;
 import de.metas.util.Check.assumeNotNull;
import java.util.ArrayList;
import java.util.List;
import com.google.common.base.MoreObjects;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Check;
import lombok.Getter;
import lombok.NonNull;
public class Builder {

 private  WindowId windowId;

 private  DetailId detailId;

 private  String internalName;

 private  ViewLayout.Builder gridLayout;

 private  DocumentLayoutSingleRow.Builder singleRowLayout;

 private  boolean supportQuickInput;

 private  boolean queryOnActivate;

 private  boolean singleRowDetailLayout;

 private  List<DocumentLayoutDetailDescriptor> subTabLayouts;

 private  ITranslatableString caption;

 private  ITranslatableString description;


public Builder gridLayout(ViewLayout.Builder gridLayout){
    this.gridLayout = gridLayout;
    gridLayout.setWindowId(windowId);
    gridLayout.setDetailId(detailId);
    return this;
}


public Builder singleRowDetailLayout(boolean singleRowDetailLayout){
    this.singleRowDetailLayout = singleRowDetailLayout;
    return this;
}


public boolean isEmpty(){
    return (gridLayout == null || !gridLayout.hasElements()) && (singleRowLayout == null || singleRowLayout.isEmpty());
}


public Builder caption(ITranslatableString caption){
    this.caption = caption;
    return this;
}


public Builder description(ITranslatableString description){
    this.description = description;
    return this;
}


public Builder singleRowLayout(DocumentLayoutSingleRow.Builder singleRowLayout){
    this.singleRowLayout = singleRowLayout;
    singleRowLayout.setWindowId(windowId);
    return this;
}


public Builder queryOnActivate(boolean queryOnActivate){
    this.queryOnActivate = queryOnActivate;
    return this;
}


public Builder supportQuickInput(boolean supportQuickInput){
    this.supportQuickInput = supportQuickInput;
    return this;
}


public Builder internalName(String internalName){
    this.internalName = internalName;
    return this;
}


public boolean isSupportQuickInput(){
    return supportQuickInput;
}


public Builder addAllSubTabLayouts(List<DocumentLayoutDetailDescriptor> subTabLayouts){
    this.subTabLayouts.addAll(subTabLayouts);
    return this;
}


public DocumentLayoutDetailDescriptor build(){
    return new DocumentLayoutDetailDescriptor(this);
}


public DocumentLayoutSingleRow buildSingleRowLayout(){
    if (singleRowLayout == null) {
        return null;
    }
    return singleRowLayout.build();
}


public Builder addSubTabLayout(DocumentLayoutDetailDescriptor subTabLayout){
    this.subTabLayouts.add(subTabLayout);
    return this;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("detailId", detailId).toString();
}


public ViewLayout buildGridLayout(){
    if (gridLayout == null) {
        return null;
    }
    return gridLayout.build();
}


}