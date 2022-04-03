package DTO;
 import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.model.DocumentCollection;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
public interface IEditableView extends IView{

 private  DocumentId rowId;

 private  DocumentCollection documentsCollection;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public LookupValuesList getFieldTypeahead(RowEditingContext ctx,String fieldName,String query)
;

public LookupValuesList getFieldDropdown(RowEditingContext ctx,String fieldName)
;

public IEditableView asEditableView(IView view){
    if (view instanceof IEditableView) {
        return (IEditableView) view;
    } else {
        throw new AdempiereException("View is not editable").setParameter("view", view);
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/asEditableView"))

.queryParam("view",view);
IEditableView aux = restTemplate.getForObject(builder.toUriString(),IEditableView.class);
return aux;
}
;

public void patchViewRow(RowEditingContext ctx,List<JSONDocumentChangedEvent> fieldChangeRequests)

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/patchViewRow"))

.queryParam("ctx",ctx);
.queryParam("fieldChangeRequests",fieldChangeRequests);
restTemplate.put(builder.toUriString(),null);
}
;

}