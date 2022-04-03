package de.metas.ui.web.handlingunits;
 import java.util.concurrent.ConcurrentHashMap;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.attribute.storage.IAttributeStorage;
import de.metas.handlingunits.attribute.storage.IAttributeStorageFactory;
import de.metas.handlingunits.attribute.storage.IAttributeStorageFactoryService;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.handlingunits.storage.IHUProductStorage;
import de.metas.handlingunits.storage.IHUStorage;
import de.metas.handlingunits.storage.IHUStorageFactory;
import de.metas.product.ProductId;
import de.metas.ui.web.view.IViewRowAttributesProvider;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.util.Services;
import lombok.Builder;
import lombok.Value;
public class HUEditorRowAttributesProvider implements IViewRowAttributesProvider{

 private  boolean readonly;

 private  ExtendedMemorizingSupplier<IAttributeStorageFactory> _attributeStorageFactory;

 private  ConcurrentHashMap<ViewRowAttributesKey,HUEditorRowAttributes> rowAttributesByKey;

 private  DocumentId huEditorRowId;

 private  DocumentId huId;


public boolean isReadonly(){
    return readonly;
}


public IAttributeStorageFactory getAttributeStorageFactory(){
    return _attributeStorageFactory.get();
}


@Override
public HUEditorRowAttributes getAttributes(DocumentId viewRowId,DocumentId huId){
    final ViewRowAttributesKey key = new ViewRowAttributesKey(viewRowId, huId);
    return rowAttributesByKey.computeIfAbsent(key, this::createRowAttributes);
}


public I_M_HU extractHU(ViewRowAttributesKey key){
    final HuId huId = HuId.ofRepoId(key.getHuId().toInt());
    final IHandlingUnitsDAO handlingUnitsRepo = Services.get(IHandlingUnitsDAO.class);
    final I_M_HU hu = handlingUnitsRepo.getByIdOutOfTrx(huId);
    if (hu == null) {
        throw new IllegalArgumentException("No HU found for M_HU_ID=" + huId);
    }
    return hu;
}


@Override
public void invalidateAll(){
    // 
    // Destroy AttributeStorageFactory
    _attributeStorageFactory.forget();
    // 
    // Destroy attribute documents
    rowAttributesByKey.clear();
}


public DocumentPath createDocumentPath(ViewRowAttributesKey key){
    final DocumentId documentTypeId = key.getHuId();
    final DocumentId huEditorRowId = key.getHuEditorRowId();
    return DocumentPath.rootDocumentPath(DocumentType.ViewRecordAttributes, documentTypeId, huEditorRowId);
}


public HUEditorRowAttributes createRowAttributes(ViewRowAttributesKey key){
    final I_M_HU hu = extractHU(key);
    final IAttributeStorage attributesStorage = getAttributeStorageFactory().getAttributeStorage(hu);
    attributesStorage.setSaveOnChange(true);
    final boolean rowAttributesReadonly = // readonly if the provider shall provide readonly attributes
    isReadonly() || // or, readonly if not Planning, see https://github.com/metasfresh/metasfresh-webui-api/issues/314
    !X_M_HU.HUSTATUS_Planning.equals(hu.getHUStatus());
    final IHandlingUnitsBL handlingUnitsBL = Services.get(IHandlingUnitsBL.class);
    final IHUStorageFactory storageFactory = handlingUnitsBL.getStorageFactory();
    final IHUStorage storage = storageFactory.getStorage(hu);
    final ImmutableSet<ProductId> productIDs = storage.getProductStorages().stream().map(IHUProductStorage::getProductId).collect(ImmutableSet.toImmutableSet());
    final DocumentPath documentPath = createDocumentPath(key);
    return new HUEditorRowAttributes(documentPath, attributesStorage, productIDs, rowAttributesReadonly);
}


public DocumentId createAttributeKey(HuId huId){
    return DocumentId.of(huId);
}


public IAttributeStorageFactory createAttributeStorageFactory(){
    final IHandlingUnitsBL handlingUnitsBL = Services.get(IHandlingUnitsBL.class);
    final IAttributeStorageFactoryService attributeStorageFactoryService = Services.get(IAttributeStorageFactoryService.class);
    final IHUStorageFactory storageFactory = handlingUnitsBL.getStorageFactory();
    final IAttributeStorageFactory huAttributeStorageFactory = attributeStorageFactoryService.createHUAttributeStorageFactory(storageFactory);
    return huAttributeStorageFactory;
}


}