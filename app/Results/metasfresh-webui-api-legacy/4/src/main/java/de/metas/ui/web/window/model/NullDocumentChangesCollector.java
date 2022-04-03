package de.metas.ui.web.window.model;
 import java.util.Set;
import java.util.stream.Stream;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DetailId;
public class NullDocumentChangesCollector implements IDocumentChangesCollector{

 public  NullDocumentChangesCollector instance;


@Override
public void collectValueIfChanged(IDocumentFieldView documentField,Object valueOld,ReasonSupplier reason){
// do nothing
}


@Override
public Set<String> collectFrom(Document document,ReasonSupplier reason){
    // nothing collected
    return ImmutableSet.of();
}


@Override
public void setPrimaryChange(DocumentPath documentPath){
// do nothing
}


@Override
public Stream<DocumentChanges> streamOrderedDocumentChanges(){
    return Stream.empty();
}


@Override
public void collectLookupValuesStaled(IDocumentFieldView documentField,ReasonSupplier reason){
// do nothing
}


@Override
public void collectAllowNew(DocumentPath rootDocumentPath,DetailId detailId,LogicExpressionResult allowNew){
// do nothing
}


@Override
public void collectDocumentValidStatusChanged(DocumentPath documentPath,DocumentValidStatus documentValidStatus){
// do nothing
}


@Override
public void collectValueChanged(IDocumentFieldView documentField,ReasonSupplier reason){
// do nothing
}


@Override
public void collectDeleted(DocumentPath documentPath){
// do nothing
}


@Override
public void collectFieldWarning(IDocumentFieldView documentField,DocumentFieldWarning fieldWarning){
// do nothing
}


@Override
public void collectStaleDetailId(DocumentPath rootDocumentPath,DetailId detailId){
// do nothing
}


public boolean isNull(IDocumentChangesCollector changesCollector){
    return changesCollector == null || changesCollector instanceof NullDocumentChangesCollector;
}


@Override
public void collectMandatoryIfChanged(IDocumentFieldView documentField,LogicExpressionResult valueOld,ReasonSupplier reason){
// do nothing
}


@Override
public void collectReadonlyIfChanged(IDocumentFieldView documentField,LogicExpressionResult valueOld,ReasonSupplier reason){
// do nothing
}


@Override
public void collectAllowDelete(DocumentPath rootDocumentPath,DetailId detailId,LogicExpressionResult allowDelete){
// do nothing
}


@Override
public void collectEvent(IDocumentFieldChangedEvent event){
// do nothing
}


@Override
public void collectDisplayedIfChanged(IDocumentFieldView documentField,LogicExpressionResult valueOld,ReasonSupplier reason){
// do nothing
}


@Override
public void collectDocumentSaveStatusChanged(DocumentPath documentPath,DocumentSaveStatus documentSaveStatus){
// do nothing
}


@Override
public void collectValidStatus(IDocumentFieldView documentField){
// do nothing
}


}