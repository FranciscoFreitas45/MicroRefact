package de.metas.ui.web.board.json;
 import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.metas.ui.web.document.filter.json.JSONDocumentFilterDescriptor;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Builder
@Value
public class JSONNewCardsViewLayout {

@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String caption;

@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String description;

@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String emptyResultText;

@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String emptyResultHint;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Singular
 private  List<JSONDocumentFilterDescriptor> filters;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Singular
 private  List<JSONBoardCardOrderBy> orderBys;


}