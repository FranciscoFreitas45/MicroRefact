package DTO;
 import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.IViewRowOverrides;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewRowOverridesHelper;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONDocumentBase;
import de.metas.ui.web.window.datatypes.json.JSONDocumentField;
import de.metas.ui.web.window.datatypes.json.JSONLayoutWidgetType;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.ViewEditorRenderMode;
import de.metas.util.GuavaCollectors;
import lombok.Value;
public class JSONViewRow extends JSONDocumentBaseimplements JSONViewRowBase{

 private  String type;

 private  Boolean processed;

 private  Boolean supportAttributes;

 private  Boolean supportIncludedViews;

 private  JSONIncludedViewId includedView;

 private  List<JSONViewRow> includedDocuments;

 private  Boolean colspan;

 private  String caption;

 private  WindowId windowId;

 private  String viewId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


public List<JSONViewRow> ofViewRows(List<? extends IViewRow> rows,IViewRowOverrides rowOverrides,JSONOptions jsonOpts){
    return rows.stream().map(row -> ofRow(row, rowOverrides, jsonOpts)).collect(Collectors.toList());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofViewRows"))

.queryParam("rows",rows);
.queryParam("rowOverrides",rowOverrides);
.queryParam("jsonOpts",jsonOpts);
List<JSONViewRow> aux = restTemplate.getForObject(builder.toUriString(),List<JSONViewRow>.class);
return aux;
}


}