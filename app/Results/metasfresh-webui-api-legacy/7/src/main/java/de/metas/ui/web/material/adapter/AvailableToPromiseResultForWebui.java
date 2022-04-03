package de.metas.ui.web.material.adapter;
 import java.util.List;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.mm.attributes.api.ImmutableAttributeSet;
import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.util.Check;
import de.metas.util.lang.CoalesceUtil;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
@Value
public class AvailableToPromiseResultForWebui {

 private  List<Group> groups;

 private ProductId productId;

 private Quantity qty;

 private Type type;

 private ImmutableAttributeSet attributes;


public Quantity getSingleQuantity(){
    return getSingleGroup().getQty();
}


public Group getSingleGroup(){
    if (groups.size() > 1) {
        throw new AdempiereException("Not a single group: " + this);
    }
    return groups.get(0);
}


}