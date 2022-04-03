package com.gbcom.system.utils;
 import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
public class XmlFileUtil {


@SuppressWarnings("unchecked")
public T unmarshallerObjectFromXml(Class<T> type,InputStream ins){
    try {
        JAXBContext context = JAXBContext.newInstance(type);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        T obj = (T) unmarshaller.unmarshal(ins);
        return obj;
    } catch (JAXBException e) {
        e.printStackTrace();
    }
    return null;
}


public void marshallerObjectToXml(T obj,Class<T> type){
    try {
        JAXBContext context = JAXBContext.newInstance(type);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(obj, System.out);
    } catch (JAXBException e) {
        e.printStackTrace();
    }
}


}