package de.metas.ui.web.window.exceptions;
 import org.adempiere.exceptions.AdempiereException;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.model.Document;
@SuppressWarnings("serial")
public class InvalidDocumentStateException extends AdempiereException{


public String buildMsg(DocumentPath documentPath,String reason){
    return "Document " + documentPath + " state is invalid: " + reason;
}


}