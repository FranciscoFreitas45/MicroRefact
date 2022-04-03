package de.metas.ui.web.window.datatypes.json;
 import java.io.Serializable;
import java.util.List;
import javax.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementGroupDescriptor;
import de.metas.util.GuavaCollectors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NonNull;
@ApiModel("elementGroup")
@SuppressWarnings("serial")
public class JSONDocumentLayoutElementGroup implements Serializable{

@ApiModelProperty(allowEmptyValue = true)
@JsonProperty("type")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
 private  JSONLayoutType type;

@// 
ApiModelProperty(allowEmptyValue = true, value = "Number of equal-width-columns into which the included elementsLines shall be displayed:\n" + "Notes:\n" + "* one element line per cell" + "* an empty element line shall be rendered as empty cell" + "* if you have e.g. columnCount=3 and four element lines, then the rightmost two cells of the last line shall be empty" + "* if this property is missing, then <code>1</code> should be assumed")
@JsonProperty("columnCount")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
 private  Integer columnCount;

@JsonProperty("internalName")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
 private  String internalName;

@// 
ApiModelProperty(allowEmptyValue = true, value = "Container for elementy that are supposed to be displayed next to each other\n" + "Notes:" + "* individual element lines might be empty for layout purposes; see <code>columnCount</code>\n" + "* in most of the cases, each elementLine has one element")
@JsonProperty("elementsLine")
@JsonInclude(JsonInclude.Include.ALWAYS)
@Getter
 private  List<JSONDocumentLayoutElementLine> elementLines;


public JSONDocumentLayoutElementGroup of(DocumentLayoutElementGroupDescriptor elementGroup,JSONDocumentLayoutOptions jsonOpts){
    return new JSONDocumentLayoutElementGroup(elementGroup, jsonOpts);
}


public boolean isEmpty(){
    final boolean atLeastOneLineIsFilled = elementLines.stream().anyMatch(line -> !line.isEmpty());
    return !atLeastOneLineIsFilled;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("type", type).add("columnCount", columnCount).add("elements", elementLines.isEmpty() ? null : elementLines).toString();
}


public List<JSONDocumentLayoutElementGroup> ofList(List<DocumentLayoutElementGroupDescriptor> elementGroups,JSONDocumentLayoutOptions jsonOpts){
    return elementGroups.stream().map(elementGroup -> of(elementGroup, jsonOpts)).filter(group -> !group.isEmpty()).collect(GuavaCollectors.toImmutableList());
}


}