package de.metas.ui.web.board;
 import java.util.Collection;
import org.adempiere.exceptions.AdempiereException;
import com.google.common.collect.ImmutableMap;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.LookupDescriptorProvider;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
@Builder
@Value
public class BoardDescriptor {

 private  int boardId;

@NonNull
 private  ITranslatableString caption;

@NonNull
 private  String websocketEndpoint;

@Singular
 private  ImmutableMap<Integer,BoardLaneDescriptor> lanes;

@Singular("cardFieldByFieldName")
 final  ImmutableMap<String,BoardCardFieldDescriptor> cardFieldsByFieldName;

@NonNull
 private  WindowId documentWindowId;

@NonNull
 private  LookupDescriptorProvider documentLookupDescriptorProvider;

@Default
 private  DocumentFilterList documentFilters;

@NonNull
 private  String tableName;

@NonNull
 private  String tableAlias;

@NonNull
 private  String keyColumnName;

@NonNull
 private  String userIdColumnName;


public BoardCardFieldDescriptor getCardFieldByName(String fieldName){
    final BoardCardFieldDescriptor cardField = cardFieldsByFieldName.get(fieldName);
    if (cardField == null) {
        throw new AdempiereException("No card field found for " + fieldName).setParameter("board", this);
    }
    return cardField;
}


public LookupDescriptor getLookupDescriptor(){
    return getDocumentLookupDescriptorProvider().provide().get();
}


public Collection<BoardCardFieldDescriptor> getCardFields(){
    return cardFieldsByFieldName.values();
}


public void assertLaneIdExists(int laneId){
    if (lanes.get(laneId) == null) {
        throw new AdempiereException("Lane ID=" + laneId + " found for board ID=" + getBoardId()).setParameter("board", this).setParameter("laneId", laneId);
    }
}


}