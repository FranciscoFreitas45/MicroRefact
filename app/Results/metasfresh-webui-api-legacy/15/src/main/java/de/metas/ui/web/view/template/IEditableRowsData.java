package de.metas.ui.web.view.template;
 import java.util.List;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.IEditableView.RowEditingContext;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
public interface IEditableRowsData extends IRowsData<T>{


public void patchRow(RowEditingContext ctx,List<JSONDocumentChangedEvent> fieldChangeRequests)
;

}