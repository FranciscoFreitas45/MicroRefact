package de.metas.ui.web.view;
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
public class ViewRow implements IViewRow{

 private  DocumentPath documentPath;

 private  DocumentId rowId;

 private  DocumentId parentRowId;

 private  IViewRowType type;

 private  boolean processed;

 private  ViewRowFieldNameAndJsonValues values;

 private  List<IViewRow> includedRows;

 private  WindowId windowId;

 private  DocumentId rowId;

 private  DocumentId _rowIdEffective;

 private  DocumentId parentRowId;

 private  IViewRowType type;

 private  Boolean processed;

 private  Map<String,Object> values;

 private  List<IViewRow> includedRows;


@Override
public String getName(){
    // FIXME: use some proper name and icon
    return HUEditorRowType.TU.getName();
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


@Override
public DocumentId getId(){
    return rowId;
}


public DocumentId getParentRowId(){
    return parentRowId;
}


public Builder setParentRowId(DocumentId parentRowId){
    this.parentRowId = parentRowId;
    _rowIdEffective = null;
    return this;
}


public Builder setProcessed(boolean processed){
    this.processed = processed;
    return this;
}


@Override
public ImmutableSet<String> getFieldNames(){
    return values.getFieldNames();
}


public ViewRow cast(IViewRow row){
    return (ViewRow) row;
}


public Builder putFieldValue(String fieldName,Object jsonValue){
    if (JSONNullValue.isNull(jsonValue)) {
        values.remove(fieldName);
    } else {
        values.put(fieldName, jsonValue);
    }
    return this;
}


@Override
public boolean hasAttributes(){
    return false;
}


public Builder builder(WindowId windowId){
    return new Builder(windowId);
}


@Override
public List<IViewRow> getIncludedRows(){
    return includedRows;
}


@Override
public IViewRowAttributes getAttributes(){
    throw new EntityNotFoundException("row does not support attributes");
}


public Map<String,Object> getValues(){
    return values;
}


public Builder setType(IViewRowType type){
    this.type = type;
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


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values;
}


public IViewRowType getType(){
    return type;
}


public ViewRow build(){
    return new ViewRow(this);
}


public Builder setRowId(DocumentId rowId){
    this.rowId = rowId;
    _rowIdEffective = null;
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


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("id", rowId).add("type", type).add("parentId", parentRowId).add("values", values).add("includedRows.count", includedRows.size()).add("processed", processed).toString();
}


public boolean isRootRow(){
    return getParentRowId() == null;
}


public DocumentId getParentId(){
    return parentRowId;
}


public List<IViewRow> buildIncludedRows(){
    if (includedRows == null || includedRows.isEmpty()) {
        return ImmutableList.of();
    }
    return ImmutableList.copyOf(includedRows);
}


}