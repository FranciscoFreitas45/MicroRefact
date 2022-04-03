package de.metas.ui.web.window.model.HighVolumeReadWriteIncludedDocumentsCollection;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import org.compiere.util.Evaluatee;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.exceptions.DocumentNotFoundException;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.Document.OnValidStatusChanged;
import lombok.AllArgsConstructor;
import lombok.NonNull;
@AllArgsConstructor
public class ActionsContext implements IncludedDocumentsCollectionActionsContext{


@Override
public boolean isParentDocumentNew(){
    return parentDocument.isNew();
}


@Override
public boolean isParentDocumentActive(){
    return parentDocument.isActive();
}


@Override
public boolean isParentDocumentInvalid(){
    return !parentDocument.getValidStatus().isValid();
}


@Override
public boolean isParentDocumentProcessed(){
    return parentDocument.isProcessed();
}


@Override
public void collectAllowDelete(DocumentPath parentDocumentPath,DetailId detailId,LogicExpressionResult allowDelete){
    parentDocument.getChangesCollector().collectAllowDelete(parentDocumentPath, detailId, allowDelete);
}


@Override
public Evaluatee toEvaluatee(){
    return parentDocument.asEvaluatee();
}


@Override
public void collectAllowNew(DocumentPath parentDocumentPath,DetailId detailId,LogicExpressionResult allowNew){
    parentDocument.getChangesCollector().collectAllowNew(parentDocumentPath, detailId, allowNew);
}


@Override
public Collection<Document> getIncludedDocuments(){
    return getChangedDocuments();
}


}