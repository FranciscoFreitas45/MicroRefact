package de.metas.ui.web.accounting.process;
 import java.util.Set;
import org.adempiere.service.ClientId;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.util.Env;
import com.google.common.collect.ImmutableSet;
import de.metas.acct.api.IPostingRequestBuilder.PostImmediate;
import de.metas.acct.api.IPostingService;
import de.metas.user.UserId;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
public class FactAcctRepostCommand {

 private  IPostingService postingService;

 private  UserId loggedUserId;

 private  boolean forcePosting;

 private  ImmutableSet<DocumentToRepost> documentsToRepost;

 public  String TABLENAME_RV_UnPosted;

 private int adTableId;

 private int recordId;

 private ClientId clientId;


public TableRecordReference getRecordRef(){
    return TableRecordReference.of(getAdTableId(), getRecordId());
}


public void execute(){
    documentsToRepost.forEach(this::repost);
}


public void repost(DocumentToRepost doc){
    postingService.newPostingRequest().setClientId(doc.getClientId()).setDocumentRef(doc.getRecordRef()).setForce(forcePosting).setPostImmediate(PostImmediate.Yes).setFailOnError(true).onErrorNotifyUser(loggedUserId).postIt();
}


}