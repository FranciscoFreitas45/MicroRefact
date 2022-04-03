package de.metas.ui.web.view.json;
 import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.view.descriptor.ViewRowAttributesLayout;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutElement;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONViewRowAttributesLayout implements Serializable{

 private  List<JSONDocumentLayoutElement> elements;


public JSONViewRowAttributesLayout of(ViewRowAttributesLayout layout,JSONDocumentLayoutOptions jsonOpts){
    return new JSONViewRowAttributesLayout(layout, jsonOpts);
}


public List<JSONDocumentLayoutElement> getElements(){
    return elements;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("elements", elements).toString();
}


}