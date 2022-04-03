package de.metas.ui.web.window.model;
 import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.ILogicExpression;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.exceptions.InvalidDocumentStateException;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
@ToString(exclude = { "allowCreateNewLogic", "allowDeleteLogic" })
public class IncludedDocumentsCollectionActions {

 private  LogicExpressionResult DISALLOW_Initially;

 private  LogicExpressionResult DISALLOW_ParentDocumentProcessed;

 private  LogicExpressionResult DISALLOW_ParentDocumentNotActive;

 private  LogicExpressionResult DISALLOW_ParentDocumentNew;

 private  LogicExpressionResult DISALLOW_ParentDocumentInvalid;

 private  LogicExpressionResult DISALLOW_AnotherNewDocumentAlreadyExists;

 private  LogicExpressionResult DISALLOW_UnsavedRowFound;

 private  DetailId detailId;

 private  ILogicExpression allowCreateNewLogic;

 private  ILogicExpression allowDeleteLogic;

 private  DocumentPath parentDocumentPath;

 private  LogicExpressionResult allowNew;

 private  LogicExpressionResult allowDelete;


public LogicExpressionResult computeAllowDeleteDocument(IncludedDocumentsCollectionActionsContext context){
    // Quickly check if the allowDelete logic it's constant and it's false.
    // In that case we can return right away.
    if (allowDeleteLogic.isConstantFalse()) {
        return LogicExpressionResult.ofConstantExpression(allowDeleteLogic);
    }
    if (context.isParentDocumentProcessed()) {
        return DISALLOW_ParentDocumentProcessed;
    }
    if (!context.isParentDocumentActive()) {
        return DISALLOW_ParentDocumentNotActive;
    }
    final LogicExpressionResult allowDelete = allowDeleteLogic.evaluateToResult(context.toEvaluatee(), OnVariableNotFound.ReturnNoResult);
    return allowDelete;
}


public LogicExpressionResult updateAndGetAllowCreateNewDocument(IncludedDocumentsCollectionActionsContext context){
    final LogicExpressionResult allowNew = computeAllowCreateNewDocument(context);
    return setAllowNewAndCollect(allowNew, context);
}


public void updateAndAssertAlowCreateNew(IncludedDocumentsCollectionActionsContext context){
    final LogicExpressionResult allowCreateNewDocument = updateAndGetAllowCreateNewDocument(context);
    if (allowCreateNewDocument.isFalse()) {
        throw new InvalidDocumentStateException(parentDocumentPath, "Cannot create included document because it's not allowed.").setParameter("allowCreateNewDocument", allowCreateNewDocument).setParameter("allowCreateNewLogic", allowCreateNewLogic).setParameter("detailId", detailId).setParameter("context", context);
    }
}


public void assertDeleteDocumentAllowed(IncludedDocumentsCollectionActionsContext context){
    final LogicExpressionResult allowDelete = updateAndGetAllowDeleteDocument(context);
    if (allowDelete.isFalse()) {
        throw new InvalidDocumentStateException(parentDocumentPath, "Cannot delete included document because it's not allowed: " + allowDelete);
    }
}


public LogicExpressionResult updateAndGetAllowDeleteDocument(IncludedDocumentsCollectionActionsContext context){
    final LogicExpressionResult allowDeleteOld = allowDelete;
    final LogicExpressionResult allowDelete = computeAllowDeleteDocument(context);
    this.allowDelete = allowDelete;
    if (!allowDeleteOld.equalsByNameAndValue(allowDelete)) {
        context.collectAllowDelete(parentDocumentPath, detailId, allowDelete);
    }
    return allowDelete;
}


public LogicExpressionResult computeAllowCreateNewDocument(IncludedDocumentsCollectionActionsContext context){
    // Quickly check if the allowCreateNew logic it's constant and it's false.
    // In that case we can return right away.
    if (allowCreateNewLogic.isConstantFalse()) {
        return LogicExpressionResult.ofConstantExpression(allowCreateNewLogic);
    }
    if (context.isParentDocumentProcessed()) {
        return DISALLOW_ParentDocumentProcessed;
    }
    if (!context.isParentDocumentActive()) {
        return DISALLOW_ParentDocumentNotActive;
    }
    if (context.isParentDocumentNew()) {
        return DISALLOW_ParentDocumentNew;
    }
    if (context.isParentDocumentInvalid()) {
        return DISALLOW_ParentDocumentInvalid;
    }
    // 
    // Check all included documents and don't allow creating new documents if:
    // * if there is a new included document
    // * if one of the included documents were not already saved
    {
        final LogicExpressionResult allowCreateNew = context.getIncludedDocuments().stream().map(includedDocument -> {
            if (includedDocument.isNew()) {
                return DISALLOW_AnotherNewDocumentAlreadyExists;
            } else if (!includedDocument.getSaveStatus().isSavedOrDeleted()) {
                return DISALLOW_UnsavedRowFound;
            } else {
                return null;
            }
        }).filter(result -> result != null).findFirst().orElse(null);
        if (allowCreateNew != null && allowCreateNew.isFalse()) {
            return allowCreateNew;
        }
    }
    // 
    // Evaluate the allowCreateNew logic expression
    final LogicExpressionResult allowCreateNew = allowCreateNewLogic.evaluateToResult(context.toEvaluatee(), OnVariableNotFound.ReturnNoResult);
    return allowCreateNew;
}


public IncludedDocumentsCollectionActions copy(){
    return new IncludedDocumentsCollectionActions(this);
}


public LogicExpressionResult getAllowCreateNewDocument(){
    return allowNew;
}


public LogicExpressionResult setAllowNewAndCollect(LogicExpressionResult allowNew,IncludedDocumentsCollectionActionsContext context){
    final LogicExpressionResult allowNewOld = this.allowNew;
    this.allowNew = allowNew;
    if (!allowNewOld.equalsByNameAndValue(allowNew)) {
        context.collectAllowNew(parentDocumentPath, detailId, allowNew);
    }
    return allowNew;
}


public LogicExpressionResult getAllowDeleteDocument(){
    return allowDelete;
}


public void onNewDocument(Document document,IncludedDocumentsCollectionActionsContext context){
    if (document.isNew()) {
        setAllowNewAndCollect(DISALLOW_AnotherNewDocumentAlreadyExists, context);
    }
}


}