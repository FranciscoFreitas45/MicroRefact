package de.metas.ui.web.board;
 import lombok.Builder;
import lombok.Value;
@Builder
@Value
public class BoardCardUser {

 private  int userId;

 private  String avatarId;

 private  String fullname;


}