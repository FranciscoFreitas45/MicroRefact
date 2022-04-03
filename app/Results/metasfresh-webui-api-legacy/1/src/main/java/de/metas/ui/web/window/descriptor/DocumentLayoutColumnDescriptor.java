package de.metas.ui.web.window.descriptor;
 import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.logging.LogManager;
import de.metas.util.GuavaCollectors;
import lombok.NonNull;
public class DocumentLayoutColumnDescriptor {

 private  String internalName;

 private  List<DocumentLayoutElementGroupDescriptor> elementGroups;

 private  Logger logger;

 private  String internalName;

 private  List<DocumentLayoutElementGroupDescriptor.Builder> elementGroupsBuilders;


public List<DocumentLayoutElementGroupDescriptor> getElementGroups(){
    return elementGroups;
}


public DocumentLayoutColumnDescriptor build(){
    final DocumentLayoutColumnDescriptor result = new DocumentLayoutColumnDescriptor(this);
    logger.trace("Built {} for {}", result, this);
    return result;
}


public Builder addElementGroup(DocumentLayoutElementGroupDescriptor.Builder elementGroupBuilder){
    elementGroupsBuilders.add(elementGroupBuilder);
    return this;
}


public boolean checkValid(DocumentLayoutElementGroupDescriptor elementGroup){
    if (!elementGroup.hasElementLines()) {
        logger.trace("Skip adding {} to {} because it does not have element line", elementGroup, this);
        return false;
    }
    return true;
}


public Builder builder(){
    return new Builder();
}


public Builder addElementTabs(List<DocumentLayoutElementGroupDescriptor.Builder> elementGroupBuilders){
    elementGroupsBuilders.addAll(elementGroupBuilders);
    return this;
}


public boolean hasElementGroups(){
    return !elementGroups.isEmpty();
}


public List<DocumentLayoutElementGroupDescriptor> buildElementGroups(){
    return elementGroupsBuilders.stream().map(elementGroupBuilder -> elementGroupBuilder.build()).filter(elementGroup -> checkValid(elementGroup)).collect(GuavaCollectors.toImmutableList());
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("internalName", internalName).add("elementGroups-count", elementGroupsBuilders.size()).toString();
}


public Builder setInternalName(String internalName){
    this.internalName = internalName;
    return this;
}


public Stream<DocumentLayoutElementDescriptor.Builder> streamElementBuilders(){
    return elementGroupsBuilders.stream().flatMap(DocumentLayoutElementGroupDescriptor.Builder::streamElementBuilders);
}


}