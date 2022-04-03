package de.metas.ui.web.comments;
 import de.metas.ui.web.comments.json.JSONComment;
import de.metas.ui.web.comments.json.JSONCommentCreateRequest;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.controller.WindowRestController;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.ZoneId;
import java.util.List;
@RestController
@RequestMapping(CommentsRestController.ENDPOINT)
public class CommentsRestController {

 protected  String ENDPOINT;

 private  UserSession userSession;

 private  DocumentDescriptorFactory documentDescriptorFactory;

 private  CommentsService commentsService;


@GetMapping
public List<JSONComment> getAll(String windowIdStr,String documentId){
    userSession.assertLoggedIn();
    final DocumentPath documentPath = DocumentPath.rootDocumentPath(WindowId.fromJson(windowIdStr), documentId);
    final TableRecordReference tableRecordReference = documentDescriptorFactory.getTableRecordReference(documentPath);
    final ZoneId zoneId = JSONOptions.of(userSession).getZoneId();
    return commentsService.getCommentsFor(tableRecordReference, zoneId);
}


@PostMapping
public void addComment(String windowIdStr,String documentId,JSONCommentCreateRequest jsonCommentCreateRequest){
    userSession.assertLoggedIn();
    final DocumentPath documentPath = DocumentPath.rootDocumentPath(WindowId.fromJson(windowIdStr), documentId);
    final TableRecordReference tableRecordReference = documentDescriptorFactory.getTableRecordReference(documentPath);
    commentsService.addComment(tableRecordReference, jsonCommentCreateRequest);
}


}