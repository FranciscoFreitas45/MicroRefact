package de.metas.ui.web.window.datatypes.json;
 import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.quickinput.QuickInputLayoutDescriptor;
@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONQuickInputLayoutDescriptor implements Serializable{

@JsonProperty("elements")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONDocumentLayoutElement> elements;


public JSONQuickInputLayoutDescriptor fromNullable(QuickInputLayoutDescriptor layout,JSONDocumentLayoutOptions jsonOpts){
    if (layout == null) {
        return null;
    }
    return new JSONQuickInputLayoutDescriptor(layout, jsonOpts);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("elements", elements).toString();
}


}