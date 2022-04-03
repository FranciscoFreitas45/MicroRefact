package pl.szymanski.sharelibrary.response;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.Language;
@Data
@AllArgsConstructor
public class LanguageResponse {

 private  Integer id;

 private  String name;


public LanguageResponse of(Language language){
    return new LanguageResponse(language.getId(), language.getName());
}


}