package org.opengeoportal.utilities;
 import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class OgpXmlUtils {

 final  Logger logger;


public Map<String,String> getSiblingValues(Node currentNode,Set<String> siblingTags){
    Map<String, String> responseMap = new HashMap<String, String>();
    String testString = currentNode.getLocalName().toLowerCase();
    for (String tagName : siblingTags) {
        if (testString.contains(tagName.toLowerCase())) {
            responseMap.put(tagName, currentNode.getTextContent().trim());
            return responseMap;
        }
    }
    return responseMap;
}


public String getAttribute(Node currentNode,String attributeName){
    NamedNodeMap attrs = currentNode.getAttributes();
    return attrs.getNamedItem(attributeName).getNodeValue().trim();
}


public Document getDocument(InputStream inputStream){
    try {
        // Create a factory
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        // dtd isn't always available; would be nice to attempt to validate
        documentBuilderFactory.setValidating(false);
        documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        documentBuilderFactory.setNamespaceAware(true);
        // Use document builder factory
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        // Parse the document
        Document document = builder.parse(inputStream);
        return document;
    } finally {
        IOUtils.closeQuietly(inputStream);
    }
}


public Node getChildNode(Node parent,String tagName){
    NodeList children = parent.getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
        Node currentNode = children.item(i);
        if (currentNode.getLocalName().equalsIgnoreCase(tagName)) {
            return currentNode;
        }
    }
    throw new Exception("Child Node ['" + tagName + "'] not found.");
}


public String alwaysGetName(Node node){
    if (node.equals(null)) {
        throw new Exception("This node is null.");
    }
    String localName = "";
    try {
        if (!node.getLocalName().equals(null)) {
            localName = node.getLocalName();
        }
    } catch (NullPointerException e) {
        localName = node.getNodeName();
    }
    return localName;
}


public Map<String,String> getDesiredChildrenValues(Node parent,Set<String> childTags){
    Map<String, String> responseMap = new HashMap<String, String>();
    NodeList children = parent.getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
        Node child = children.item(i);
        responseMap.putAll(getSiblingValues(child, childTags));
    }
    return responseMap;
}


public List<String> getChildValuesList(Node parentNode,String tagName){
    List<String> values = new ArrayList<String>();
    NodeList children = parentNode.getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
        Node currentNode = children.item(i);
        if (currentNode.getLocalName().equalsIgnoreCase(tagName)) {
            values.add(currentNode.getTextContent().trim());
        }
    }
    return values;
}


public void handleServiceException(Node baseNode){
    /*
		  * 
		  * <ows:ExceptionReport version="1.0.0"
  				xsi:schemaLocation="http://www.opengis.net/ows http://data.fao.org/maps/schemas/ows/1.0.0/owsExceptionReport.xsd"
  				xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ows="http://www.opengis.net/ows">
  				<ows:Exception exceptionCode="NoApplicableCode">
    				<ows:ExceptionText>java.lang.NullPointerException null</ows:ExceptionText>
  				</ows:Exception>
			</ows:ExceptionReport>
		  */
    logger.debug("Full response: " + baseNode.getTextContent());
    String errorMessage = "";
    if (alwaysGetName(baseNode).toLowerCase().contains("serviceexception")) {
        if (baseNode.hasChildNodes()) {
            for (int i = 0; i < baseNode.getChildNodes().getLength(); i++) {
                String nodeName = alwaysGetName(baseNode.getChildNodes().item(i));
                if (nodeName.equals("ServiceException")) {
                    logger.error("Service Exception:");
                    errorMessage += baseNode.getChildNodes().item(i).getTextContent().trim();
                }
            }
        } else {
            errorMessage += "poorly formed service exception";
        }
    } else if (alwaysGetName(baseNode).toLowerCase().contains("exception")) {
        try {
            errorMessage += baseNode.getFirstChild().getAttributes().getNamedItem("exceptionCode").getTextContent();
        } catch (Exception e) {
            errorMessage += "Abridged response: " + baseNode.getTextContent().trim();
        }
    } else {
        return;
    }
    if (errorMessage.length() > 1024) {
        errorMessage = errorMessage.substring(0, 1023);
    }
    throw new Exception(errorMessage);
}


}