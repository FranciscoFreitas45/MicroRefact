package de.metas.ui.web.quickinput.QuickInputLayoutDescriptor;
 import java.util.ArrayList;
import java.util.List;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.util.Check;
import lombok.NonNull;
public class Builder {

 private  List<DocumentLayoutElementDescriptor> elements;


public QuickInputLayoutDescriptor build(){
    return new QuickInputLayoutDescriptor(elements);
}


public Builder element(DocumentLayoutElementDescriptor.Builder elementBuilder){
    elements.add(elementBuilder.build());
    return this;
}


}