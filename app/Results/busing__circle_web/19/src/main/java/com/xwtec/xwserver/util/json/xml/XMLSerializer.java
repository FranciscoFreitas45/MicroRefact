package com.xwtec.xwserver.util.json.xml;
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
import com.DTO.JSONObject;
import com.DTO.JSONArray;
import com.DTO.JSONFunction;
@SuppressWarnings("unchecked")
public class XMLSerializer {

 private  String[] EMPTY_ARRAY;

 private  String JSON_PREFIX;

 private  Log log;

 private  String arrayName;

 private  String elementName;

 private  String[] expandableProperties;

 private  boolean forceTopLevelObject;

 private  boolean namespaceLenient;

 private  Map namespacesPerElement;

 private  String objectName;

 private  boolean removeNamespacePrefixFromElements;

 private  String rootName;

 private  Map rootNamespace;

 private  boolean skipNamespaces;

 private  boolean skipWhitespace;

 private  boolean trimSpaces;

 private  boolean typeHintsCompatibility;

 private  boolean typeHintsEnabled;

 private  String prefix;

/**
 * Creates a new XMLSerializer with default options.<br>
 * <ul>
 * <li><code>objectName</code>: 'o'</li>
 * <li><code>arrayName</code>: 'a'</li>
 * <li><code>elementName</code>: 'e'</li>
 * <li><code>typeHinstEnabled</code>: true</li>
 * <li><code>typeHinstCompatibility</code>: true</li>
 * <li><code>namespaceLenient</code>: false</li>
 * <li><code>expandableProperties</code>: []</li>
 * <li><code>skipNamespaces</code>: false</li>
 * <li><code>removeNameSpacePrefixFromElement</code>: false</li>
 * <li><code>trimSpaces</code>: false</li>
 * </ul>
 */
public XMLSerializer() {
    setObjectName("o");
    setArrayName("a");
    setElementName("e");
    setTypeHintsEnabled(true);
    setTypeHintsCompatibility(true);
    setNamespaceLenient(false);
    setSkipNamespaces(false);
    setRemoveNamespacePrefixFromElements(false);
    setTrimSpaces(false);
    setExpandableProperties(EMPTY_ARRAY);
    setSkipNamespaces(false);
}
public void setOrAccumulate(JSONObject jsonObject,String key,Object value){
    if (jsonObject.has(key)) {
        jsonObject.accumulate(key, value);
        Object val = jsonObject.get(key);
        if (val instanceof JSONArray) {
            ((JSONArray) val).setExpandElements(true);
        }
    } else {
        jsonObject.element(key, value);
    }
}


public String getClass(Element element){
    Attribute attribute = element.getAttribute(addJsonPrefix("class"));
    String clazz = null;
    if (attribute != null) {
        String clazzText = attribute.getValue().trim();
        if (JSONTypes.OBJECT.compareToIgnoreCase(clazzText) == 0) {
            clazz = JSONTypes.OBJECT;
        } else if (JSONTypes.ARRAY.compareToIgnoreCase(clazzText) == 0) {
            clazz = JSONTypes.ARRAY;
        }
    }
    return clazz;
}


public String getName(String name){
    int colon = name.indexOf(':');
    if (colon != -1) {
        return name.substring(colon + 1);
    }
    return name;
}


public void setElementName(String elementName){
    this.elementName = StringUtils.isBlank(elementName) ? "e" : elementName;
}


public JSON readFromStream(InputStream stream){
    try {
        StringBuffer xml = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String line = null;
        while ((line = in.readLine()) != null) {
            xml.append(line);
        }
        return read(xml.toString());
    } catch (IOException ioe) {
        throw new JSONException(ioe);
    }
}


public void setArrayName(String arrayName){
    this.arrayName = StringUtils.isBlank(arrayName) ? "a" : arrayName;
}


public String getElementName(){
    return elementName;
}


public void setRemoveNamespacePrefixFromElements(boolean removeNamespacePrefixFromElements){
    this.removeNamespacePrefixFromElements = removeNamespacePrefixFromElements;
}


public void setTypeHintsEnabled(boolean typeHintsEnabled){
    this.typeHintsEnabled = typeHintsEnabled;
}


public void setRootName(String rootName){
    this.rootName = StringUtils.isBlank(rootName) ? null : rootName;
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


public void setObjectName(String objectName){
    this.objectName = StringUtils.isBlank(objectName) ? "o" : objectName;
}


public JSON processObjectElement(Element element,String defaultType){
    if (isNullObject(element)) {
        return JSONNull.getInstance();
    }
    JSONObject jsonObject = new JSONObject();
    if (!skipNamespaces) {
        for (int j = 0; j < element.getNamespaceDeclarationCount(); j++) {
            String prefix = element.getNamespacePrefix(j);
            String uri = element.getNamespaceURI(prefix);
            if (StringUtils.isBlank(uri)) {
                continue;
            }
            if (!StringUtils.isBlank(prefix)) {
                prefix = ":" + prefix;
            }
            setOrAccumulate(jsonObject, "@xmlns" + prefix, trimSpaceFromValue(uri));
        }
    }
    // process attributes first
    int attrCount = element.getAttributeCount();
    for (int i = 0; i < attrCount; i++) {
        Attribute attr = element.getAttribute(i);
        String attrname = attr.getQualifiedName();
        if (isTypeHintsEnabled() && (addJsonPrefix("class").compareToIgnoreCase(attrname) == 0 || addJsonPrefix("type").compareToIgnoreCase(attrname) == 0)) {
            continue;
        }
        String attrvalue = attr.getValue();
        setOrAccumulate(jsonObject, "@" + removeNamespacePrefix(attrname), trimSpaceFromValue(attrvalue));
    }
    // process children (including text)
    int childCount = element.getChildCount();
    for (int i = 0; i < childCount; i++) {
        Node child = element.getChild(i);
        if (child instanceof Text) {
            Text text = (Text) child;
            if (StringUtils.isNotBlank(StringUtils.strip(text.getValue()))) {
                setOrAccumulate(jsonObject, "#text", trimSpaceFromValue(text.getValue()));
            }
        } else if (child instanceof Element) {
            setValue(jsonObject, (Element) child, defaultType);
        }
    }
    return jsonObject;
}


public void setForceTopLevelObject(boolean forceTopLevelObject){
    this.forceTopLevelObject = forceTopLevelObject;
}


public String getQName(){
    if (prefix.length() == 0) {
        return getLocalName();
    } else {
        return prefix + ":" + getLocalName();
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


public JSON read(String xml){
    JSON json = null;
    try {
        Document doc = new Builder().build(new StringReader(xml));
        Element root = doc.getRootElement();
        if (isNullObject(root)) {
            return JSONNull.getInstance();
        }
        String defaultType = getType(root, JSONTypes.STRING);
        if (isArray(root, true)) {
            json = processArrayElement(root, defaultType);
            if (forceTopLevelObject) {
                String key = removeNamespacePrefix(root.getQualifiedName());
                json = new JSONObject().element(key, json);
            }
        } else {
            json = processObjectElement(root, defaultType);
            if (forceTopLevelObject) {
                String key = removeNamespacePrefix(root.getQualifiedName());
                json = new JSONObject().element(key, json);
            }
        }
    } catch (JSONException jsone) {
        throw jsone;
    } catch (Exception e) {
        throw new JSONException(e);
    }
    return json;
}


public String getPrefix(String name){
    int colon = name.indexOf(':');
    if (colon != -1) {
        return name.substring(0, colon);
    }
    return "";
}


public boolean isObject(Element element,boolean isTopLevel){
    boolean isObject = false;
    if (!isArray(element, isTopLevel) && !isFunction(element)) {
        if (hasNamespaces(element)) {
            return true;
        }
        int attributeCount = element.getAttributeCount();
        if (attributeCount > 0) {
            int attrs = element.getAttribute(addJsonPrefix("null")) == null ? 0 : 1;
            attrs += element.getAttribute(addJsonPrefix("class")) == null ? 0 : 1;
            attrs += element.getAttribute(addJsonPrefix("type")) == null ? 0 : 1;
            switch(attributeCount) {
                case 1:
                    if (attrs == 0) {
                        return true;
                    }
                    break;
                case 2:
                    if (attrs < 2) {
                        return true;
                    }
                    break;
                case 3:
                    if (attrs < 3) {
                        return true;
                    }
                    break;
                default:
                    return true;
            }
        }
        int childCount = element.getChildCount();
        if (childCount == 1 && element.getChild(0) instanceof Text) {
            return isTopLevel;
        }
        isObject = true;
    }
    return isObject;
}


public Element processJSONArray(JSONArray array,Element root,String[] expandableProperties){
    int l = array.size();
    for (int i = 0; i < l; i++) {
        Object value = array.get(i);
        Element element = processJSONValue(value, root, null, expandableProperties);
        root.appendChild(element);
    }
    return root;
}


public boolean isFunction(Element element){
    int attrCount = element.getAttributeCount();
    if (attrCount > 0) {
        Attribute typeAttr = element.getAttribute(addJsonPrefix("type"));
        Attribute paramsAttr = element.getAttribute(addJsonPrefix("params"));
        if (attrCount == 1 && paramsAttr != null) {
            return true;
        }
        if (attrCount == 2 && paramsAttr != null && typeAttr != null && (typeAttr.getValue().compareToIgnoreCase(JSONTypes.STRING) == 0 || typeAttr.getValue().compareToIgnoreCase(JSONTypes.FUNCTION) == 0)) {
            return true;
        }
    }
    return false;
}


public String removeNamespacePrefix(String name){
    if (isRemoveNamespacePrefixFromElements()) {
        int colon = name.indexOf(':');
        return colon != -1 ? name.substring(colon + 1) : name;
    }
    return name;
}


public Element processJSONObject(JSONObject jsonObject,Element root,String[] expandableProperties,boolean isRoot){
    if (jsonObject.isNullObject()) {
        root.addAttribute(new Attribute(addJsonPrefix("null"), "true"));
        return root;
    } else if (jsonObject.isEmpty()) {
        return root;
    }
    if (isRoot) {
        if (!rootNamespace.isEmpty()) {
            setNamespaceLenient(true);
            for (Iterator entries = rootNamespace.entrySet().iterator(); entries.hasNext(); ) {
                Map.Entry entry = (Map.Entry) entries.next();
                String prefix = (String) entry.getKey();
                String uri = (String) entry.getValue();
                if (StringUtils.isBlank(prefix)) {
                    root.setNamespaceURI(uri);
                } else {
                    root.addNamespaceDeclaration(prefix, uri);
                }
            }
        }
    }
    addNameSpaceToElement(root);
    Object[] names = jsonObject.names().toArray();
    Arrays.sort(names);
    Element element = null;
    for (int i = 0; i < names.length; i++) {
        String name = (String) names[i];
        Object value = jsonObject.get(name);
        if (name.startsWith("@xmlns")) {
            setNamespaceLenient(true);
            int colon = name.indexOf(':');
            if (colon == -1) {
                // do not override if already defined by nameSpaceMaps
                if (StringUtils.isBlank(root.getNamespaceURI())) {
                    root.setNamespaceURI(String.valueOf(value));
                }
            } else {
                String prefix = name.substring(colon + 1);
                if (StringUtils.isBlank(root.getNamespaceURI(prefix))) {
                    root.addNamespaceDeclaration(prefix, String.valueOf(value));
                }
            }
        } else if (name.startsWith("@")) {
            root.addAttribute(new Attribute(name.substring(1), String.valueOf(value)));
        } else if (name.equals("#text")) {
            if (value instanceof JSONArray) {
                root.appendChild(((JSONArray) value).join("", true));
            } else {
                root.appendChild(String.valueOf(value));
            }
        } else if (value instanceof JSONArray && (((JSONArray) value).isExpandElements() || ArrayUtils.contains(expandableProperties, name))) {
            JSONArray array = (JSONArray) value;
            int l = array.size();
            for (int j = 0; j < l; j++) {
                Object item = array.get(j);
                element = newElement(name);
                if (item instanceof JSONObject) {
                    element = processJSONValue((JSONObject) item, root, element, expandableProperties);
                } else if (item instanceof JSONArray) {
                    element = processJSONValue((JSONArray) item, root, element, expandableProperties);
                } else {
                    element = processJSONValue(item, root, element, expandableProperties);
                }
                addNameSpaceToElement(element);
                root.appendChild(element);
            }
        } else {
            element = newElement(name);
            element = processJSONValue(value, root, element, expandableProperties);
            addNameSpaceToElement(element);
            root.appendChild(element);
        }
    }
    return root;
}


public void setNamespaceLenient(boolean namespaceLenient){
    this.namespaceLenient = namespaceLenient;
}


public String[] getExpandableProperties(){
    return expandableProperties;
}


public void setSkipNamespaces(boolean skipNamespaces){
    this.skipNamespaces = skipNamespaces;
}


public boolean isNamespaceLenient(){
    return namespaceLenient;
}


public void setExpandableProperties(String[] expandableProperties){
    this.expandableProperties = expandableProperties == null ? EMPTY_ARRAY : expandableProperties;
}


public boolean isTrimSpaces(){
    return trimSpaces;
}


public String getObjectName(){
    return objectName;
}


public boolean isSkipNamespaces(){
    return skipNamespaces;
}


public String getArrayName(){
    return arrayName;
}


public boolean isRemoveNamespacePrefixFromElements(){
    return removeNamespacePrefixFromElements;
}


public String getType(Element element,String defaultType){
    Attribute attribute = element.getAttribute(addJsonPrefix("type"));
    String type = null;
    if (attribute != null) {
        String typeText = attribute.getValue().trim();
        if (JSONTypes.BOOLEAN.compareToIgnoreCase(typeText) == 0) {
            type = JSONTypes.BOOLEAN;
        } else if (JSONTypes.NUMBER.compareToIgnoreCase(typeText) == 0) {
            type = JSONTypes.NUMBER;
        } else if (JSONTypes.INTEGER.compareToIgnoreCase(typeText) == 0) {
            type = JSONTypes.INTEGER;
        } else if (JSONTypes.FLOAT.compareToIgnoreCase(typeText) == 0) {
            type = JSONTypes.FLOAT;
        } else if (JSONTypes.OBJECT.compareToIgnoreCase(typeText) == 0) {
            type = JSONTypes.OBJECT;
        } else if (JSONTypes.ARRAY.compareToIgnoreCase(typeText) == 0) {
            type = JSONTypes.ARRAY;
        } else if (JSONTypes.STRING.compareToIgnoreCase(typeText) == 0) {
            type = JSONTypes.STRING;
        } else if (JSONTypes.FUNCTION.compareToIgnoreCase(typeText) == 0) {
            type = JSONTypes.FUNCTION;
        }
    } else {
        if (defaultType != null) {
            log.info("Using default type " + defaultType);
            type = defaultType;
        }
    }
    return type;
}


public boolean isNullObject(Element element){
    if (element.getChildCount() == 0) {
        if (element.getAttributeCount() == 0) {
            return true;
        } else if (element.getAttribute(addJsonPrefix("null")) != null) {
            return true;
        } else if (element.getAttributeCount() == 1 && (element.getAttribute(addJsonPrefix("class")) != null || element.getAttribute(addJsonPrefix("type")) != null)) {
            return true;
        } else if (element.getAttributeCount() == 2 && (element.getAttribute(addJsonPrefix("class")) != null && element.getAttribute(addJsonPrefix("type")) != null)) {
            return true;
        }
    }
    if (skipWhitespace && element.getChildCount() == 1 && element.getChild(0) instanceof Text) {
        return true;
    }
    return false;
}


public void setTypeHintsCompatibility(boolean typeHintsCompatibility){
    this.typeHintsCompatibility = typeHintsCompatibility;
}


public void writeStartTag(Element element){
    if (element instanceof CustomElement && isNamespaceLenient()) {
        writeTagBeginning((CustomElement) element);
        writeRaw(">");
    } else {
        super.writeStartTag(element);
    }
}


public boolean isArray(Element element,boolean isTopLevel){
    boolean isArray = false;
    String clazz = getClass(element);
    if (clazz != null && clazz.equals(JSONTypes.ARRAY)) {
        isArray = true;
    } else if (element.getAttributeCount() == 0) {
        isArray = checkChildElements(element, isTopLevel);
    } else if (element.getAttributeCount() == 1 && (element.getAttribute(addJsonPrefix("class")) != null || element.getAttribute(addJsonPrefix("type")) != null)) {
        isArray = checkChildElements(element, isTopLevel);
    } else if (element.getAttributeCount() == 2 && (element.getAttribute(addJsonPrefix("class")) != null && element.getAttribute(addJsonPrefix("type")) != null)) {
        isArray = checkChildElements(element, isTopLevel);
    }
    if (isArray) {
        // check namespace
        for (int j = 0; j < element.getNamespaceDeclarationCount(); j++) {
            String prefix = element.getNamespacePrefix(j);
            String uri = element.getNamespaceURI(prefix);
            if (!StringUtils.isBlank(uri)) {
                return false;
            }
        }
    }
    return isArray;
}


public void writeTagBeginning(CustomElement element){
    writeRaw("<");
    writeRaw(element.getQName());
    writeAttributes(element);
    writeNamespaceDeclarations(element);
}


public void removeNamespace(String prefix,String elementName){
    if (prefix == null) {
        prefix = "";
    }
    if (StringUtils.isBlank(elementName)) {
        rootNamespace.remove(prefix.trim());
    } else {
        Map nameSpaces = (Map) namespacesPerElement.get(elementName);
        nameSpaces.remove(prefix);
    }
}


public JSON processArrayElement(Element element,String defaultType){
    JSONArray jsonArray = new JSONArray();
    // process children (including text)
    int childCount = element.getChildCount();
    for (int i = 0; i < childCount; i++) {
        Node child = element.getChild(i);
        if (child instanceof Text) {
            Text text = (Text) child;
            if (StringUtils.isNotBlank(StringUtils.strip(text.getValue()))) {
                jsonArray.element(text.getValue());
            }
        } else if (child instanceof Element) {
            setValue(jsonArray, (Element) child, defaultType);
        }
    }
    return jsonArray;
}


public boolean isTypeHintsEnabled(){
    return typeHintsEnabled;
}


public boolean checkChildElements(Element element,boolean isTopLevel){
    int childCount = element.getChildCount();
    Elements elements = element.getChildElements();
    int elementCount = elements.size();
    if (childCount == 1 && element.getChild(0) instanceof Text) {
        return isTopLevel;
    }
    if (childCount == elementCount) {
        if (elementCount == 0) {
            return true;
        }
        if (elementCount == 1) {
            if (skipWhitespace || element.getChild(0) instanceof Text) {
                return true;
            } else {
                return false;
            }
        }
    }
    if (childCount > elementCount) {
        for (int i = 0; i < childCount; i++) {
            Node node = element.getChild(i);
            if (node instanceof Text) {
                Text text = (Text) node;
                if (StringUtils.isNotBlank(StringUtils.strip(text.getValue())) && !skipWhitespace) {
                    return false;
                }
            }
        }
    }
    String childName = elements.get(0).getQualifiedName();
    for (int i = 1; i < elementCount; i++) {
        if (childName.compareTo(elements.get(i).getQualifiedName()) != 0) {
            return false;
        }
    }
    return true;
}


public void addNamespace(String prefix,String uri,String elementName){
    if (StringUtils.isBlank(uri)) {
        return;
    }
    if (prefix == null) {
        prefix = "";
    }
    if (StringUtils.isBlank(elementName)) {
        rootNamespace.put(prefix.trim(), uri.trim());
    } else {
        Map nameSpaces = (Map) namespacesPerElement.get(elementName);
        if (nameSpaces == null) {
            nameSpaces = new TreeMap();
            namespacesPerElement.put(elementName, nameSpaces);
        }
        nameSpaces.put(prefix, uri);
    }
}


public boolean isTypeHintsCompatibility(){
    return typeHintsCompatibility;
}


public String writeDocument(Document doc,String encoding){
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
        XomSerializer serializer = (encoding == null) ? new XomSerializer(baos) : new XomSerializer(baos, encoding);
        serializer.write(doc);
        encoding = serializer.getEncoding();
    } catch (IOException ioe) {
        throw new JSONException(ioe);
    }
    String str = null;
    try {
        str = baos.toString(encoding);
    } catch (UnsupportedEncodingException uee) {
        throw new JSONException(uee);
    }
    return str;
}


public void addNameSpaceToElement(Element element){
    String elementName = null;
    if (element instanceof CustomElement) {
        elementName = ((CustomElement) element).getQName();
    } else {
        elementName = element.getQualifiedName();
    }
    Map nameSpaces = (Map) namespacesPerElement.get(elementName);
    if (nameSpaces != null && !nameSpaces.isEmpty()) {
        setNamespaceLenient(true);
        for (Iterator entries = nameSpaces.entrySet().iterator(); entries.hasNext(); ) {
            Map.Entry entry = (Map.Entry) entries.next();
            String prefix = (String) entry.getKey();
            String uri = (String) entry.getValue();
            if (StringUtils.isBlank(prefix)) {
                element.setNamespaceURI(uri);
            } else {
                element.addNamespaceDeclaration(prefix, uri);
            }
        }
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


public Element processJSONValue(Object value,Element root,Element target,String[] expandableProperties){
    if (target == null) {
        target = newElement(getElementName());
    }
    if (JSONUtils.isBoolean(value)) {
        if (isTypeHintsEnabled()) {
            target.addAttribute(new Attribute(addJsonPrefix("type"), JSONTypes.BOOLEAN));
        }
        target.appendChild(value.toString());
    } else if (JSONUtils.isNumber(value)) {
        if (isTypeHintsEnabled()) {
            target.addAttribute(new Attribute(addJsonPrefix("type"), JSONTypes.NUMBER));
        }
        target.appendChild(value.toString());
    } else if (JSONUtils.isFunction(value)) {
        if (value instanceof String) {
            value = JSONFunction.parse((String) value);
        }
        JSONFunction func = (JSONFunction) value;
        if (isTypeHintsEnabled()) {
            target.addAttribute(new Attribute(addJsonPrefix("type"), JSONTypes.FUNCTION));
        }
        String params = ArrayUtils.toString(func.getParams());
        params = params.substring(1);
        params = params.substring(0, params.length() - 1);
        target.addAttribute(new Attribute(addJsonPrefix("params"), params));
        target.appendChild(new Text("<![CDATA[" + func.getText() + "]]>"));
    } else if (JSONUtils.isString(value)) {
        if (isTypeHintsEnabled()) {
            target.addAttribute(new Attribute(addJsonPrefix("type"), JSONTypes.STRING));
        }
        target.appendChild(value.toString());
    } else if (value instanceof JSONArray) {
        if (isTypeHintsEnabled()) {
            target.addAttribute(new Attribute(addJsonPrefix("class"), JSONTypes.ARRAY));
        }
        target = processJSONArray((JSONArray) value, target, expandableProperties);
    } else if (value instanceof JSONObject) {
        if (isTypeHintsEnabled()) {
            target.addAttribute(new Attribute(addJsonPrefix("class"), JSONTypes.OBJECT));
        }
        target = processJSONObject((JSONObject) value, target, expandableProperties, false);
    } else if (JSONUtils.isNull(value)) {
        if (isTypeHintsEnabled()) {
            target.addAttribute(new Attribute(addJsonPrefix("class"), JSONTypes.OBJECT));
        }
        target.addAttribute(new Attribute(addJsonPrefix("null"), "true"));
    }
    return target;
}


public Object processElement(Element element,String type){
    if (isNullObject(element)) {
        return JSONNull.getInstance();
    } else if (isArray(element, false)) {
        return processArrayElement(element, type);
    } else if (isObject(element, false)) {
        return processObjectElement(element, type);
    } else {
        return trimSpaceFromValue(element.getValue());
    }
}


public void writeNamespaceDeclaration(String prefix,String uri){
    if (!StringUtils.isBlank(uri)) {
        super.writeNamespaceDeclaration(prefix, uri);
    }
}


public String getRootName(){
    return rootName;
}


public boolean isSkipWhitespace(){
    return skipWhitespace;
}


public Element newElement(String name){
    if (name.indexOf(':') != -1) {
        namespaceLenient = true;
    }
    return namespaceLenient ? new CustomElement(name) : new Element(name);
}


public String trimSpaceFromValue(String value){
    if (isTrimSpaces()) {
        return value.trim();
    }
    return value;
}


public boolean hasNamespaces(Element element){
    int namespaces = 0;
    for (int i = 0; i < element.getNamespaceDeclarationCount(); i++) {
        String prefix = element.getNamespacePrefix(i);
        String uri = element.getNamespaceURI(prefix);
        if (StringUtils.isBlank(uri)) {
            continue;
        }
        namespaces++;
    }
    return namespaces > 0;
}


public void setSkipWhitespace(boolean skipWhitespace){
    this.skipWhitespace = skipWhitespace;
}


public void setTrimSpaces(boolean trimSpaces){
    this.trimSpaces = trimSpaces;
}


public boolean isForceTopLevelObject(){
    return forceTopLevelObject;
}


public JSON readFromFile(String path){
    return readFromStream(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));
}


public void setValue(JSONObject jsonObject,Element element,String defaultType){
    String clazz = getClass(element);
    String type = getType(element);
    type = (type == null) ? defaultType : type;
    String key = removeNamespacePrefix(element.getQualifiedName());
    if (hasNamespaces(element) && !skipNamespaces) {
        setOrAccumulate(jsonObject, key, simplifyValue(jsonObject, processElement(element, type)));
        return;
    } else if (element.getAttributeCount() > 0) {
        if (isFunction(element)) {
            Attribute paramsAttribute = element.getAttribute(addJsonPrefix("params"));
            String text = element.getValue();
            String[] params = StringUtils.split(paramsAttribute.getValue(), ",");
            setOrAccumulate(jsonObject, key, new JSONFunction(params, text));
            return;
        }
    /*else{
            setOrAccumulate( jsonObject, key, simplifyValue( jsonObject, processElement( element,
                  type ) ) );
            return;
         }*/
    }
    boolean classProcessed = false;
    if (clazz != null) {
        if (clazz.compareToIgnoreCase(JSONTypes.ARRAY) == 0) {
            setOrAccumulate(jsonObject, key, processArrayElement(element, type));
            classProcessed = true;
        } else if (clazz.compareToIgnoreCase(JSONTypes.OBJECT) == 0) {
            setOrAccumulate(jsonObject, key, simplifyValue(jsonObject, processObjectElement(element, type)));
            classProcessed = true;
        }
    }
    if (!classProcessed) {
        if (type.compareToIgnoreCase(JSONTypes.BOOLEAN) == 0) {
            setOrAccumulate(jsonObject, key, Boolean.valueOf(element.getValue()));
        } else if (type.compareToIgnoreCase(JSONTypes.NUMBER) == 0) {
            // try integer first
            try {
                setOrAccumulate(jsonObject, key, Integer.valueOf(element.getValue()));
            } catch (NumberFormatException e) {
                setOrAccumulate(jsonObject, key, Double.valueOf(element.getValue()));
            }
        } else if (type.compareToIgnoreCase(JSONTypes.INTEGER) == 0) {
            setOrAccumulate(jsonObject, key, Integer.valueOf(element.getValue()));
        } else if (type.compareToIgnoreCase(JSONTypes.FLOAT) == 0) {
            setOrAccumulate(jsonObject, key, Double.valueOf(element.getValue()));
        } else if (type.compareToIgnoreCase(JSONTypes.FUNCTION) == 0) {
            String[] params = null;
            String text = element.getValue();
            Attribute paramsAttribute = element.getAttribute(addJsonPrefix("params"));
            if (paramsAttribute != null) {
                params = StringUtils.split(paramsAttribute.getValue(), ",");
            }
            setOrAccumulate(jsonObject, key, new JSONFunction(params, text));
        } else if (type.compareToIgnoreCase(JSONTypes.STRING) == 0) {
            // see if by any chance has a 'params' attribute
            Attribute paramsAttribute = element.getAttribute(addJsonPrefix("params"));
            if (paramsAttribute != null) {
                String[] params = null;
                String text = element.getValue();
                params = StringUtils.split(paramsAttribute.getValue(), ",");
                setOrAccumulate(jsonObject, key, new JSONFunction(params, text));
            } else {
                if (isArray(element, false)) {
                    setOrAccumulate(jsonObject, key, processArrayElement(element, defaultType));
                } else if (isObject(element, false)) {
                    setOrAccumulate(jsonObject, key, simplifyValue(jsonObject, processObjectElement(element, defaultType)));
                } else {
                    setOrAccumulate(jsonObject, key, trimSpaceFromValue(element.getValue()));
                }
            }
        }
    }
}


public void clearNamespaces(String elementName){
    if (StringUtils.isBlank(elementName)) {
        rootNamespace.clear();
    } else {
        namespacesPerElement.remove(elementName);
    }
}


public String addJsonPrefix(String str){
    if (!isTypeHintsCompatibility()) {
        return JSON_PREFIX + str;
    }
    return str;
}


public Object simplifyValue(JSONObject parent,Object json){
    if (json instanceof JSONObject) {
        JSONObject object = (JSONObject) json;
        if (parent != null) {
            // remove all duplicated @xmlns from child
            for (Iterator entries = parent.entrySet().iterator(); entries.hasNext(); ) {
                Map.Entry entry = (Map.Entry) entries.next();
                String key = (String) entry.getKey();
                Object value = entry.getValue();
                if (key.startsWith("@xmlns") && value.equals(object.opt(key))) {
                    object.remove(key);
                }
            }
        }
        if (object.size() == 1 && object.has("#text")) {
            return object.get("#text");
        }
    }
    return json;
}


public void setNamespace(String prefix,String uri,String elementName){
    if (StringUtils.isBlank(uri)) {
        return;
    }
    if (prefix == null) {
        prefix = "";
    }
    if (StringUtils.isBlank(elementName)) {
        rootNamespace.clear();
        rootNamespace.put(prefix.trim(), uri.trim());
    } else {
        Map nameSpaces = (Map) namespacesPerElement.get(elementName);
        if (nameSpaces == null) {
            nameSpaces = new TreeMap();
            namespacesPerElement.put(elementName, nameSpaces);
        }
        nameSpaces.clear();
        nameSpaces.put(prefix, uri);
    }
}


}