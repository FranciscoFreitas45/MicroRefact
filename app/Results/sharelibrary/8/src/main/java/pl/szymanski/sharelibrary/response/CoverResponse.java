package pl.szymanski.sharelibrary.response;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.szymanski.sharelibrary.entity.Cover;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoverResponse {

 private  Long id;

 private  String name;

 private  String type;

 private  byte[] data;


public CoverResponse of(Cover cover){
    return new CoverResponse(cover.getId(), cover.getName(), cover.getType(), cover.getData());
}


}