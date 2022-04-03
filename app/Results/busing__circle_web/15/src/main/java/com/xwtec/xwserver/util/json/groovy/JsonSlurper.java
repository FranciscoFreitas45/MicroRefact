package com.xwtec.xwserver.util.json.groovy;
 import groovy.lang.GroovyObjectSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import com.xwtec.xwserver.util.json.JSON;
import com.xwtec.xwserver.util.json.JSONSerializer;
import com.xwtec.xwserver.util.json.JsonConfig;
public class JsonSlurper extends GroovyObjectSupport{

 private  JsonConfig jsonConfig;

public JsonSlurper() {
    this(new JsonConfig());
}public JsonSlurper(JsonConfig jsonConfig) {
    this.jsonConfig = jsonConfig != null ? jsonConfig : new JsonConfig();
}
public JSON parse(Reader reader){
    // unfortunately JSONSerializer can't process the text as a stream
    // so we must read the full content first and then parse it
    StringBuffer buffer = new StringBuffer();
    BufferedReader in = new BufferedReader(reader);
    String line = null;
    while ((line = in.readLine()) != null) {
        buffer.append(line).append("\n");
    }
    return parseText(buffer.toString());
}


public JSON parseText(String text){
    return JSONSerializer.toJSON(text, jsonConfig);
}


}