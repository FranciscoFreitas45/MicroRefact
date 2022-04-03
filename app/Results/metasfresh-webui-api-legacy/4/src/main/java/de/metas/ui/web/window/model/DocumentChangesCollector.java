package de.metas.ui.web.window.model;
 import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import com.google.common.base.MoreObjects;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.model.DocumentChanges.IncludedDetailInfo;
import lombok.NonNull;
public class DocumentChangesCollector implements IDocumentChangesCollector{

 private  Map<DocumentPath,DocumentChanges> documentChangesByPath;


@Override
public void collectValueIfChanged(IDocumentFieldView documentField,Object valueOld,ReasonSupplier reason){
    // If there is no change, don't collect the value
    final Object valueNew = documentField.getValue();
    if (DataTypes.equals(valueOld, valueNew)) {
        return;
    }
    documentChanges(documentField).collectValueChanged(documentField, reason);
}


public DocumentChanges documentChanges(DocumentPath documentPath){
    return documentChangesByPath.computeIfAbsent(documentPath, DocumentChanges::new);
}


public Optional<DocumentChanges> documentChangesIfExists(DocumentPath documentPath){
    return Optional.ofNullable(documentChangesByPath.get(documentPath));
}


@Override
public Set<String> collectFrom(Document document,ReasonSupplier reason){
    final DocumentPath documentPath = document.getDocumentPath();
    return documentChanges(documentPath).collectFrom(document, reason);
}


@Override
public void setPrimaryChange(DocumentPath documentPath){
    documentChanges(documentPath).setPrimaryChange();
}


public DocumentChangesCollector newInstance(){
    return new DocumentChangesCollector();
}


@Override
public Stream<DocumentChanges> streamOrderedDocumentChanges(){
    return documentChangesByPath.values().stream().filter(documentChanges -> !isStaleDocumentChanges(documentChanges)).sorted(// make sure primary changes are returned first (exacted by frontend)
    Comparator.comparing(documentChanges -> documentChanges.isPrimaryChange() ? 0 : 1));
}


@Override
public void collectLookupValuesStaled(IDocumentFieldView documentField,ReasonSupplier reason){
    documentChanges(documentField).collectLookupValuesStaled(documentField, reason);
}


public boolean isStaleDocumentChanges(DocumentChanges documentChanges){
    final DocumentPath documentPath = documentChanges.getDocumentPath();
    if (!documentPath.isSingleIncludedDocument()) {
        return false;
    }
    final DocumentPath rootDocumentPath = documentPath.getRootDocumentPath();
    final DetailId detailId = documentPath.getDetailId();
    return documentChangesIfExists(rootDocumentPath).flatMap(rootDocumentChanges -> rootDocumentChanges.includedDetailInfoIfExists(detailId)).map(IncludedDetailInfo::isStale).orElse(false);
}


@Override
public void collectAllowNew(DocumentPath rootDocumentPath,DetailId detailId,LogicExpressionResult allowNew){
    rootDocumentChanges(rootDocumentPath).includedDetailInfo(detailId).setAllowNew(allowNew);
}


@Override
public void collectDocumentValidStatusChanged(DocumentPath documentPath,DocumentValidStatus documentValidStatus){
    documentChanges(documentPath).collectDocumentValidStatusChanged(documentValidStatus);
}


@Override
public void collectValueChanged(IDocumentFieldView documentField,ReasonSupplier reason){
    documentChanges(documentField).collectValueChanged(documentField, reason);
}


@Override
public void collectDeleted(DocumentPath documentPath){
    documentChanges(documentPath).collectDeleted();
}


@Override
public void collectStaleDetailId(DocumentPath rootDocumentPath,DetailId detailId){
    rootDocumentChanges(rootDocumentPath).includedDetailInfo(detailId).setStale();
}


@Override
public void collectFieldWarning(IDocumentFieldView documentField,DocumentFieldWarning fieldWarning){
    documentChanges(documentField).collectFieldWarning(documentField, fieldWarning);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).addValue(documentChangesByPath).toString();
}


public DocumentChanges rootDocumentChanges(DocumentPath rootDocumentPath){
    Check.assume(rootDocumentPath.isRootDocument(), "{} is root document path", rootDocumentPath);
    return documentChanges(rootDocumentPath);
}


@Override
public void collectMandatoryIfChanged(IDocumentFieldView documentField,LogicExpressionResult valueOld,ReasonSupplier reason){
    // If there is no change, don't collect the value
    final LogicExpressionResult value = documentField.getMandatory();
    if (value.equalsByNameAndValue(valueOld)) {
        return;
    }
    final ReasonSupplier reasonNew = reason.add("mandatory", value);
    documentChanges(documentField).collectMandatoryChanged(documentField, reasonNew);
}


@Override
public void collectReadonlyIfChanged(IDocumentFieldView documentField,LogicExpressionResult valueOld,ReasonSupplier reason){
    // If there is no change, don't collect the value
    final LogicExpressionResult value = documentField.getReadonly();
    if (value.equalsByNameAndValue(valueOld)) {
        return;
    }
    final ReasonSupplier reasonNew = reason.add("readonly", value);
    documentChanges(documentField).collectReadonlyChanged(documentField, reasonNew);
}


@Override
public void collectAllowDelete(DocumentPath rootDocumentPath,DetailId detailId,LogicExpressionResult allowDelete){
    rootDocumentChanges(rootDocumentPath).includedDetailInfo(detailId).setAllowDelete(allowDelete);
}


@Override
public void collectEvent(IDocumentFieldChangedEvent event){
    documentChanges(event.getDocumentPath()).collectEvent(event);
}


@Override
public void collectDisplayedIfChanged(IDocumentFieldView documentField,LogicExpressionResult valueOld,ReasonSupplier reason){
    // If there is no change, don't collect the value
    final LogicExpressionResult value = documentField.getDisplayed();
    if (value.equalsByNameAndValue(valueOld)) {
        return;
    }
    final ReasonSupplier reasonNew = reason.add("displayed", value);
    documentChanges(documentField).collectDisplayedChanged(documentField, reasonNew);
}


@Override
public void collectDocumentSaveStatusChanged(DocumentPath documentPath,DocumentSaveStatus documentSaveStatus){
    documentChanges(documentPath).collectDocumentSaveStatusChanged(documentSaveStatus);
}


@Override
public void collectValidStatus(IDocumentFieldView documentField){
    documentChanges(documentField).collectValidStatusChanged(documentField);
}


}