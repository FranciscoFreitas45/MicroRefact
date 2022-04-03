package de.metas.ui.web.window.datatypes;
 import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import de.metas.util.Check;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NonNull;
import lombok.Value;
@Value
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ColorValue {

 private String hexString;


@JsonValue
public String toJson(){
    return hexString;
}


public String toHexString(int red,int green,int blue){
    return String.format("#%02x%02x%02x", red, green, blue);
}


public String normalizeHexString(String hexString){
    try {
        final int i = Integer.decode(hexString);
        final int red = i >> 16 & 0xFF;
        final int green = i >> 8 & 0xFF;
        final int blue = i & 0xFF;
        return toHexString(red, green, blue);
    } catch (final Exception ex) {
        throw new AdempiereException("Invalid color hex string: " + hexString, ex);
    }
}


@JsonCreator
public ColorValue ofHexString(String hexString){
    return new ColorValue(hexString);
}


public ColorValue ofRGB(int red,int green,int blue){
    return new ColorValue(red, green, blue);
}


}