package de.metas.ui.web.address;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import de.metas.ui.web.address.json.JSONAddressLayout;
import de.metas.ui.web.address.json.JSONCreateAddressRequest;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.controller.Execution;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocument;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
import de.metas.ui.web.window.datatypes.json.JSONDocumentOptions;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import io.swagger.annotations.Api;
@Api
@RestController
@RequestMapping(value = AddressRestController.ENDPOINT)
public class AddressRestController {

 public  String ENDPOINT;

@Autowired
 private  UserSession userSession;

@Autowired
 private AddressRepository addressRepo;


public JSONLookupValue toJSONLookupValue(LookupValue lookupValue){
    return JSONLookupValue.ofLookupValue(lookupValue, userSession.getAD_Language());
}


@RequestMapping(value = "/{docId}/layout", method = RequestMethod.GET)
public JSONAddressLayout getLayout(int docId_NOTUSED){
    userSession.assertLoggedIn();
    return JSONAddressLayout.of(addressRepo.getLayout(), newJsonDocumentLayoutOpts());
}


@RequestMapping(value = "/{docId}/field/{attributeName}/typeahead", method = RequestMethod.GET)
public JSONLookupValuesList getAttributeTypeahead(int docId,String attributeName,String query){
    userSession.assertLoggedIn();
    return addressRepo.getAddressDocumentForReading(docId).getFieldLookupValuesForQuery(attributeName, query).transform(this::toJSONLookupValuesList);
}


public JSONLookupValuesList toJSONLookupValuesList(LookupValuesList lookupValuesList){
    return JSONLookupValuesList.ofLookupValuesList(lookupValuesList, userSession.getAD_Language());
}


public JSONDocumentOptions newJsonDocumentOpts(){
    return JSONDocumentOptions.of(userSession);
}


@RequestMapping(value = "/{docId}", method = RequestMethod.GET)
public JSONDocument getAddressDocument(int docId){
    userSession.assertLoggedIn();
    final Document addressDoc = addressRepo.getAddressDocumentForReading(docId);
    return JSONDocument.ofDocument(addressDoc, newJsonDocumentOpts());
}


@RequestMapping(value = "/{docId}/field/{attributeName}/dropdown", method = RequestMethod.GET)
public JSONLookupValuesList getAttributeDropdown(int docId,String attributeName){
    userSession.assertLoggedIn();
    return addressRepo.getAddressDocumentForReading(docId).getFieldLookupValues(attributeName).transform(this::toJSONLookupValuesList);
}


public JSONDocumentLayoutOptions newJsonDocumentLayoutOpts(){
    return JSONDocumentLayoutOptions.of(userSession);
}


@PostMapping(value = "/{docId}/complete")
public JSONLookupValue complete(int docId){
    userSession.assertLoggedIn();
    return Execution.callInNewExecution("complete", () -> addressRepo.complete(docId).transform(this::toJSONLookupValue));
}


@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
public JSONDocument createAddressDocument(JSONCreateAddressRequest request){
    userSession.assertLoggedIn();
    return Execution.callInNewExecution("createAddressDocument", () -> {
        final Document addressDoc = addressRepo.createNewFrom(request.getTemplateId());
        return JSONDocument.ofDocument(addressDoc, newJsonDocumentOpts());
    });
}


@RequestMapping(value = "/{docId}", method = RequestMethod.PATCH)
public List<JSONDocument> processChanges(int docId,List<JSONDocumentChangedEvent> events){
    userSession.assertLoggedIn();
    return Execution.callInNewExecution("processChanges", () -> {
        final IDocumentChangesCollector changesCollector = Execution.getCurrentDocumentChangesCollectorOrNull();
        addressRepo.processAddressDocumentChanges(docId, events, changesCollector);
        return JSONDocument.ofEvents(changesCollector, newJsonDocumentOpts());
    });
}


}