package de.metas.ui.web.quickinput;
 import java.util.ArrayList;
import java.util.List;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.util.Check;
import lombok.NonNull;
public class QuickInputLayoutDescriptor {

 private  List<DocumentLayoutElementDescriptor> elements;

 private  List<DocumentLayoutElementDescriptor> elements;


public QuickInputLayoutDescriptor build(){
    return new QuickInputLayoutDescriptor(elements);
}


public List<DocumentLayoutElementDescriptor> getElements(){
    return elements;
}


public Builder builder(){
    return new Builder();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("elements", elements.isEmpty() ? null : elements).toString();
}


public Builder element(DocumentLayoutElementDescriptor.Builder elementBuilder){
    elements.add(elementBuilder.build());
    return this;
}


}