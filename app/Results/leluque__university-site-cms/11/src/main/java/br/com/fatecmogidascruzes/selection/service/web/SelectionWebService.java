package br.com.fatecmogidascruzes.selection.service.web;
 import java.util.UUID;
import br.com.fatecmogidascruzes.data.SearchCriteria;
import br.com.fatecmogidascruzes.dto.TableDTO;
import br.com.fatecmogidascruzes.exception.InexistentOrDisabledEntity;
public interface SelectionWebService {


public SelectionEditDTO getSelectionEditDTOByHash(UUID selectionHash) throws InexistentOrDisabledEntity
;

public TableDTO<SelectionTableRowDTO> getTable(SearchCriteria searchCriteria,int draw)
;

public SelectionEditDTO find(UUID postHash) throws InexistentOrDisabledEntity
;

public void save(SelectionEditDTO selectionEditDTO)
;

}