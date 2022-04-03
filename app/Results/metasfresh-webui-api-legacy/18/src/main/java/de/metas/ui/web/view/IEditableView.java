package de.metas.ui.web.view;
 import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.model.DocumentCollection;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
public interface IEditableView extends IView{

@NonNull
 private  DocumentId rowId;

@NonNull
 private  DocumentCollection documentsCollection;


public LookupValuesList getFieldTypeahead(RowEditingContext ctx,String fieldName,String query)
;

public LookupValuesList getFieldDropdown(RowEditingContext ctx,String fieldName)
;

public IEditableView asEditableView(IView view){
    if (view instanceof IEditableView) {
        return (IEditableView) view;
    } else {
        throw new AdempiereException("View is not editable").setParameter("view", view);
    }
}
;

public void patchViewRow(RowEditingContext ctx,List<JSONDocumentChangedEvent> fieldChangeRequests)
;

}