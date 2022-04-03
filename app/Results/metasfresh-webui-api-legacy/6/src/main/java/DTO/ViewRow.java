package DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


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


@Override
public ImmutableSet<String> getFieldNames(){
    return values.getFieldNames();
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


public LookupValue getFieldValueAsLookupValue(String fieldName){
    return LookupValue.cast(values.get(fieldName));
}


@Override
public ViewRowFieldNameAndJsonValues getFieldNameAndJsonValues(){
    return values;
}


public IViewRowType getType(){
    return type;
}


public DocumentPath getDocumentPath(){
    final DocumentId documentId = getRowId();
    return DocumentPath.rootDocumentPath(windowId, documentId);
}


public DocumentId getParentId(){
    return parentRowId;
}


public Builder builder(WindowId windowId){
    return new Builder(windowId);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))

.queryParam("windowId",windowId);
Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


public ViewRow cast(IViewRow row){
    return (ViewRow) row;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/cast"))

.queryParam("row",row);
ViewRow aux = restTemplate.getForObject(builder.toUriString(),ViewRow.class);
return aux;
}


}