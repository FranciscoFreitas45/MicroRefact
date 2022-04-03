package org.gliderwiki.DTO;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gliderwiki.util.parser.GliderTagParserMethodBasicTag;
import org.gliderwiki.util.parser.GliderTagParserMethodBoxTag;
import org.gliderwiki.util.parser.GliderTagParserMethodSpecialTag;
public class GliderTagParser {

 private GliderTagParserMethodBasicTag basic;

 private GliderTagParserMethodBoxTag box;

 private GliderTagParserMethodSpecialTag special;


@SuppressWarnings("rawtypes")
public Map<String,Object> getHtml(String str){
    List characherList = EsCharacterList();
    for (int i = 0; i < characherList.size(); i++) {
        str = getParsering(str, (EsCharacter) characherList.get(i));
    }
    Map<String, Object> parsingMap = new HashMap<String, Object>();
    parsingMap.put("htmltag", GliderTagPaserUtil.replaceHtmlToParsing(str));
    parsingMap.put("linkTagList", basic.linkTagList);
    parsingMap.put("noteTagList", box.noteTagList);
    parsingMap.put("h1TagList", special.h1TagList);
    parsingMap.put("graphCnt", special.graphCnt);
    return parsingMap;
}


public String getParsering(String str,EsCharacter ch){
    switch(ch) {
        case BOLD:
            str = basic.getBOLD(str);
            break;
        case ITALIC:
            str = basic.getITALIC(str);
            break;
        case STRIKE:
            str = basic.getSTRIKE(str);
            break;
        case SUBERSCRIPT:
            str = basic.getSUBERSCRIPT(str);
            break;
        case SUPERSCRIPT:
            str = basic.getSUPERSCRIPT(str);
            break;
        case HR:
            str = basic.getHR(str);
            break;
        case HIPERLINK:
            str = basic.getHIPERLINK(str);
            break;
        case IMG:
            str = basic.getIMG(str);
            break;
        case UNDERLINING:
            str = basic.getUNDERLINING(str);
            break;
        case BACKGROUND:
            str = basic.getBACKGROUND(str);
            break;
        case COLOR:
            str = basic.getCOLOR(str);
            break;
        case FONT:
            str = basic.getFONT(str);
            break;
        case FONTALIGN:
            str = basic.getFONTALIGN(str);
            break;
        case FONTSIZE:
            str = basic.getFONTSIZE(str);
            break;
        case ALERT:
            str = box.getALERT(str);
            break;
        case BOX:
            str = box.getBOX(str);
            break;
        case FIELD:
            str = box.getFIELD(str);
            break;
        case INFO:
            str = box.getINFO(str);
            break;
        case NOTE:
            str = box.getNOTE(str);
            break;
        case GRAPH:
            str = special.getGRAPH(str);
            break;
        case H1:
            str = special.getH1(str);
            break;
        case LINE:
            str = special.getLINE(str);
            break;
        case TABLE:
            str = special.getTABLE(str);
            break;
        case SYNTAXAFTER:
            str = special.getSYNTAX(str, false);
            break;
        case SYNTAXBEFORE:
            str = special.getSYNTAX(str, true);
            break;
        case BR:
            str = basic.getBR(str);
            break;
        case INDENT:
            str = basic.getINDENT(str);
            break;
        default:
            break;
    }
    return str;
}


}