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
public class DocumentLayoutElementLineDescriptor {

 private  String internalName;

 private  List<DocumentLayoutElementDescriptor> elements;

 private  Logger logger;

 private  String internalName;

 private  List<DocumentLayoutElementDescriptor.Builder> elementsBuilders;


public boolean hasElements(){
    return !elementsBuilders.isEmpty();
}


public DocumentLayoutElementLineDescriptor build(){
    final DocumentLayoutElementLineDescriptor result = new DocumentLayoutElementLineDescriptor(this);
    logger.trace("Built {} for {}", result, this);
    return result;
}


public List<DocumentLayoutElementDescriptor> getElements(){
    return elements;
}


public boolean checkValid(DocumentLayoutElementDescriptor element){
    if (!element.hasFields()) {
        logger.trace("Skip adding {} to {} because it does not have fields", element, this);
        return false;
    }
    return true;
}


public Builder builder(){
    return new Builder();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("internalName", internalName).add("elements-count", elementsBuilders.size()).toString();
}


public Builder addElement(DocumentLayoutElementDescriptor.Builder elementBuilder){
    elementsBuilders.add(elementBuilder);
    return this;
}


public List<DocumentLayoutElementDescriptor> buildElements(){
    return elementsBuilders.stream().filter(elementBuilder -> checkValid(elementBuilder)).map(elementBuilder -> elementBuilder.build()).filter(element -> checkValid(element)).collect(GuavaCollectors.toImmutableList());
}


public Builder setInternalName(String internalName){
    this.internalName = internalName;
    return this;
}


public Stream<DocumentLayoutElementDescriptor.Builder> streamElementBuilders(){
    return elementsBuilders.stream();
}


}