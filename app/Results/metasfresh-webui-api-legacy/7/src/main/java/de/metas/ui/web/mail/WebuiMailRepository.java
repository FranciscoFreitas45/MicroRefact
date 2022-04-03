package de.metas.ui.web.mail;
 import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;
import org.compiere.util.DisplayType;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluatees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import de.metas.user.UserId;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;
@Component
public class WebuiMailRepository {

@Autowired
 private  ApplicationEventPublisher eventPublisher;

 private  AtomicInteger nextEmailId;

 private  Cache<String,WebuiEmailEntry> emailsById;

 private  LookupDataSource emailToLookup;

 private  WebuiEmail email;

@NonNull
 private  WebuiEmail email;


public WebuiEmailChangeResult compute(UnaryOperator<WebuiEmail> modifier){
    final WebuiEmail emailOld = email;
    final WebuiEmail emailNew = modifier.apply(emailOld);
    if (emailNew == null) {
        throw new NullPointerException("email");
    }
    email = emailNew;
    return WebuiEmailChangeResult.builder().email(emailNew).originalEmail(emailOld).build();
}


public WebuiEmailEntry getEmailEntry(String emailId){
    final WebuiEmailEntry emailEntry = emailsById.getIfPresent(emailId);
    if (emailEntry == null) {
        throw new EntityNotFoundException("Email not found").setParameter("emailId", emailId);
    }
    return emailEntry;
}


public WebuiEmail getEmail(){
    return email;
}


public void onEmailRemoved(WebuiEmail email){
    eventPublisher.publishEvent(new WebuiEmailRemovedEvent(email));
}


public WebuiEmail createNewEmail(UserId ownerUserId,LookupValue from,LookupValue to,DocumentPath contextDocumentPath){
    final String emailId = String.valueOf(nextEmailId.getAndIncrement());
    final LookupValuesList toList = LookupValuesList.fromNullable(to);
    final WebuiEmail email = WebuiEmail.builder().emailId(emailId).ownerUserId(ownerUserId).from(from).to(toList).contextDocumentPath(contextDocumentPath).build();
    emailsById.put(emailId, new WebuiEmailEntry(email));
    return email;
}


public LookupValuesList getToTypeahead(String emailId_NOTUSED,String query){
    // TODO
    final Evaluatee ctx = Evaluatees.empty();
    // TODO: filter only those which have a valid email address
    return emailToLookup.findEntities(ctx, query);
}


public WebuiEmailChangeResult changeEmail(String emailId,UnaryOperator<WebuiEmail> emailModifier){
    return getEmailEntry(emailId).compute(emailModifier);
}


public void removeEmailById(String emailId){
    emailsById.invalidate(emailId);
}


public LookupValue getToByUserId(Integer adUserId){
    return emailToLookup.findById(adUserId);
}


}