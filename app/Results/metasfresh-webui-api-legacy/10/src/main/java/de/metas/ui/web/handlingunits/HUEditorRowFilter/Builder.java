package de.metas.ui.web.handlingunits.HUEditorRowFilter;
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
public class Builder {


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


public Builder onlyActiveHUs(){
    onlyHUStatus(X_M_HU.HUSTATUS_Active);
    return this;
}


}