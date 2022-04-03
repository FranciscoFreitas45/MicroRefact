package DTO;
 import javax.annotation.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.view.descriptor.annotation.ViewColumnHelper;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.ViewEditorRenderMode;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
public class ViewRowFieldNameAndJsonValuesHolder {

 private  Class<RowType> rowType;

 private  ImmutableMap<String,ViewEditorRenderMode> viewEditorRenderModeByFieldName;

 private  ImmutableMap<String,DocumentFieldWidgetType> widgetTypesByFieldName;

 private  ImmutableSet<String> fieldNames;

 private  ViewRowFieldNameAndJsonValues values;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


public ViewRowFieldNameAndJsonValues get(RowType row){
    ViewRowFieldNameAndJsonValues values = this.values;
    if (values == null) {
        values = this.values = ViewColumnHelper.extractJsonMap(row);
    }
    return values;
}


public ImmutableSet<String> getFieldNames(){
    ImmutableSet<String> fieldNames = this.fieldNames;
    if (fieldNames == null) {
        fieldNames = this.fieldNames = ImmutableSet.<String>builder().addAll(ViewColumnHelper.extractFieldNames(rowType)).addAll(getViewEditorRenderModeByFieldName().keySet()).addAll(getWidgetTypesByFieldName().keySet()).build();
    }
    return fieldNames;
}


public ViewRowFieldNameAndJsonValuesHolderBuilder<RowType> builder(Class<RowType> rowType){
    return new ViewRowFieldNameAndJsonValuesHolderBuilder<RowType>().rowType(rowType);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))

.queryParam("rowType",rowType);
ViewRowFieldNameAndJsonValuesHolderBuilder<RowType> aux = restTemplate.getForObject(builder.toUriString(),ViewRowFieldNameAndJsonValuesHolderBuilder<RowType>.class);
return aux;
}


}