package de.metas.ui.web.window.exceptions;
 import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import de.metas.ui.web.exceptions.EntityNotFoundException;
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DocumentLayoutDetailNotFoundException extends EntityNotFoundException{


}