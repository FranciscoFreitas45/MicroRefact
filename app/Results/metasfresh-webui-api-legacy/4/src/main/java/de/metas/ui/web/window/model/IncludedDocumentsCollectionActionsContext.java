package de.metas.ui.web.window.model;
 import java.util.Collection;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import org.compiere.util.Evaluatee;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DetailId;
public interface IncludedDocumentsCollectionActionsContext {


public boolean isParentDocumentNew()
;

public boolean isParentDocumentActive()
;

public boolean isParentDocumentInvalid()
;

public boolean isParentDocumentProcessed()
;

public void collectAllowDelete(DocumentPath parentDocumentPath,DetailId detailId,LogicExpressionResult allowDelete)
;

public Evaluatee toEvaluatee()
;

public void collectAllowNew(DocumentPath parentDocumentPath,DetailId detailId,LogicExpressionResult allowNew)
;

public Collection<Document> getIncludedDocuments()
;

}