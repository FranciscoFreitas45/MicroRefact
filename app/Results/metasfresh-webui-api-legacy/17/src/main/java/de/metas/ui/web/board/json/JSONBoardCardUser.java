package de.metas.ui.web.board.json;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import de.metas.ui.web.board.BoardCardUser;
import lombok.Builder;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Builder
@Value
public class JSONBoardCardUser {

 private  int userId;

 private  String avatarId;

 private  String fullname;


public JSONBoardCardUser of(BoardCardUser user){
    return JSONBoardCardUser.builder().userId(user.getUserId()).avatarId(user.getAvatarId()).fullname(user.getFullname()).build();
}


}