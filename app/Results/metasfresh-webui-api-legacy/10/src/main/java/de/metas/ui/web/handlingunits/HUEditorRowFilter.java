package de.metas.ui.web.handlingunits;
 import java.util.Set;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import java.util.Objects;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
@Value
public class HUEditorRowFilter {

 public  HUEditorRowFilter ALL;

 private  Select select;

 private  ImmutableSet<HUEditorRowId> onlyRowIds;

 private  ImmutableSet<String> onlyHUStatuses;

 private  ImmutableSet<String> excludeHUStatuses;

 private  ImmutableSet<HuId> excludeHUIds;

 private  String userInputFilter;


public HUEditorRowFilter andOnlyRowIds(DocumentIdsSelection rowIds){
    if (rowIds.isAll()) {
        // nothing to do
        return this;
    } else {
        return toBuilder().onlyRowIdsFromSelection(rowIds).build();
    }
}


public HUEditorRowFilter select(Select select){
    return builder().select(select).build();
}


public Builder onlyRowIdsFromSelection(DocumentIdsSelection rowIds){
    if (rowIds.isAll()) {
    // nothing
    } else if (rowIds.isEmpty()) {
        throw new AdempiereException("Empty rowIds is not allowed");
    } else {
        onlyRowIds(rowIds.toSet(HUEditorRowId::ofDocumentId));
    }
    return this;
}


public HUEditorRowFilter onlyRowIds(DocumentIdsSelection rowIds){
    if (rowIds.isAll()) {
        return ALL;
    } else {
        return builder().onlyRowIdsFromSelection(rowIds).build();
    }
}


public Builder onlyActiveHUs(){
    onlyHUStatus(X_M_HU.HUSTATUS_Active);
    return this;
}


}