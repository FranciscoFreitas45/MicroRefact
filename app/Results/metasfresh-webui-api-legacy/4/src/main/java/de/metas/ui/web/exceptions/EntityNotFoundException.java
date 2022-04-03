package de.metas.ui.web.exceptions;
 import org.adempiere.exceptions.AdempiereException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.NonNull;
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends AdempiereException{


public EntityNotFoundException wrapIfNeeded(Throwable throwable){
    if (throwable instanceof EntityNotFoundException) {
        return (EntityNotFoundException) throwable;
    }
    final Throwable cause = extractCause(throwable);
    if (cause != throwable) {
        return wrapIfNeeded(cause);
    }
    return new EntityNotFoundException(extractMessage(throwable), cause);
}


}