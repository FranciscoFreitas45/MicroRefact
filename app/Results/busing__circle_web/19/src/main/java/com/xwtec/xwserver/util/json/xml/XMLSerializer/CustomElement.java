package com.xwtec.xwserver.util.json.xml.XMLSerializer;
 import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;
import nu.xom.Serializer;
import nu.xom.Text;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.xwtec.xwserver.util.json.JSON;
import com.xwtec.xwserver.util.json.JSONArray;
import com.xwtec.xwserver.util.json.JSONException;
import com.xwtec.xwserver.util.json.JSONFunction;
import com.xwtec.xwserver.util.json.JSONNull;
import com.xwtec.xwserver.util.json.JSONObject;
import com.xwtec.xwserver.util.json.util.JSONUtils;
public class CustomElement extends Element{

 private  String prefix;

public CustomElement(String name) {
    super(CustomElement.getName(name));
    prefix = CustomElement.getPrefix(name);
}
public String getName(String name){
    int colon = name.indexOf(':');
    if (colon != -1) {
        return name.substring(colon + 1);
    }
    return name;
}


public String getPrefix(String name){
    int colon = name.indexOf(':');
    if (colon != -1) {
        return name.substring(0, colon);
    }
    return "";
}


public String getQName(){
    if (prefix.length() == 0) {
        return getLocalName();
    } else {
        return prefix + ":" + getLocalName();
    }
}


}