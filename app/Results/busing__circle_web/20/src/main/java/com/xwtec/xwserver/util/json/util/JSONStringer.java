package com.xwtec.xwserver.util.json.util;
 import java.io.StringWriter;
public class JSONStringer extends JSONBuilder{

/**
 * Make a fresh JSONStringer. It can be used to build one JSON text.
 */
public JSONStringer() {
    super(new StringWriter());
}
public String toString(){
    return this.mode == 'd' ? this.writer.toString() : null;
}


}