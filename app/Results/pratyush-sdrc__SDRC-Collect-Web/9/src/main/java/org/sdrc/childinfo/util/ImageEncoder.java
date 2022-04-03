package org.sdrc.childinfo.util;
 import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.sdrc.childinfo.model.MapSvg;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class ImageEncoder {


public String createImgFromFile(String path,int cnt){
    // Create a JPEG transcoder
    JPEGTranscoder t = new JPEGTranscoder();
    // Set the transcoding hints.
    t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(.8));
    String date = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
    // Create the transcoder input.
    String svgURI = new File(path).toURI().toURL().toString();
    TranscoderInput input = new TranscoderInput(svgURI);
    System.out.println("Input==>" + input);
    // Create the transcoder output.
    // String imgPath= "d:/out_"+cnt+".jpg";
    // String rb = ResourceBundle.getBundle("spring/app").getString("ripas.jpgimage.path");
    // String imgPath = rb.concat("out_"+cnt+ ""+date+".jpg") ;
    String fileName = ResourceBundle.getBundle("spring/app").getString("jpgimage.path");
    OutputStream ostream = new FileOutputStream(fileName + "/map_" + date + ".jpg");
    TranscoderOutput output = new TranscoderOutput(ostream);
    System.out.println("output==>" + output);
    // Save the image.
    // try{
    t.transcode(input, output);
    // }
    // catch (Exception e){
    // System.out.println("transcode block" + cnt);
    // }
    // Flush and close the stream.
    ostream.flush();
    ostream.close();
    // System.exit(0);
    return fileName + "/map_" + date + ".jpg";
}


public String save(Document document){
    // Create a JPEGTranscoder and set its quality hint.
    // PNGTranscoder t=new PNGTranscoder();
    JPEGTranscoder t = new JPEGTranscoder();
    t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(.8));
    String date = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
    // Set the transcoder input and output.
    // String svgURI = new File("").toUR
    TranscoderInput input = new TranscoderInput(document);
    String fileName = ResourceBundle.getBundle("spring/app").getString("jpgimage.path");
    OutputStream ostream = new FileOutputStream(fileName + "/map_" + date + ".jpg");
    TranscoderOutput output = new TranscoderOutput(ostream);
    // Perform the transcoding.
    t.transcode(input, output);
    ostream.flush();
    ostream.close();
    return fileName + "/map_" + date + ".jpg";
}


public void main(String[] args){
    ImageEncoder encoder = new ImageEncoder();
    encoder.save(encoder.createDocumentDemo());
}


public Document createDocument(List<MapSvg> data){
    DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
    String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
    Document document = impl.createDocument(svgNS, "svg", null);
    Element root = document.getDocumentElement();
    root.setAttributeNS(null, "width", "550");
    root.setAttributeNS(null, "height", "480");
    Element e;
    for (MapSvg mapSvg : data) {
        e = document.createElementNS(svgNS, "path");
        e.setAttributeNS(null, "d", mapSvg.getD());
        e.setAttributeNS(null, "fill", mapSvg.getFill());
        e.setAttributeNS(null, "stroke", mapSvg.getStroke());
        // e.setAttributeNS(null, "preserveAspectRatio", map.get("preserveAspectRatio"));
        root.appendChild(e);
    }
    return document;
}


public Document createDocumentDemo(){
    DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
    String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
    Document document = impl.createDocument(svgNS, "svg", null);
    Element root = document.getDocumentElement();
    root.setAttributeNS(null, "width", "450");
    root.setAttributeNS(null, "height", "500");
    // Add some content to the document.
    Element e;
    e = document.createElementNS(svgNS, "rect");
    e.setAttributeNS(null, "x", "10");
    e.setAttributeNS(null, "y", "10");
    e.setAttributeNS(null, "width", "200");
    e.setAttributeNS(null, "height", "300");
    e.setAttributeNS(null, "style", "fill:rgb(253, 174, 97);stroke:rgb(255, 255, 255);stroke-width:4");
    root.appendChild(e);
    e = document.createElementNS(svgNS, "circle");
    e.setAttributeNS(null, "cx", "225");
    e.setAttributeNS(null, "cy", "250");
    e.setAttributeNS(null, "r", "100");
    e.setAttributeNS(null, "style", "fill:green;fill-opacity:.5");
    root.appendChild(e);
    return document;
}


}