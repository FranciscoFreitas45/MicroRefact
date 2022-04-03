package org.gliderwiki.util;
 import java.util.LinkedList;
import org.gliderwiki.util.DiffMatchPatch.Diff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DiffEngine {

 private  Logger logger;


public String diffInsertHtml(LinkedList<Diff> diffs){
    StringBuffer insertList = new StringBuffer();
    for (Diff aDiff : diffs) {
        String text = aDiff.text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\r\n", "<br />").replace("\r", "<br />").replace("\n", "<br />");
        switch(aDiff.operation) {
            case INSERT:
                insertList.append("<ins style=\"background:#e6ffe6;\">" + text + "</ins>");
                break;
            case EQUAL:
                insertList.append("<span>" + text + "</span>");
                break;
            default:
                break;
        }
    }
    return insertList.toString();
}


public String diffDeleteHtml(LinkedList<Diff> diffs){
    StringBuffer deleteList = new StringBuffer();
    for (Diff aDiff : diffs) {
        String text = aDiff.text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\r\n", "<br />").replace("\r", "<br />").replace("\n", "<br />");
        switch(aDiff.operation) {
            case DELETE:
                deleteList.append("<del style=\"background:#ffe6e6;\">" + text + "</del>");
                break;
            case EQUAL:
                deleteList.append("<span>" + text + "</span>");
                break;
            default:
                break;
        }
    }
    return deleteList.toString();
}


public LinkedList<Diff> diffMain(String text1,String text2){
    DiffMatchPatch dmp = new DiffMatchPatch();
    LinkedList<Diff> diffs = dmp.diff_main(text1, text2);
    dmp.diff_cleanupSemantic(diffs);
    return diffs;
}


}