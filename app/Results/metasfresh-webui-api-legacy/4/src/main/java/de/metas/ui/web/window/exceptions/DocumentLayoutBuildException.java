package de.metas.ui.web.window.exceptions;
 import org.adempiere.exceptions.AdempiereException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DocumentLayoutBuildException extends AdempiereException{


public DocumentLayoutBuildException wrapIfNeeded(Throwable throwable){
    if (throwable == null) {
        return null;
    } else if (throwable instanceof DocumentLayoutBuildException) {
        return (DocumentLayoutBuildException) throwable;
    }
    final Throwable cause = extractCause(throwable);
    if (cause != throwable) {
        return wrapIfNeeded(cause);
    }
    // default
    return new DocumentLayoutBuildException(cause.getLocalizedMessage(), cause);
}


}