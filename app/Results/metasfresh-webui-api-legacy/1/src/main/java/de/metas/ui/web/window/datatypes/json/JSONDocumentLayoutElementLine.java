package de.metas.ui.web.window.datatypes.json;
 import java.io.Serializable;
import java.util.List;
import javax.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementLineDescriptor;
import de.metas.util.GuavaCollectors;
import io.swagger.annotations.ApiModel;
import lombok.NonNull;
@ApiModel("element-line")
@SuppressWarnings("serial")
public class JSONDocumentLayoutElementLine implements Serializable{

@JsonProperty("elements")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONDocumentLayoutElement> elements;


public JSONDocumentLayoutElementLine ofDocumentLayoutElementLineDescriptor(DocumentLayoutElementLineDescriptor elementLine,JSONDocumentLayoutOptions options){
    return new JSONDocumentLayoutElementLine(elementLine, options);
}


public List<JSONDocumentLayoutElement> getElements(){
    return elements;
}


@JsonIgnore
public boolean isEmpty(){
    return elements.isEmpty();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("elements", elements).toString();
}


public List<JSONDocumentLayoutElementLine> ofList(List<DocumentLayoutElementLineDescriptor> elementsLines,JSONDocumentLayoutOptions options){
    return elementsLines.stream().map(elementsLine -> ofDocumentLayoutElementLineDescriptor(elementsLine, options)).collect(GuavaCollectors.toImmutableList());
}


}