package DTO;
 import java.util.List;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
public class ViewRowAttributesLayout {

 private  List<DocumentLayoutElementDescriptor> elements;


public ViewRowAttributesLayout of(List<DocumentLayoutElementDescriptor> elements){
    return new ViewRowAttributesLayout(elements);
}


public List<DocumentLayoutElementDescriptor> getElements(){
    return elements;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("elements", elements).toString();
}


}