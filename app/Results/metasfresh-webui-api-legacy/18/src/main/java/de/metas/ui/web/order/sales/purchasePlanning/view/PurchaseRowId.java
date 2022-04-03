package de.metas.ui.web.order.sales.purchasePlanning.view;
 import java.util.List;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.apache.commons.lang3.RandomStringUtils;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Splitter;
import de.metas.bpartner.BPartnerId;
import de.metas.purchasecandidate.PurchaseDemandId;
import de.metas.purchasecandidate.availability.AvailabilityResult.Type;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.util.Check;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@ToString
@EqualsAndHashCode
public class PurchaseRowId {

@VisibleForTesting
 static  String PARTS_SEPARATOR;

 private  Splitter PARTS_SPLITTER;

@Getter
 private  PurchaseRowType type;

@Getter
 private  PurchaseDemandId purchaseDemandId;

@Getter(AccessLevel.PACKAGE)
@VisibleForTesting
 private  BPartnerId vendorId;

 private  boolean readonly;

@Getter
 private  Type availabilityType;

@Getter(AccessLevel.PACKAGE)
@VisibleForTesting
 private  String availabilityDistinguisher;

 private  DocumentId _documentId;


public boolean decodeReadonly(String readonlyStr){
    return "ro".equals(readonlyStr);
}


public boolean isLineRowId(){
    return type == PurchaseRowType.LINE;
}


public PurchaseRowId toLineRowId(){
    if (isLineRowId()) {
        return this;
    } else {
        final boolean readonly = false;
        return lineId(purchaseDemandId, vendorId, readonly);
    }
}


public PurchaseRowId withAvailability(Type availabilityType,String availabilityDistinguisher){
    Check.errorUnless(this.isLineRowId(), "The method withAvailabilityId may only be invoked on a line row id; this={}", this);
    return availabilityDetailId(purchaseDemandId, vendorId, availabilityType, availabilityDistinguisher);
}


public PurchaseRowId toGroupRowId(){
    if (isGroupRowId()) {
        return this;
    } else {
        return groupId(purchaseDemandId);
    }
}


public boolean isGroupRowId(){
    return type == PurchaseRowType.GROUP;
}


public void assertRowType(PurchaseRowType expectedRowType){
    final PurchaseRowType rowType = getType();
    if (rowType != expectedRowType) {
        throw new AdempiereException("Expected " + expectedRowType + " but it was " + rowType + ": " + this);
    }
}


public PurchaseRowId groupId(PurchaseDemandId purchaseDemandId){
    final DocumentId documentId = null;
    return new PurchaseRowId(purchaseDemandId, documentId);
}


public PurchaseRowId lineId(PurchaseDemandId purchaseDemandId,BPartnerId vendorId,boolean readonly){
    final DocumentId documentId = null;
    return new PurchaseRowId(purchaseDemandId, vendorId, readonly, documentId);
}


public boolean isAvailableOnVendor(){
    assertRowType(PurchaseRowType.AVAILABILITY_DETAIL);
    return getAvailabilityType().equals(Type.AVAILABLE);
}


public DocumentId createDocumentId(){
    final StringBuilder sb = new StringBuilder();
    sb.append(type.getCode());
    sb.append(PARTS_SEPARATOR).append(purchaseDemandId.getId());
    if (type == PurchaseRowType.GROUP) {
    } else if (type == PurchaseRowType.LINE) {
        sb.append(PARTS_SEPARATOR).append(vendorId.getRepoId());
        sb.append(PARTS_SEPARATOR).append(encodeReadonly(readonly));
    } else if (type == PurchaseRowType.AVAILABILITY_DETAIL) {
        sb.append(PARTS_SEPARATOR).append(vendorId.getRepoId());
        sb.append(PARTS_SEPARATOR).append(availabilityType.toString());
        sb.append(PARTS_SEPARATOR).append(availabilityDistinguisher);
    } else {
        throw new AdempiereException("Type " + type + " is not supported");
    }
    return DocumentId.ofString(sb.toString());
}


public String encodeReadonly(boolean readonly){
    return readonly ? "ro" : "rw";
}


public boolean isAvailabilityRowId(){
    return type == PurchaseRowType.AVAILABILITY_DETAIL;
}


public PurchaseRowId withAvailabilityAndRandomDistinguisher(Type availabilityType){
    final String availabilityDistinguisher = RandomStringUtils.random(8, /* includeLetters */
    true, /* includeNumbers */
    true);
    return withAvailability(availabilityType, availabilityDistinguisher);
}


public DocumentId toDocumentId(){
    DocumentId documentId = _documentId;
    if (documentId == null) {
        documentId = _documentId = createDocumentId();
    }
    return documentId;
}


public PurchaseRowId availabilityDetailId(PurchaseDemandId purchaseDemandId,BPartnerId vendorId,Type availabilityType,String availabilityDistinguisher){
    final DocumentId documentId = null;
    return new PurchaseRowId(purchaseDemandId, vendorId, availabilityType, availabilityDistinguisher, documentId);
}


public PurchaseRowId fromJson(String json,DocumentId documentId){
    final List<String> parts = PARTS_SPLITTER.splitToList(json);
    final int partsCount = parts.size();
    if (// we expect at least the type and purchaseDemandId to be encoded in the json string
    partsCount < 2) {
        throw new AdempiereException("Invalid format: " + json);
    }
    try {
        final PurchaseRowType type = PurchaseRowType.ofCode(parts.get(0));
        final PurchaseDemandId purchaseDemandId = PurchaseDemandId.ofId(Integer.parseInt(parts.get(1)));
        if (type == PurchaseRowType.GROUP) {
            return new PurchaseRowId(purchaseDemandId, documentId);
        }
        final BPartnerId vendorId = BPartnerId.ofRepoId(Integer.parseInt(parts.get(2)));
        if (type == PurchaseRowType.LINE) {
            final boolean readonly = decodeReadonly(parts.get(3));
            return new PurchaseRowId(purchaseDemandId, vendorId, readonly, documentId);
        }
        final Type availabilityType = Type.valueOf(parts.get(3));
        final String availabilityDistinguisher = parts.get(4);
        if (type == PurchaseRowType.AVAILABILITY_DETAIL) {
            return new PurchaseRowId(purchaseDemandId, vendorId, availabilityType, availabilityDistinguisher, documentId);
        }
        // 
        throw new AdempiereException("Type " + type + " is not supported");
    } catch (final Exception ex) {
        throw new AdempiereException("Cannot convert '" + json + "' to " + PurchaseRowId.class, ex);
    }
}


public PurchaseRowId fromDocumentId(DocumentId documentId){
    return fromJson(documentId.toJson(), documentId);
}


}