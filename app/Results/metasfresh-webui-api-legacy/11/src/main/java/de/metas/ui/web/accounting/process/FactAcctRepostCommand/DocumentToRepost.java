package de.metas.ui.web.accounting.process.FactAcctRepostCommand;
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
@lombok.Value
@lombok.Builder
public class DocumentToRepost {

 private int adTableId;

 private int recordId;

 private ClientId clientId;


public TableRecordReference getRecordRef(){
    return TableRecordReference.of(getAdTableId(), getRecordId());
}


}