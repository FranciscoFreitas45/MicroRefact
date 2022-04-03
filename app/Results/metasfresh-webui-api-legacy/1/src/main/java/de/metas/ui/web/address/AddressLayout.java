package de.metas.ui.web.address;
 import java.util.ArrayList;
import java.util.List;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
public class AddressLayout {

 private  List<DocumentLayoutElementDescriptor> elements;

 private  List<DocumentLayoutElementDescriptor.Builder> elementBuilders;


public AddressLayout build(){
    return new AddressLayout(this);
}


public List<DocumentLayoutElementDescriptor> getElements(){
    return elements;
}


public Builder builder(){
    return new Builder();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("elements-count", elementBuilders.size()).toString();
}


public Builder addElement(DocumentLayoutElementDescriptor.Builder elementBuilder){
    Check.assumeNotNull(elementBuilder, "Parameter elementBuilder is not null");
    elementBuilders.add(elementBuilder);
    return this;
}


public List<DocumentLayoutElementDescriptor> buildElements(){
    return elementBuilders.stream().map(elementBuilder -> elementBuilder.build()).collect(GuavaCollectors.toImmutableList());
}


}