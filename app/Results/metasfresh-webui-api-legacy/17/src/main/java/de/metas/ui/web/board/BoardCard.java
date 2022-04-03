package de.metas.ui.web.board;
 import com.google.common.collect.ImmutableList;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.window.datatypes.DocumentPath;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
@Builder
@Value
public class BoardCard {

 private  int cardId;

 private  int laneId;

@NonNull
 private  ITranslatableString caption;

@NonNull
 private  ITranslatableString description;

@NonNull
 private  DocumentPath documentPath;

@Singular
 private  ImmutableList<BoardCardUser> users;


}