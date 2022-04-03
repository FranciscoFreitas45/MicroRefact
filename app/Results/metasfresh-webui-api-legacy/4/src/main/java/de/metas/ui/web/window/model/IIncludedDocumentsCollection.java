package de.metas.ui.web.window.model;
 import org.adempiere.ad.expression.api.LogicExpressionResult;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.Document.OnValidStatusChanged;
public interface IIncludedDocumentsCollection {


public void markStaleAll()
;

public void onChildChanged(Document document){
// nothing
}
;

public int getNextLineNo()
;

public void assertNewDocumentAllowed()
;

public OrderedDocumentsList getDocuments(DocumentQueryOrderByList orderBys)
;

public DocumentValidStatus checkAndGetValidStatus(OnValidStatusChanged onValidStatusChanged)
;

public OrderedDocumentsList getDocumentsByIds(DocumentIdsSelection documentIds)
;

public void updateStatusFromParent()
;

public LogicExpressionResult getAllowCreateNewDocument()
;

public LogicExpressionResult getAllowDeleteDocument()
;

public void deleteDocuments(DocumentIdsSelection documentIds)
;

public boolean hasChangesRecursivelly()
;

public void markStale(DocumentIdsSelection rowIds)
;

public Document getDocumentById(DocumentId documentId)
;

public IIncludedDocumentsCollection copy(Document parentDocumentCopy,CopyMode copyMode)
;

public Document createNewDocument()
;

public void onChildSaved(Document document){
// nothing
}
;

public boolean isStale()
;

public DetailId getDetailId()
;

public void saveIfHasChanges()
;

}