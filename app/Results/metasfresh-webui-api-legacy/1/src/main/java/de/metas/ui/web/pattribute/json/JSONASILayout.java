package de.metas.ui.web.pattribute.json;
 import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.pattribute.ASILayout;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutElement;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONASILayout implements Serializable{

@JsonProperty("id")
 private  String id;

@JsonProperty("caption")
 private  String caption;

@JsonProperty("description")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String description;

@JsonProperty("elements")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONDocumentLayoutElement> elements;


public JSONASILayout of(ASILayout layout,JSONDocumentLayoutOptions options){
    return new JSONASILayout(layout, options);
}


public List<JSONDocumentLayoutElement> getElements(){
    return elements;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("id", id).add("caption", caption).add("description", description).add("elements", elements).toString();
}


public String getCaption(){
    return caption;
}


public String getDescription(){
    return description;
}


}