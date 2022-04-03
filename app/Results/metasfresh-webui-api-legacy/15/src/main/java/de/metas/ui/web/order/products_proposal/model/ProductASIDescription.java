package de.metas.ui.web.order.products_proposal.model;
 import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
@EqualsAndHashCode
public class ProductASIDescription {

 public  ProductASIDescription NONE;

 private  String value;


@JsonCreator
public ProductASIDescription ofString(String value){
    if (value == null) {
        return NONE;
    }
    final String valueNorm = value.trim();
    if (valueNorm.isEmpty()) {
        return NONE;
    }
    return new ProductASIDescription(valueNorm);
}


@Override
@JsonValue
public String toString(){
    return value;
}


}