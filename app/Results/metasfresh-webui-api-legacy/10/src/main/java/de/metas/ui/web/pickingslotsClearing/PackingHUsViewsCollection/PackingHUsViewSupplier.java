package de.metas.ui.web.pickingslotsClearing.PackingHUsViewsCollection;
 import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import de.metas.handlingunits.HuId;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.pickingslotsClearing.process.HUExtractedFromPickingSlotEvent;
import lombok.NonNull;
@FunctionalInterface
public interface PackingHUsViewSupplier {


public HUEditorView createPackingHUsView(PackingHUsViewKey key)
;

}