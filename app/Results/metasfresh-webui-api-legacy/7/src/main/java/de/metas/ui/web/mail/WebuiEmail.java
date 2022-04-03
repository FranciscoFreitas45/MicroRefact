package de.metas.ui.web.mail;
 import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.user.UserId;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NonNull;
import lombok.Value;
@Builder(toBuilder = true)
@Value
public class WebuiEmail {

@NonNull
 private  String emailId;

 private  UserId ownerUserId;

 private  LookupValue from;

@Default
 private  LookupValuesList to;

 private  String subject;

 private  String message;

@Default
 private  LookupValuesList attachments;

 private  boolean sent;

 private  DocumentPath contextDocumentPath;


}