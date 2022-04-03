package de.metas.ui.web.window.exceptions;
 import org.adempiere.exceptions.AdempiereException;
public class DocumentPermissionException extends AdempiereException{

 private  long serialVersionUID;


public DocumentPermissionException of(DocumentPermission permissionRequired,String errorMessage){
    return new DocumentPermissionException(permissionRequired, errorMessage);
}


}