package de.metas.ui.web.letter;
 import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.user.UserId;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NonNull;
import lombok.Value;
@Builder(toBuilder = true)
@Value
public class WebuiLetter {

@NonNull
 private  String letterId;

 private  UserId ownerUserId;

 private  boolean processed;

 private  byte[] temporaryPDFData;

@Default
 private  int textTemplateId;

 private  String content;

 private  String subject;

@NonNull
 private  DocumentPath contextDocumentPath;

 private  int adOrgId;

 private  int bpartnerId;

 private  int bpartnerLocationId;

 private  String bpartnerAddress;

 private  int bpartnerContactId;


}