package com.gbcom.common.im;
 import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import com.gbcom.common.im.exception.IMInitialException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
public class AbstractConfiguration {

 private  Logger logger;


public int removeWhitespace(Element e){
    NodeList children = e.getChildNodes();
    if (children.getLength() < 1) {
        return 0;
    }
    int count = 0;
    for (int i = children.getLength() - 1; i >= 0; i--) {
        Node child = children.item(i);
        if (child instanceof Text && ((Text) child).getData().trim().length() == 0) {
            e.removeChild(child);
            count++;
        } else if (child instanceof Element) {
            count += removeWhitespace((Element) child);
        }
    }
    return count;
}


public Element configure(File file){
    try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        Element root = doc.getDocumentElement();
        int whiteSpacesRemoved = this.removeWhitespace(root);
        logger.debug("White Spaces Removed: " + whiteSpacesRemoved);
        return root;
    } catch (Exception e) {
        throw new IMInitialException("exception while parsing " + file.getName().toString());
    }
}


}