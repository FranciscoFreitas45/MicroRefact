package de.metas.ui.web.letter;
 import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class WebuiLetterChangeResult {

@NonNull
 private  WebuiLetter letter;

@NonNull
 private  WebuiLetter originalLetter;


}