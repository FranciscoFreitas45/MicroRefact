package de.metas.ui.web.handlingunits;
 import java.util.stream.Stream;
import java.util.Objects;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.Services;
import de.metas.util.stream.StreamUtils;
import lombok.NonNull;
public class HUEditorProcessTemplate extends ViewBasedProcessTemplate{

 protected  IHandlingUnitsDAO handlingUnitsRepo;


public Stream<HUEditorRow> streamSelectedRows(HUEditorRowFilter filter){
    final DocumentIdsSelection selectedDocumentIds = getSelectedRowIds();
    if (selectedDocumentIds.isEmpty()) {
        return Stream.empty();
    }
    return getView().streamByIds(filter.andOnlyRowIds(selectedDocumentIds));
}


public Stream<HuId> streamSelectedHUIds(HUEditorRowFilter filter){
    return streamSelectedRows(filter).map(HUEditorRow::getHuId).filter(Objects::nonNull);
}


@Override
public HUEditorRow getSingleSelectedRow(){
    return HUEditorRow.cast(super.getSingleSelectedRow());
}


@Override
public HUEditorView getView(){
    return super.getView(HUEditorView.class);
}


public boolean isHUEditorView(){
    return isViewClass(HUEditorView.class);
}


public Stream<I_M_HU> streamSelectedHUs(HUEditorRowFilter filter){
    final Stream<HuId> huIds = streamSelectedHUIds(filter);
    return StreamUtils.dice(huIds, 100).flatMap(huIdsChunk -> handlingUnitsRepo.getByIds(huIdsChunk).stream());
}


}