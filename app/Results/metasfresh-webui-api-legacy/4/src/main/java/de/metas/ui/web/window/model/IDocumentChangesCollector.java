package de.metas.ui.web.window.model;
 import java.util.Set;
import java.util.stream.Stream;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DetailId;
public interface IDocumentChangesCollector {

 public  ReasonSupplier NONE;


public void collectValueIfChanged(IDocumentFieldView documentField,Object valueOld,ReasonSupplier reason)
;

public ReasonSupplier add(String name,Object value){
    return () -> this.get() + " | " + name + "=" + value;
}
;

public Set<String> collectFrom(Document document,ReasonSupplier reason)
;

public void setPrimaryChange(DocumentPath documentPath)
;

public Stream<DocumentChanges> streamOrderedDocumentChanges()
;

public void collectLookupValuesStaled(IDocumentFieldView documentField,ReasonSupplier reason)
;

public void collectAllowNew(DocumentPath rootDocumentPath,DetailId detailId,LogicExpressionResult allowNew)
;

public void collectDocumentValidStatusChanged(DocumentPath documentPath,DocumentValidStatus documentValidStatus)
;

public void collectValueChanged(IDocumentFieldView documentField,ReasonSupplier reason)
;

public void collectDeleted(DocumentPath documentPath)
;

public void collectFieldWarning(IDocumentFieldView documentField,DocumentFieldWarning fieldWarning)
;

public void collectStaleDetailId(DocumentPath rootDocumentPath,DetailId detailId)
;

public String get()
;

public void collectMandatoryIfChanged(IDocumentFieldView documentField,LogicExpressionResult valueOld,ReasonSupplier reason)
;

public void collectReadonlyIfChanged(IDocumentFieldView documentField,LogicExpressionResult valueOld,ReasonSupplier reason)
;

public void collectAllowDelete(DocumentPath rootDocumentPath,DetailId detailId,LogicExpressionResult allowDelete)
;

public void collectEvent(IDocumentFieldChangedEvent event)
;

public ReasonSupplier addPreviousReason(ReasonSupplier previousReason,Object previousValue){
    if (previousReason == null && previousValue == null) {
        return this;
    }
    return () -> {
        final String reason = this.get();
        final StringBuilder reasonNew = new StringBuilder();
        reasonNew.append(reason == null ? "unknown reason" : reason);
        if (previousReason != null) {
            reasonNew.append(" | previous reason: ").append(previousReason);
        }
        if (previousValue != null) {
            reasonNew.append(" | previous value: ").append(previousValue);
        }
        return reasonNew.toString();
    };
}
;

public void collectDisplayedIfChanged(IDocumentFieldView documentField,LogicExpressionResult valueOld,ReasonSupplier reason)
;

public void collectDocumentSaveStatusChanged(DocumentPath documentPath,DocumentSaveStatus documentSaveStatus)
;

public void collectValidStatus(IDocumentFieldView documentField)
;

public String toDebugString(ReasonSupplier reasonSupplier){
    if (reasonSupplier == null) {
        return null;
    }
    // Extract the reason only if debugging is enabled
    if (!WindowConstants.isProtocolDebugging()) {
        return null;
    }
    return reasonSupplier.get();
}
;

}