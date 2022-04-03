package de.metas.ui.web.window.datatypes.json;
 import java.io.Serializable;
import java.util.List;
import javax.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.descriptor.DocumentLayoutColumnDescriptor;
import de.metas.util.GuavaCollectors;
import io.swagger.annotations.ApiModel;
@ApiModel("column")
@SuppressWarnings("serial")
public class JSONDocumentLayoutColumn implements Serializable{

 static  JSONDocumentLayoutColumn EMPTY;

@JsonProperty("elementGroups")
@JsonInclude(Include.NON_EMPTY)
 private  List<JSONDocumentLayoutElementGroup> elementGroups;


public List<JSONDocumentLayoutElementGroup> getElementGroups(){
    return elementGroups;
}


public JSONDocumentLayoutColumn of(DocumentLayoutColumnDescriptor column,JSONDocumentLayoutOptions jsonOpts){
    return new JSONDocumentLayoutColumn(column, jsonOpts);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("elementGroups", elementGroups).toString();
}


public List<JSONDocumentLayoutColumn> ofList(List<DocumentLayoutColumnDescriptor> columns,JSONDocumentLayoutOptions jsonOpts){
    return columns.stream().map(column -> of(column, jsonOpts)).collect(GuavaCollectors.toImmutableList());
}


}