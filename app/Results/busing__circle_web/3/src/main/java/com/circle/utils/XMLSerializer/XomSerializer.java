package com.circle.utils.XMLSerializer;
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
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONFunction;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import net.sf.json.xml.JSONTypes;
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
public class XomSerializer extends Serializer{

public XomSerializer(OutputStream out) {
    super(out);
}public XomSerializer(OutputStream out, String encoding) throws UnsupportedEncodingException {
    super(out, encoding);
}
public void writeEndTag(Element element){
    if (element instanceof CustomElement && isNamespaceLenient()) {
        writeRaw("</");
        writeRaw(((CustomElement) element).getQName());
        writeRaw(">");
    } else {
        super.writeEndTag(element);
    }
}


public void writeTagBeginning(CustomElement element){
    writeRaw("<");
    writeRaw(element.getQName());
    writeAttributes(element);
    writeNamespaceDeclarations(element);
}


public void writeStartTag(Element element){
    if (element instanceof CustomElement && isNamespaceLenient()) {
        writeTagBeginning((CustomElement) element);
        writeRaw(">");
    } else {
        super.writeStartTag(element);
    }
}


public void writeEmptyElementTag(Element element){
    if (element instanceof CustomElement && isNamespaceLenient()) {
        writeTagBeginning((CustomElement) element);
        writeRaw("/>");
    } else {
        super.writeEmptyElementTag(element);
    }
}


public void write(Text text){
    String value = text.getValue();
    if (value.startsWith("<![CDATA[") && value.endsWith("]]>")) {
        value = value.substring(9);
        value = value.substring(0, value.length() - 3);
        writeRaw("<![CDATA[");
        writeRaw(value);
        writeRaw("]]>");
    } else {
        super.write(text);
    }
}


public void writeNamespaceDeclaration(String prefix,String uri){
    if (!StringUtils.isBlank(uri)) {
        super.writeNamespaceDeclaration(prefix, uri);
    }
}


}