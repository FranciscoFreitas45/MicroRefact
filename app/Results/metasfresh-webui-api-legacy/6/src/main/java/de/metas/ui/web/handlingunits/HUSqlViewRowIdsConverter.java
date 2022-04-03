package de.metas.ui.web.handlingunits;
 import java.util.Set;
import de.metas.handlingunits.HuId;
import de.metas.ui.web.view.descriptor.SqlViewRowIdsConverter;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
public class HUSqlViewRowIdsConverter implements SqlViewRowIdsConverter{

 public  HUSqlViewRowIdsConverter instance;


@Override
public Set<Integer> convertToRecordIds(DocumentIdsSelection rowIds){
    final Set<HuId> huIds = HUEditorRowId.extractHUIdsOnly(rowIds);
    return HuId.toRepoIds(huIds);
}


}