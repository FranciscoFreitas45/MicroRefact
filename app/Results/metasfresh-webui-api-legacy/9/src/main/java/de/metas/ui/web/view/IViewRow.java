package de.metas.ui.web.view;
 import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.ViewRow.DefaultRowType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.ViewEditorRenderMode;
import lombok.NonNull;
public interface IViewRow {


public boolean isSingleColumn(){
    return false;
}
;

public int getFieldValueAsInt(String fieldName,int defaultValueIfNotFoundOrError){
    return getFieldNameAndJsonValues().getAsInt(fieldName, defaultValueIfNotFoundOrError);
}
;

public Object getFieldValueAsJsonObject(String fieldName,JSONOptions jsonOpts){
    return getFieldNameAndJsonValues().getAsJsonObject(fieldName, jsonOpts);
}
;

public IViewRowAttributes getAttributes(){
    throw new EntityNotFoundException("Row does not support attributes");
}
;

public BigDecimal getFieldValueAsBigDecimal(String fieldName,BigDecimal defaultValueIfNotFoundOrError){
    return getFieldNameAndJsonValues().getAsBigDecimal(fieldName, defaultValueIfNotFoundOrError);
}
;

public ViewId getIncludedViewId(){
    return null;
}
;

public Stream<IViewRow> streamRecursive(){
    return this.getIncludedRows().stream().map(includedRow -> includedRow.streamRecursive()).reduce(Stream.of(this), Stream::concat);
}
;

public DocumentId getId()
;

public ITranslatableString getSingleColumnCaption(){
    return TranslatableStrings.empty();
}
;

public Set<String> getFieldNames()
;

public Map<String,DocumentFieldWidgetType> getWidgetTypesByFieldName(){
    return ImmutableMap.of();
}
;

public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues()
;

public Map<String,ViewEditorRenderMode> getViewEditorRenderModeByFieldName(){
    return ImmutableMap.of();
}
;

public IViewRowType getType(){
    return DefaultRowType.Row;
}
;

public boolean getFieldValueAsBoolean(String fieldName,boolean defaultValueIfNotFoundOrError){
    return getFieldNameAndJsonValues().getAsBoolean(fieldName, defaultValueIfNotFoundOrError);
}
;

public DocumentPath getDocumentPath()
;

public boolean hasAttributes(){
    return false;
}
;

public boolean isProcessed()
;

public Collection<? extends IViewRow> getIncludedRows(){
    return ImmutableList.of();
}
;

}