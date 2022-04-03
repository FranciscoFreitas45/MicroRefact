package de.metas.ui.web.address.AddressLayout;
 import java.util.ArrayList;
import java.util.List;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
public class Builder {

 private  List<DocumentLayoutElementDescriptor.Builder> elementBuilders;


public AddressLayout build(){
    return new AddressLayout(this);
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