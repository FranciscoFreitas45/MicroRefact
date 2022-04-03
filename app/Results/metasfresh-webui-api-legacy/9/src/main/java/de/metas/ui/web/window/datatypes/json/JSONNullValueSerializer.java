package de.metas.ui.web.window.datatypes.json;
 import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
public class JSONNullValueSerializer extends JsonSerializer<JSONNullValue>{


@Override
public void serialize(JSONNullValue value,JsonGenerator gen,SerializerProvider serializers){
    gen.writeNull();
}


}