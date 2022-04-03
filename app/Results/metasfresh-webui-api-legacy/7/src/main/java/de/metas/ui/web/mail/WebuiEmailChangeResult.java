package de.metas.ui.web.mail;
 import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class WebuiEmailChangeResult {

@NonNull
 private  WebuiEmail email;

@NonNull
 private  WebuiEmail originalEmail;


}