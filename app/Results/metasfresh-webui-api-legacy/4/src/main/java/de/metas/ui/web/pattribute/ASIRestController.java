package de.metas.ui.web.pattribute;
 import java.util.List;
import java.util.function.Function;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.mm.attributes.AttributeSetInstanceId;
import org.adempiere.mm.attributes.util.ASIEditingInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import de.metas.lang.SOTrx;
import de.metas.product.ProductId;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.pattribute.json.JSONASILayout;
import de.metas.ui.web.pattribute.json.JSONCreateASIRequest;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.controller.Execution;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocument;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
import de.metas.ui.web.window.datatypes.json.JSONDocumentOptions;
import de.metas.ui.web.window.datatypes.json.JSONDocumentPath;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.ui.web.window.model.NullDocumentChangesCollector;
import io.swagger.annotations.Api;
import lombok.NonNull;
@Api
@RestController
@RequestMapping(value = ASIRestController.ENDPOINT)
public class ASIRestController {

 public  String ENDPOINT;

 private  ReasonSupplier REASON_ProcessASIDocumentChanges;

 private  UserSession userSession;

 private  ASIRepository asiRepo;

 private  DocumentCollection documentsCollection;

 private  IViewsRepository viewsRepository;


public JSONLookupValuesList toJSONLookupValuesList(LookupValuesList lookupValuesList){
    return JSONLookupValuesList.ofLookupValuesList(lookupValuesList, userSession.getAD_Language());
}


public JSONDocumentOptions newJsonDocumentOpts(){
    return JSONDocumentOptions.of(userSession);
}


public WebuiASIEditingInfo createWebuiASIEditingInfo(JSONCreateASIRequest request){
    final AttributeSetInstanceId attributeSetInstanceId = request.getTemplateId();
    final JSONDocumentPath source = request.getSource();
    if (source.isWindow()) {
        return documentsCollection.forDocumentReadonly(source.toSingleDocumentPath(), contextDocument -> createWebuiASIEditingInfoForSingleDocument(contextDocument, attributeSetInstanceId));
    } else if (source.isView()) {
        return createWebuiASIEditingInfoForViewRow(attributeSetInstanceId, source.getViewId(), source.getRowId());
    } else if (source.isProcess()) {
        return WebuiASIEditingInfo.processParameterASI(attributeSetInstanceId, source.toSingleDocumentPath());
    } else {
        throw new AdempiereException("Cannot create ASI editing info from " + source);
    }
}


@PostMapping({ "", "/" })
public JSONDocument createASIDocument(JSONCreateASIRequest request){
    userSession.assertLoggedIn();
    final WebuiASIEditingInfo info = createWebuiASIEditingInfo(request);
    return Execution.callInNewExecution("createASI", () -> asiRepo.createNewFrom(info).toJSONDocument(newJsonDocumentOpts()));
}


@GetMapping("/{asiDocId}/field/{attributeName}/dropdown")
public JSONLookupValuesList getAttributeDropdown(String asiDocIdStr,String attributeName){
    userSession.assertLoggedIn();
    final DocumentId asiDocId = DocumentId.of(asiDocIdStr);
    return forASIDocumentReadonly(asiDocId, asiDoc -> asiDoc.getFieldLookupValues(attributeName)).transform(this::toJSONLookupValuesList);
}


public JSONDocumentLayoutOptions newJsonDocumentLayoutOpts(){
    return JSONDocumentLayoutOptions.of(userSession);
}


@PatchMapping("/{asiDocId}")
public List<JSONDocument> processChanges(String asiDocIdStr,List<JSONDocumentChangedEvent> events){
    userSession.assertLoggedIn();
    final DocumentId asiDocId = DocumentId.of(asiDocIdStr);
    return Execution.callInNewExecution("processChanges", () -> processASIDocumentChanges(asiDocId, events));
}


public JSONLookupValue toJSONLookupValue(LookupValue lookupValue){
    return JSONLookupValue.ofLookupValue(lookupValue, userSession.getAD_Language());
}


@GetMapping("/{asiDocId}/layout")
public JSONASILayout getLayout(String asiDocIdStr){
    userSession.assertLoggedIn();
    final DocumentId asiDocId = DocumentId.of(asiDocIdStr);
    final ASILayout asiLayout = forASIDocumentReadonly(asiDocId, ASIDocument::getLayout);
    return JSONASILayout.of(asiLayout, newJsonDocumentLayoutOpts());
}


public WebuiASIEditingInfo createWebuiASIEditingInfoForSingleDocument(Document contextDocument,AttributeSetInstanceId attributeSetInstanceId){
    final ProductId productId = ProductId.ofRepoIdOrNull(contextDocument.asEvaluatee().get_ValueAsInt("M_Product_ID", -1));
    final SOTrx soTrx = SOTrx.ofBoolean(contextDocument.asEvaluatee().get_ValueAsBoolean("IsSOTrx", true));
    final String callerTableName = contextDocument.getEntityDescriptor().getTableNameOrNull();
    // FIXME implement
    final int callerColumnId = -1;
    final ASIEditingInfo info = ASIEditingInfo.of(productId, attributeSetInstanceId, callerTableName, callerColumnId, soTrx);
    return WebuiASIEditingInfo.builder(info).contextDocumentPath(contextDocument.getDocumentPath()).build();
}


@GetMapping("/{asiDocId}/field/{attributeName}/typeahead")
public JSONLookupValuesList getAttributeTypeahead(String asiDocIdStr,String attributeName,String query){
    userSession.assertLoggedIn();
    final DocumentId asiDocId = DocumentId.of(asiDocIdStr);
    return forASIDocumentReadonly(asiDocId, asiDoc -> asiDoc.getFieldLookupValuesForQuery(attributeName, query)).transform(this::toJSONLookupValuesList);
}


@GetMapping("/{asiDocId}")
public JSONDocument getASIDocument(String asiDocIdStr){
    userSession.assertLoggedIn();
    final DocumentId asiDocId = DocumentId.of(asiDocIdStr);
    return forASIDocumentReadonly(asiDocId, asiDoc -> asiDoc.toJSONDocument(newJsonDocumentOpts()));
}


public List<JSONDocument> processASIDocumentChanges(DocumentId asiDocId,List<JSONDocumentChangedEvent> events){
    final IDocumentChangesCollector changesCollector = Execution.getCurrentDocumentChangesCollectorOrNull();
    asiRepo.forASIDocumentWritable(asiDocId, changesCollector, documentsCollection, asiDoc -> {
        asiDoc.processValueChanges(events, REASON_ProcessASIDocumentChanges);
        // no response
        return null;
    });
    return JSONDocument.ofEvents(changesCollector, newJsonDocumentOpts());
}


public WebuiASIEditingInfo createWebuiASIEditingInfoForViewRow(AttributeSetInstanceId attributeSetInstanceId,ViewId viewId,DocumentId rowId){
    final IView view = viewsRepository.getView(viewId);
    final IViewRow row = view.getById(rowId);
    if (row instanceof WebuiASIEditingInfoAware) {
        final WebuiASIEditingInfoAware infoAware = (WebuiASIEditingInfoAware) row;
        return infoAware.getWebuiASIEditingInfo(attributeSetInstanceId);
    } else {
        throw new AdempiereException("In order to edit ASI, let the row implement " + WebuiASIEditingInfoAware.class);
    }
}


public LookupValue complete(DocumentId asiDocId){
    return asiRepo.forASIDocumentWritable(asiDocId, NullDocumentChangesCollector.instance, documentsCollection, ASIDocument::complete);
}


public R forASIDocumentReadonly(DocumentId asiDocId,Function<ASIDocument,R> processor){
    return asiRepo.forASIDocumentReadonly(asiDocId, documentsCollection, processor);
}


}