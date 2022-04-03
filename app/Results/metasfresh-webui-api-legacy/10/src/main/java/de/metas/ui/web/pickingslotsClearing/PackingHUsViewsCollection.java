package de.metas.ui.web.pickingslotsClearing;
 import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import de.metas.handlingunits.HuId;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.pickingslotsClearing.process.HUExtractedFromPickingSlotEvent;
import lombok.NonNull;
public class PackingHUsViewsCollection {

 private  ConcurrentHashMap<PackingHUsViewKey,HUEditorView> packingHUsViewsByKey;


public Optional<HUEditorView> getByKeyIfExists(PackingHUsViewKey key){
    return Optional.ofNullable(packingHUsViewsByKey.get(key));
}


public Optional<HUEditorView> removeIfExists(PackingHUsViewKey key){
    final HUEditorView packingHUsViewRemoved = packingHUsViewsByKey.remove(key);
    return Optional.ofNullable(packingHUsViewRemoved);
}


public void handleEvent(HUExtractedFromPickingSlotEvent event){
    packingHUsViewsByKey.entrySet().stream().filter(entry -> isEventMatchingKey(event, entry.getKey())).map(entry -> entry.getValue()).forEach(packingHUsView -> packingHUsView.addHUIdAndInvalidate(HuId.ofRepoId(event.getHuId())));
}


public HUEditorView computeIfAbsent(PackingHUsViewKey key,PackingHUsViewSupplier packingHUsViewFactory){
    return packingHUsViewsByKey.computeIfAbsent(key, packingHUsViewFactory::createPackingHUsView);
}


public boolean isEventMatchingKey(HUExtractedFromPickingSlotEvent event,PackingHUsViewKey key){
    return key.isBPartnerIdMatching(event.getBpartnerId()) && key.isBPartnerLocationIdMatching(event.getBpartnerLocationId());
}


public HUEditorView createPackingHUsView(PackingHUsViewKey key)


}