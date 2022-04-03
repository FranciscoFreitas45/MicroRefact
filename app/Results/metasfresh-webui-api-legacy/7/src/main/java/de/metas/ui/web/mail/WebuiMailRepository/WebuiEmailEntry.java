package de.metas.ui.web.mail.WebuiMailRepository;
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
@ToString
public class WebuiEmailEntry {

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


public WebuiEmail getEmail(){
    return email;
}


}