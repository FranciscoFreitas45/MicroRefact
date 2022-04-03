package de.metas.ui.web.window.model.DocumentCollection;
 import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.AdMessageKey;
import de.metas.letters.model.MADBoilerPlate;
import de.metas.letters.model.MADBoilerPlate.BoilerPlateContext;
import de.metas.letters.model.MADBoilerPlate.SourceDocument;
import de.metas.logging.LogManager;
import de.metas.process.AdProcessId;
import de.metas.process.ProcessExecutionResult;
import de.metas.process.ProcessInfo;
import de.metas.report.server.OutputType;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.controller.DocumentPermissionsHelper;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentDescriptor;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import de.metas.ui.web.window.events.DocumentWebsocketPublisher;
import de.metas.ui.web.window.exceptions.DocumentNotFoundException;
import de.metas.ui.web.window.exceptions.InvalidDocumentPathException;
import de.metas.ui.web.window.invalidation.DocumentToInvalidate;
import de.metas.ui.web.window.invalidation.IncludedDocumentToInvalidate;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.lookup.DocumentZoomIntoInfo;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.ad.expression.api.IExpressionEvaluator.OnVariableNotFound;
import org.adempiere.ad.expression.api.ILogicExpression;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import org.adempiere.ad.persistence.TableModelLoader;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.CopyRecordFactory;
import org.adempiere.model.CopyRecordSupport;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.model.PlainContextAware;
import org.adempiere.model.RecordZoomWindowFinder;
import org.adempiere.service.ISysConfigBL;
import org.adempiere.util.lang.IAutoCloseable;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluatees;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
@Immutable
public class DocumentKey {

 private  DocumentType documentType;

 private  DocumentId documentTypeId;

 private  DocumentId documentId;

 private  Integer _hashcode;


public DocumentId getDocumentId(){
    return documentId;
}


@Override
public int hashCode(){
    if (_hashcode == null) {
        _hashcode = Objects.hash(documentType, documentTypeId, documentId);
    }
    return _hashcode;
}


public DocumentKey of(WindowId windowId,DocumentId documentId){
    return new DocumentKey(DocumentType.Window, windowId.toDocumentId(), documentId);
}


public DocumentKey ofRootDocumentPath(DocumentPath documentPath){
    if (!documentPath.isRootDocument()) {
        throw new InvalidDocumentPathException(documentPath, "shall be a root document path");
    }
    if (documentPath.isNewDocument()) {
        throw new InvalidDocumentPathException(documentPath, "document path for creating new documents is not allowed");
    }
    return new DocumentKey(documentPath.getDocumentType(), documentPath.getDocumentTypeId(), documentPath.getDocumentId());
}


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (!(obj instanceof DocumentKey)) {
        return false;
    }
    final DocumentKey other = (DocumentKey) obj;
    return Objects.equals(documentType, other.documentType) && Objects.equals(documentTypeId, other.documentTypeId) && Objects.equals(documentId, other.documentId);
}


public DocumentPath getDocumentPath(){
    return DocumentPath.rootDocumentPath(documentType, documentTypeId, documentId);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("type", documentType).add("typeId", documentTypeId).add("documentId", documentId).toString();
}


public WindowId getWindowId(){
    Check.assume(documentType == DocumentType.Window, "documentType shall be {} but it was {}", DocumentType.Window, documentType);
    return WindowId.of(documentTypeId);
}


}