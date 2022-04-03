package de.metas.ui.web.letter;
 import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.UnaryOperator;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.apache.commons.io.FileUtils;
import org.compiere.util.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.ImmutableSet;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.BPartnerLocationId;
import de.metas.document.IDocumentLocationBL;
import de.metas.document.model.impl.PlainDocumentLocation;
import de.metas.i18n.IMsgBL;
import de.metas.letter.BoilerPlateId;
import de.metas.letters.api.ITextTemplateBL;
import de.metas.letters.model.Letter;
import de.metas.letters.model.Letters;
import de.metas.letters.model.MADBoilerPlate;
import de.metas.letters.model.MADBoilerPlate.BoilerPlateContext;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.letter.WebuiLetter.WebuiLetterBuilder;
import de.metas.ui.web.letter.json.JSONLetter;
import de.metas.ui.web.letter.json.JSONLetterRequest;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentPath;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.user.UserId;
import de.metas.util.Services;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping(LetterRestController.ENDPOINT)
@ApiModel("Letter endpoint")
public class LetterRestController {

 public  String ENDPOINT;

@Autowired
 private  UserSession userSession;

@Autowired
 private  WebuiLetterRepository lettersRepo;

@Autowired
 private  DocumentCollection documentCollection;

 private  String PATCH_FIELD_Message;

 private  String PATCH_FIELD_TemplateId;

 private  Set<String> PATCH_FIELD_ALL;


@GetMapping("/{letterId}/printPreview")
@ApiOperation("Returns letter's printable version (e.g. PDF)")
public ResponseEntity<byte[]> getLetterPrintPreview(String letterId){
    userSession.assertLoggedIn();
    // 
    // Get the letter
    final WebuiLetter letter = lettersRepo.getLetter(letterId);
    assertReadable(letter);
    // 
    // Create and return the printable letter
    final byte[] pdfData = createPDFData(letter);
    return createPDFResponseEntry(pdfData);
}


public ResponseEntity<byte[]> createPDFResponseEntry(byte[] pdfData){
    final String pdfFilename = Services.get(IMsgBL.class).getMsg(Env.getCtx(), Letters.MSG_Letter);
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + pdfFilename + "\"");
    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    final ResponseEntity<byte[]> response = new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    return response;
}


public byte[] createPDFData(WebuiLetter letter){
    final Letter request = Letter.builder().adLanguage(userSession.getAD_Language()).boilerPlateId(BoilerPlateId.ofRepoIdOrNull(letter.getTextTemplateId())).subject(letter.getSubject()).body(letter.getContent()).adOrgId(letter.getAdOrgId()).bpartnerId(BPartnerId.ofRepoId(letter.getBpartnerId())).bpartnerLocationId(BPartnerLocationId.ofRepoId(BPartnerId.ofRepoId(letter.getBpartnerId()), letter.getBpartnerLocationId())).address(letter.getBpartnerAddress()).userId(UserId.ofRepoIdOrNull(letter.getBpartnerContactId())).build();
    return Services.get(ITextTemplateBL.class).createPDF(request);
}


public void assertWritable(WebuiLetter letter){
    assertReadable(letter);
    // Make sure the letter was not already processed
    if (letter.isProcessed()) {
        throw new AdempiereException("Cannot change an letter which was already processed").setParameter("letterId", letter.getLetterId());
    }
}


public void changeLetter(WebuiLetter letter,WebuiLetter.WebuiLetterBuilder newLetterBuilder,JSONDocumentChangedEvent event){
    if (!event.isReplace()) {
        throw new AdempiereException("Unsupported event").setParameter("event", event);
    }
    final String fieldName = event.getPath();
    if (PATCH_FIELD_Message.equals(fieldName)) {
        final String message = event.getValueAsString(null);
        newLetterBuilder.content(message);
    } else if (PATCH_FIELD_TemplateId.equals(fieldName)) {
        @SuppressWarnings("unchecked")
        final LookupValue templateId = JSONLookupValue.integerLookupValueFromJsonMap((Map<String, Object>) event.getValue());
        applyTemplate(letter, newLetterBuilder, templateId);
    } else {
        throw new AdempiereException("Unsupported event path").setParameter("event", event).setParameter("fieldName", fieldName).setParameter("availablePaths", PATCH_FIELD_ALL);
    }
}


public File createFile(byte[] pdfData){
    final String pdfFilenamePrefix = Services.get(IMsgBL.class).getMsg(Env.getCtx(), Letters.MSG_Letter);
    try {
        final File pdfFile = File.createTempFile(pdfFilenamePrefix, ".pdf");
        FileUtils.writeByteArrayToFile(pdfFile, pdfData);
        return pdfFile;
    } catch (IOException e) {
        throw AdempiereException.wrapIfNeeded(e);
    }
}


