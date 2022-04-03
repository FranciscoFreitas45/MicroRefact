package de.metas.ui.web.letter.json;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import de.metas.ui.web.letter.WebuiLetter;
import lombok.Builder;
import lombok.Getter;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Getter
public class JSONLetter {

 private  String letterId;

 private  String message;


public JSONLetter of(WebuiLetter letter){
    return builder().letterId(letter.getLetterId()).message(letter.getContent()).build();
}


}