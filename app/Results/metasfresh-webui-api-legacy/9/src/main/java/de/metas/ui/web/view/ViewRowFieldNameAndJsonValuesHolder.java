package de.metas.ui.web.view;
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

@Getter
 private  ImmutableMap<String,ViewEditorRenderMode> viewEditorRenderModeByFieldName;

@Getter
 private  ImmutableMap<String,DocumentFieldWidgetType> widgetTypesByFieldName;

 private  ImmutableSet<String> fieldNames;

 private  ViewRowFieldNameAndJsonValues values;


public void clearValues(){
    this.values = null;
}


public ViewRowFieldNameAndJsonValues get(RowType row){
    ViewRowFieldNameAndJsonValues values = this.values;
    if (values == null) {
        values = this.values = ViewColumnHelper.extractJsonMap(row);
    }
    return values;
}


public ViewRowFieldNameAndJsonValuesHolderBuilder<RowType> builder(Class<RowType> rowType){
    return new ViewRowFieldNameAndJsonValuesHolderBuilder<RowType>().rowType(rowType);
}


public ViewRowFieldNameAndJsonValuesHolder<RowType> newInstance(Class<RowType> rowType){
    return new ViewRowFieldNameAndJsonValuesHolder<>(rowType);
}


public ViewRowFieldNameAndJsonValuesHolder<RowType> copy(){
    return new ViewRowFieldNameAndJsonValuesHolder<>(this);
}


public ImmutableSet<String> getFieldNames(){
    ImmutableSet<String> fieldNames = this.fieldNames;
    if (fieldNames == null) {
        fieldNames = this.fieldNames = ImmutableSet.<String>builder().addAll(ViewColumnHelper.extractFieldNames(rowType)).addAll(getViewEditorRenderModeByFieldName().keySet()).addAll(getWidgetTypesByFieldName().keySet()).build();
    }
    return fieldNames;
}


}