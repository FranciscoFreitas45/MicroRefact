package de.metas.ui.web.view.IEditableView;
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
@Builder
@Getter
@ToString(exclude = "documentsCollection")
public class RowEditingContext {

@NonNull
 private  DocumentId rowId;

@NonNull
 private  DocumentCollection documentsCollection;


}