package de.metas.ui.web.board.json;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.board.BoardCard;
import de.metas.ui.web.view.json.JSONViewRowBase;
import de.metas.ui.web.window.datatypes.json.JSONDocumentPath;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Builder
@Value
public class JSONBoardCard implements JSONViewRowBase{

 private  int cardId;

 private  int laneId;

 private  String caption;

 private  String description;

 private  JSONDocumentPath documentPath;

@Singular
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  ImmutableList<JSONBoardCardUser> users;


public JSONBoardCard of(BoardCard card,String adLanguage){
    final JSONBoardCardBuilder jsonCard = JSONBoardCard.builder().cardId(card.getCardId()).laneId(card.getLaneId()).caption(card.getCaption().translate(adLanguage)).description(card.getDescription().translate(adLanguage)).documentPath(JSONDocumentPath.ofWindowDocumentPath(card.getDocumentPath()));
    // Users
    card.getUsers().stream().map(JSONBoardCardUser::of).forEach(jsonCard::user);
    return jsonCard.build();
}


}