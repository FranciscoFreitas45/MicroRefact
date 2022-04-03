package de.metas.ui.web.window.datatypes.json;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.i18n.IMsgBL;
import de.metas.util.Services;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.compiere.util.ValueNamePairValidationInformation;
import javax.annotation.Nullable;
@Value
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONLookupValueValidationInformation {

@JsonProperty("title")
 private  String title;

@JsonProperty("question")
 private  String question;

@JsonProperty("answerYes")
 private  String answerYes;

@JsonProperty("answerNo")
 private  String answerNo;


public JSONLookupValueValidationInformation ofNullable(ValueNamePairValidationInformation validationInformation,String adLanguage){
    if (validationInformation == null) {
        return null;
    }
    final IMsgBL msgBL = Services.get(IMsgBL.class);
    return JSONLookupValueValidationInformation.builder().title(msgBL.translate(adLanguage, validationInformation.getTitle().toAD_Message())).question(msgBL.translate(adLanguage, validationInformation.getQuestion().toAD_Message())).answerYes(msgBL.translate(adLanguage, validationInformation.getAnswerYes().toAD_Message())).answerNo(msgBL.translate(adLanguage, validationInformation.getAnswerNo().toAD_Message())).build();
}


}