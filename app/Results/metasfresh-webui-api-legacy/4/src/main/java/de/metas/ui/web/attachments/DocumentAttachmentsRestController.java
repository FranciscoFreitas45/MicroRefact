package de.metas.ui.web.attachments;
 import com.google.common.collect.ImmutableList;
import de.metas.attachments.AttachmentEntry;
import de.metas.attachments.AttachmentEntryService;
import de.metas.attachments.listener.TableAttachmentListenerService;
import de.metas.security.IUserRolePermissions;
import de.metas.ui.web.attachments.json.JSONAttachURLRequest;
import de.metas.ui.web.attachments.json.JSONAttachment;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.controller.WindowRestController;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import de.metas.ui.web.window.events.DocumentWebsocketPublisher;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping(value = DocumentAttachmentsRestController.ENDPOINT)
public class DocumentAttachmentsRestController {

 public  String ENDPOINT;

 private  UserSession userSession;

 private  DocumentDescriptorFactory documentDescriptorFactory;

 private  DocumentWebsocketPublisher websocketPublisher;

 private  AttachmentEntryService attachmentEntryService;

 private  TableAttachmentListenerService tableAttachmentListenerService;


@GetMapping
public List<JSONAttachment> getAttachments(String windowIdStr,String documentId){
    userSession.assertLoggedIn();
    final DocumentPath documentPath = DocumentPath.rootDocumentPath(WindowId.fromJson(windowIdStr), documentId);
    if (documentPath.isComposedKey()) {
        // document with composed keys does not support attachments
        return ImmutableList.of();
    }
    final boolean allowDelete = isAllowDeletingAttachments();
    final List<JSONAttachment> attachments = getDocumentAttachments(documentPath).toJson();
    attachments.forEach(attachment -> attachment.setAllowDelete(allowDelete));
    return attachments;
}


@DeleteMapping("/{id}")
public void deleteAttachmentById(String windowIdStr,String documentId,String entryIdStr){
    userSession.assertLoggedIn();
    if (!isAllowDeletingAttachments()) {
        throw new AdempiereException("Delete not allowed");
    }
    final DocumentId entryId = DocumentId.of(entryIdStr);
    getDocumentAttachments(windowIdStr, documentId).deleteEntry(entryId);
}


public ResponseEntity<byte[]> extractResponseEntryFromURL(IDocumentAttachmentEntry entry){
    final HttpHeaders headers = new HttpHeaders();
    // forward to attachment entry's URL
    headers.setLocation(entry.getUrl());
    final ResponseEntity<byte[]> response = new ResponseEntity<>(new byte[] {}, headers, HttpStatus.FOUND);
    return response;
}


@PostMapping
public void attachFile(String windowIdStr,String documentId,MultipartFile file){
    userSession.assertLoggedIn();
    getDocumentAttachments(windowIdStr, documentId).addEntry(file);
}


public boolean isAllowDeletingAttachments(){
    return userSession.getUserRolePermissions().hasPermission(IUserRolePermissions.PERMISSION_IsAttachmentDeletionAllowed);
}


public ResponseEntity<byte[]> extractResponseEntryFromData(IDocumentAttachmentEntry entry){
    final String entryFilename = entry.getFilename();
    final byte[] entryData = entry.getData();
    if (entryData == null || entryData.length == 0) {
        throw new EntityNotFoundException("No attachment found").setParameter("entry", entry).setParameter("reason", "data is null or empty");
    }
    final String entryContentType = entry.getContentType();
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType(entryContentType));
    headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + entryFilename + "\"");
    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    final ResponseEntity<byte[]> response = new ResponseEntity<>(entryData, headers, HttpStatus.OK);
    return response;
}


@PostMapping("/addUrl")
public void attachURL(String windowIdStr,String documentId,JSONAttachURLRequest request){
    userSession.assertLoggedIn();
    getDocumentAttachments(windowIdStr, documentId).addURLEntry(request.getName(), request.getUri());
}


@GetMapping("/{id}")
public ResponseEntity<byte[]> getAttachmentById(String windowIdStr,String documentId,String entryIdStr){
    userSession.assertLoggedIn();
    final DocumentId entryId = DocumentId.of(entryIdStr);
    final IDocumentAttachmentEntry entry = getDocumentAttachments(windowIdStr, documentId).getEntry(entryId);
    final AttachmentEntry.Type type = entry.getType();
    if (type == AttachmentEntry.Type.Data) {
        return extractResponseEntryFromData(entry);
    } else if (type == AttachmentEntry.Type.URL) {
        return extractResponseEntryFromURL(entry);
    } else {
        throw new AdempiereException("Invalid attachment entry").setParameter("reason", "invalid type").setParameter("type", type).setParameter("entry", entry);
    }
}


public DocumentAttachments getDocumentAttachments(DocumentPath documentPath){
    if (documentPath.isComposedKey()) {
        throw new AdempiereException("Document does not support attachments").setParameter("technicalReason", "documents with composed keys are not handled");
    }
    final TableRecordReference recordRef = documentDescriptorFactory.getTableRecordReference(documentPath);
    return DocumentAttachments.builder().documentPath(documentPath).recordRef(recordRef).entityDescriptor(documentDescriptorFactory.getDocumentEntityDescriptor(documentPath)).websocketPublisher(websocketPublisher).tableAttachmentListenerService(tableAttachmentListenerService).attachmentEntryService(attachmentEntryService).build();
}


}