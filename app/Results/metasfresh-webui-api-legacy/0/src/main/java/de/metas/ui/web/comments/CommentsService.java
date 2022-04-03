package de.metas.ui.web.comments;
 import de.metas.comments.CommentEntry;
import de.metas.comments.CommentEntryRepository;
import de.metas.ui.web.comments.json.JSONComment;
import de.metas.ui.web.comments.json.JSONCommentCreateRequest;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.user.api.IUserDAO;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
@Service
public class CommentsService {

 private  CommentEntryRepository commentEntryRepository;

 final  IUserDAO userDAO;


@NonNull
public JSONComment toJsonComment(CommentEntry comment,ZoneId zoneId){
    final String text = comment.getText();
    final String created = DateTimeConverters.toJson(comment.getCreated(), zoneId);
    final String createdBy = userDAO.retrieveUserFullname(comment.getCreatedBy());
    return JSONComment.builder().text(text).created(created).createdBy(createdBy).build();
}


@NonNull
public List<JSONComment> getCommentsFor(TableRecordReference tableRecordReference,ZoneId zoneId){
    final List<CommentEntry> comments = commentEntryRepository.retrieveLastCommentEntries(tableRecordReference, 100);
    return comments.stream().map(comment -> toJsonComment(comment, zoneId)).sorted(Comparator.comparing(JSONComment::getCreated).reversed()).collect(GuavaCollectors.toImmutableList());
}


public void addComment(TableRecordReference tableRecordReference,JSONCommentCreateRequest jsonCommentCreateRequest){
    commentEntryRepository.createCommentEntry(jsonCommentCreateRequest.getText(), tableRecordReference);
}


}