package de.metas.ui.web.window.datatypes.json;
 import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.descriptor.DocumentLayoutSectionDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutSectionDescriptor.CaptionMode;
import de.metas.ui.web.window.descriptor.DocumentLayoutSectionDescriptor.ClosableMode;
import io.swagger.annotations.ApiModel;
import lombok.NonNull;
@ApiModel("section")
public class JSONDocumentLayoutSection {

@JsonProperty("title")
@JsonInclude(Include.NON_EMPTY)
 private  String title;

@JsonProperty("description")
@JsonInclude(Include.NON_EMPTY)
 private  String description;

@JsonProperty("columns")
@JsonInclude(Include.NON_EMPTY)
 private  List<JSONDocumentLayoutColumn> columns;

@JsonProperty("closableMode")
@JsonInclude(Include.NON_EMPTY)
 private  JSONClosableMode closableMode;


public JSONClosableMode ofClosableMode(ClosableMode closableMode){
    switch(closableMode) {
        case ALWAYS_OPEN:
            return ALWAYS_OPEN;
        case INITIALLY_CLOSED:
            return INITIALLY_CLOSED;
        case INITIALLY_OPEN:
            return INITIALLY_OPEN;
        default:
            throw new AdempiereException("Unexpected closableMode=" + closableMode);
    }
}


public String exctractTitle(DocumentLayoutSectionDescriptor section,JSONDocumentLayoutOptions options){
    if (CaptionMode.DISPLAY.equals(section.getCaptionMode())) {
        return section.getCaption(options.getAdLanguage()).trim();
    } else if (CaptionMode.DISPLAY_IN_ADV_EDIT.equals(section.getCaptionMode())) {
        if (options.isShowAdvancedFields()) {
            return section.getCaption(options.getAdLanguage()).trim();
        } else {
            return null;
        }
    } else if (CaptionMode.DONT_DISPLAY.equals(section.getCaptionMode())) {
        return null;
    }
    throw new AdempiereException("Unexpected captionMode=" + section.getCaptionMode()).appendParametersToMessage().setParameter("documentLayoutSectionDescriptor", section);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("columns", columns).toString();
}


public List<JSONDocumentLayoutSection> ofSectionsList(List<DocumentLayoutSectionDescriptor> sections,JSONDocumentLayoutOptions jsonOpts){
    return sections.stream().map(section -> new JSONDocumentLayoutSection(section, jsonOpts)).collect(ImmutableList.toImmutableList());
}


public List<JSONDocumentLayoutColumn> getColumns(){
    return columns;
}


}