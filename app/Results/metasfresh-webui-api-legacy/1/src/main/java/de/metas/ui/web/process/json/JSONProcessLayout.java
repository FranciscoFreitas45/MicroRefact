package de.metas.ui.web.process.json;
 import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.process.BarcodeScannerType;
import de.metas.ui.web.process.descriptor.ProcessLayout;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutElement;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONProcessLayout {

@JsonProperty("layoutType")
 private  PanelLayoutType layoutType;

@JsonProperty("barcodeScannerType")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  BarcodeScannerType barcodeScannerType;

@JsonProperty("caption")
 private  String caption;

@JsonProperty("description")
 private  String description;

@JsonProperty("elements")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONDocumentLayoutElement> elements;


public JSONProcessLayout of(ProcessLayout layout,JSONDocumentLayoutOptions jsonOpts){
    return new JSONProcessLayout(layout, jsonOpts);
}


}