@PostMapping
@ApiOperation("Creates a new letter")
public JSONLetter createNewLetter(JSONLetterRequest request){
    userSession.assertLoggedIn();
    final DocumentPath contextDocumentPath = JSONDocumentPath.toDocumentPathOrNull(request.getDocumentPath());
    // 
    // Extract context BPartner, Location and Contact
    final BoilerPlateContext context = documentCollection.createBoilerPlateContext(contextDocumentPath);
    final BPartnerId bpartnerId = BPartnerId.ofRepoIdOrNull(context.getC_BPartner_ID(-1));
    final BPartnerLocationId bpartnerLocationId = BPartnerLocationId.ofRepoIdOrNull(bpartnerId, context.getC_BPartner_Location_ID(-1));
    final UserId contactId = UserId.ofRepoIdOrNull(context.getAD_User_ID(-1));
    // 
    // Build BPartnerAddress
    final PlainDocumentLocation documentLocation = PlainDocumentLocation.builder().bpartnerId(bpartnerId).bpartnerLocationId(bpartnerLocationId).contactId(contactId).build();
    Services.get(IDocumentLocationBL.class).setBPartnerAddress(documentLocation);
    final String bpartnerAddress = documentLocation.getBPartnerAddress();
    final WebuiLetter letter = lettersRepo.createNewLetter(WebuiLetter.builder().contextDocumentPath(contextDocumentPath).ownerUserId(userSession.getLoggedUserId()).adOrgId(context.getAD_Org_ID(userSession.getOrgId().getRepoId())).bpartnerId(BPartnerId.toRepoId(bpartnerId)).bpartnerLocationId(BPartnerLocationId.toRepoId(bpartnerLocationId)).bpartnerContactId(UserId.toRepoId(contactId)).bpartnerAddress(bpartnerAddress));
    return JSONLetter.of(letter);
}


@GetMapping("/templates")
@ApiOperation("Available Email templates")
public JSONLookupValuesList getTemplates(){
    return MADBoilerPlate.getAll(Env.getCtx()).stream().map(adBoilerPlate -> JSONLookupValue.of(adBoilerPlate.getAD_BoilerPlate_ID(), adBoilerPlate.getName())).collect(JSONLookupValuesList.collect());
}


@GetMapping("/{letterId}")
@ApiOperation("Gets letter by ID")
public JSONLetter getLetter(String letterId){
    userSession.assertLoggedIn();
    final WebuiLetter letter = lettersRepo.getLetter(letterId);
    assertReadable(letter);
    return JSONLetter.of(letter);
}


public WebuiLetter complete0(WebuiLetter letter){
    lettersRepo.createC_Letter(letter);
    // 
    // Create the printable letter
    final byte[] pdfData = createPDFData(letter);
    final File pdfFile = createFile(pdfData);
    // 
    // create the Boilerplate context
    final BoilerPlateContext context = documentCollection.createBoilerPlateContext(letter.getContextDocumentPath());
    // 
    // Create the request
    final TableRecordReference recordRef = documentCollection.getTableRecordReference(letter.getContextDocumentPath());
    MADBoilerPlate.createRequest(pdfFile, recordRef.getAD_Table_ID(), recordRef.getRecord_ID(), context);
    return letter.toBuilder().processed(true).temporaryPDFData(pdfData).build();
}


public void assertReadable(WebuiLetter letter){
    // Make sure current logged in user is the owner
    final UserId loggedUserId = userSession.getLoggedUserId();
    if (!UserId.equals(loggedUserId, letter.getOwnerUserId())) {
        throw new AdempiereException("No credentials to read the letter").setParameter("letterId", letter.getLetterId()).setParameter("ownerUserId", letter.getOwnerUserId()).setParameter("loggedUserId", loggedUserId);
    }
}


@PostMapping("/{letterId}/complete")
@ApiOperation("Completes the letter and returns it's printable version (e.g. PDF)")
public ResponseEntity<byte[]> complete(String letterId){
    userSession.assertLoggedIn();
    final ITrxManager trxManager = Services.get(ITrxManager.class);
    final WebuiLetterChangeResult result = changeLetter(letterId, letter -> trxManager.callInNewTrx(() -> complete0(letter)));
    // 
    // Remove the letter
    lettersRepo.removeLetterById(letterId);
    // 
    // Return the printable letter
    return createPDFResponseEntry(result.getLetter().getTemporaryPDFData());
}


public void applyTemplate(WebuiLetter letter,WebuiLetterBuilder newLetterBuilder,LookupValue templateLookupValue){
    final Properties ctx = Env.getCtx();
    final int textTemplateId = templateLookupValue.getIdAsInt();
    final MADBoilerPlate boilerPlate = MADBoilerPlate.get(ctx, textTemplateId);
    // 
    // Attributes
    final BoilerPlateContext context = documentCollection.createBoilerPlateContext(letter.getContextDocumentPath());
    // 
    // Content and subject
    newLetterBuilder.textTemplateId(textTemplateId);
    newLetterBuilder.content(boilerPlate.getTextSnippetParsed(context));
    newLetterBuilder.subject(boilerPlate.getSubject());
}


}