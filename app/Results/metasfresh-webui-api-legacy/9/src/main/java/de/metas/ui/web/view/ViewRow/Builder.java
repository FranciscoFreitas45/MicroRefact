package de.metas.ui.web.view.ViewRow;
 import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.handlingunits.HUEditorRowType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONNullValue;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class Builder {

 private  WindowId windowId;

 private  DocumentId rowId;

 private  DocumentId _rowIdEffective;

 private  DocumentId parentRowId;

 private  IViewRowType type;

 private  Boolean processed;

 private  Map<String,Object> values;

 private  List<IViewRow> includedRows;


public Map<String,Object> getValues(){
    return values;
}


public DocumentId getRowId(){
    if (_rowIdEffective == null) {
        if (rowId == null) {
            throw new IllegalStateException("No rowId was provided for " + this);
        }
        if (isRootRow()) {
            _rowIdEffective = rowId;
        } else {
            // NOTE: we have to do this because usually, the root row can have the same ID as one of the included rows,
            // because the root/aggregated rows are build on demand and they don't really exist in database.
            // Also see https://github.com/metasfresh/metasfresh-webui-frontend/issues/835#issuecomment-307783959
            _rowIdEffective = rowId.toIncludedRowId();
        }
    }
    return _rowIdEffective;
}


public DocumentId getParentRowId(){
    return parentRowId;
}


public Builder setParentRowId(DocumentId parentRowId){
    this.parentRowId = parentRowId;
    _rowIdEffective = null;
    return this;
}


public Builder setType(IViewRowType type){
    this.type = type;
    return this;
}


public Builder setProcessed(boolean processed){
    this.processed = processed;
    return this;
}


public LookupValue getFieldValueAsLookupValue(String fieldName){
    return LookupValue.cast(values.get(fieldName));
}


public Builder addIncludedRow(IViewRow includedRow){
    if (includedRows == null) {
        includedRows = new ArrayList<>();
    }
    includedRows.add(includedRow);
    return this;
}


public ViewRow build(){
    return new ViewRow(this);
}


public Builder setRowId(DocumentId rowId){
    this.rowId = rowId;
    _rowIdEffective = null;
    return this;
}


public IViewRowType getType(){
    return type;
}


public Builder putFieldValue(String fieldName,Object jsonValue){
    if (JSONNullValue.isNull(jsonValue)) {
        values.remove(fieldName);
    } else {
        values.put(fieldName, jsonValue);
    }
    return this;
}


public DocumentPath getDocumentPath(){
    final DocumentId documentId = getRowId();
    return DocumentPath.rootDocumentPath(windowId, documentId);
}


public boolean isProcessed(){
    if (processed == null) {
        // NOTE: don't take the "Processed" field if any, because in frontend we will end up with a lot of grayed out completed sales orders, for example.
        // return DisplayType.toBoolean(values.getOrDefault("Processed", false));
        return false;
    } else {
        return processed.booleanValue();
    }
}


public boolean isRootRow(){
    return getParentRowId() == null;
}


public List<IViewRow> buildIncludedRows(){
    if (includedRows == null || includedRows.isEmpty()) {
        return ImmutableList.of();
    }
    return ImmutableList.copyOf(includedRows);
}


}