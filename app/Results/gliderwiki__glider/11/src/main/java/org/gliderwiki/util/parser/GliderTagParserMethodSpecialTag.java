package org.gliderwiki.util.parser;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.gliderwiki.util.GliderTagPaserUtil;
public class GliderTagParserMethodSpecialTag {

 public  List<String> syntaxList;

 public  Integer graphCnt;

 public  List<Object> h1TagList;


public String getH1(String str){
    String patternTxt = "";
    String[] htagStr = str.split("\r\n");
    str = "";
    Map<String, String> hTagMap = null;
    for (int i = 0; i < htagStr.length; i++) {
        hTagMap = new HashMap<String, String>();
        patternTxt = "(h1\\.(\\s{0,}))(.*)?";
        if (GliderTagPaserUtil.getMatchFind(htagStr[i], patternTxt)) {
            hTagMap.put("tag", "h1");
            hTagMap.put("tagVal", GliderTagPaserUtil.getFirstReturnTag(htagStr[i], patternTxt, "$3").trim().replaceAll("\"", "&#34;"));
            htagStr[i] = GliderTagPaserUtil.replaceFirstTag(htagStr[i], patternTxt, "<h2><a id=\"" + hTagMap.get("tagVal") + "\" name=\"" + hTagMap.get("tagVal") + "\">$3</a></h2>");
        }
        patternTxt = "(h2\\.(\\s{0,}))(.*)?";
        if (GliderTagPaserUtil.getMatchFind(htagStr[i], patternTxt)) {
            hTagMap.put("tag", "h2");
            hTagMap.put("tagVal", GliderTagPaserUtil.getFirstReturnTag(htagStr[i], patternTxt, "$3").trim().replaceAll("\"", "&#34;"));
            htagStr[i] = GliderTagPaserUtil.replaceFirstTag(htagStr[i], patternTxt, "<h3><a id=\"" + hTagMap.get("tagVal") + "\" name=\"" + hTagMap.get("tagVal") + "\">$3</a></h3>");
        }
        patternTxt = "(h3\\.(\\s{0,}))(.*)?";
        if (GliderTagPaserUtil.getMatchFind(htagStr[i], patternTxt)) {
            hTagMap.put("tag", "h3");
            hTagMap.put("tagVal", GliderTagPaserUtil.getFirstReturnTag(htagStr[i], patternTxt, "$3").trim().replaceAll("\"", "&#34;"));
            htagStr[i] = GliderTagPaserUtil.replaceFirstTag(htagStr[i], patternTxt, "<h4><a id=\"" + hTagMap.get("tagVal") + "\" name=\"" + hTagMap.get("tagVal") + "\">$3</a></h4>");
        }
        if (!hTagMap.isEmpty()) {
            h1TagList.add(hTagMap);
        }
        str += htagStr[i] + "\r\n";
    }
    return str;
}


public String getSYNTAX(String str,Boolean falg){
    String patternTxt = null;
    if (falg) {
        patternTxt = "(\\[syntax\\])([\\w\\W]*?)(\\[syntax\\])";
    } else {
        patternTxt = "(\\[SONJSsyntax\\])([\\w\\W]*?)(\\[SONJSsyntax\\])";
    }
    Integer syntaxCnt = 0;
    while (GliderTagPaserUtil.getMatchFind(str, patternTxt)) {
        if (falg) {
            // syntax ????????? ???????????? ????????????.
            String syntax = GliderTagPaserUtil.getFirstTag(str, patternTxt);
            syntax = GliderTagPaserUtil.getFirstReturnTag(syntax, patternTxt, "$2");
            syntax = syntax.replaceAll("<", "&lt;");
            syntax = syntax.replaceAll(">", "&gt;");
            syntaxList.add(syntax);
            // ????????? syntax??? ????????? ?????????.
            str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, "[SONJSsyntax][SONJSsyntax]");
        } else {
            // syntax ????????? ?????? ?????? ?????????.
            str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, "<pre class=\"brush: js\">" + syntaxList.get(syntaxCnt).toString().replaceAll("[$]", "\\\\\\$") + "</pre>");
            syntaxCnt++;
        }
    }
    return str;
}


public String getTABLE(String str){
    String patternTxt = "";
    String[] tableStr = str.split("\r\n");
    str = "";
    Integer tableFlag = 0;
    for (int i = 0; i < tableStr.length; i++) {
        // || ????????? | ????????? ???????????? ????????????.(????????? ??????????????????????????? ??????)
        Integer tableCnt = 0;
        for (int cnt = 0; cnt < 2; cnt++) {
            String beforetag = null;
            String aftertag = null;
            String parsing = null;
            if (cnt == 0) {
                beforetag = "\\\\|\\|";
                aftertag = "\\|\\|";
                parsing = "th";
            } else {
                beforetag = "\\|";
                aftertag = "\\|";
                parsing = "td";
            }
            // || ??????(th)??? ????????? ???????????? ????????? ?????????????????? ????????????.
            patternTxt = "((^" + beforetag + ")(.*)(" + aftertag + "))";
            String html = null;
            if (GliderTagPaserUtil.getMatchFind(tableStr[i], patternTxt, Pattern.MULTILINE)) {
                // ??????????????? ????????? ??????????????? ????????????.
                html = GliderTagPaserUtil.getFirstReturnTag(tableStr[i], patternTxt, "$1", Pattern.MULTILINE);
                // ??????????????? ???????????? || ??? || ????????? ?????? ????????? th(tr) html?????? ????????????.
                // 1. row ?????? ?????? ??????
                html = GliderTagPaserUtil.replaceAllTag(html, "(?!" + beforetag + ")([\\s]{0,}?)(row:(\\w+)#(.*?))(?=" + aftertag + ")", "<" + parsing + " rowspan=\"$3\">$1$4</" + parsing + ">\n");
                // 2. col ?????? ?????? ??????
                html = GliderTagPaserUtil.replaceAllTag(html, "(?!" + beforetag + ")([\\s]{0,}?)(col:(\\w+)#(.*?))(?=" + aftertag + ")", "<" + parsing + " colspan=\"$3\">$1$4</" + parsing + ">\n");
                // 3. ?????? ?????? ?????? ??????
                html = GliderTagPaserUtil.replaceAllTag(html, "(?!" + beforetag + ")(.*?)(?=" + aftertag + ")", "<" + parsing + " class=\"\">$1</" + parsing + ">\n");
                // ??????????????? ???????????? || ????????? ????????????.
                html = GliderTagPaserUtil.replaceAllTag(html, "(" + beforetag + ")(?=.*)", "", Pattern.MULTILINE);
                html = "<tr>\n" + html + "</tr>\n";
                // ????????? ?????? ????????? ????????????.
                tableStr[i] = GliderTagPaserUtil.replaceAllTag(tableStr[i], patternTxt, html, Pattern.MULTILINE);
                // ???????????????????????? ????????? ?????? ???????????? ????????????.
                tableCnt++;
            }
        }
        if (tableCnt > 0) {
            tableFlag++;
        }
        // ?????????????????? ?????????????????? <table>????????? ?????????.
        if (tableCnt > 0 && tableFlag == 1) {
            tableStr[i] = "<table>\n" + tableStr[i];
        // table ????????? ???????????? ??????(non??????), ????????????????????? 0?????? ?????? ?????????????????? ????????? ??????.
        } else if (tableCnt == 0 && tableFlag > 0) {
            tableStr[i] = "</table>\n" + tableStr[i];
            tableFlag = 0;
        // ( tableStr.length - 1 == i &&  tableFlag > 0  )  ==> ????????? ?????????????????? ?????????????????? ????????? ???????????? ????????? ????????????.
        } else if (tableStr.length - 1 == i && tableFlag > 0) {
            tableStr[i] = tableStr[i] + "\n</table>\n";
            tableFlag = 0;
        // table ????????? ????????????.
        } else if (tableCnt == 0 && tableFlag == 0) {
            tableStr[i] = tableStr[i] + "\r\n";
        }
        str += tableStr[i];
    }
    return str;
}


public String getGRAPH(String str){
    String patternTxt = "(\\[%\\](.*?)\\[%\\])";
    Integer graphCnt = 0;
    while (GliderTagPaserUtil.getMatchFind(str, patternTxt, Pattern.DOTALL)) {
        graphCnt++;
        String parsingTxt = GliderTagPaserUtil.getFirstReturnTag(str, patternTxt, "$2", Pattern.DOTALL);
        String pie = GliderTagPaserUtil.getFirstReturnTag(parsingTxt, "^pie\\[(.*)\\]", "$1", Pattern.MULTILINE);
        parsingTxt = GliderTagPaserUtil.replaceFirstTag(parsingTxt, "^pie\\[(.*)\\]", "", Pattern.MULTILINE);
        String bar = GliderTagPaserUtil.getFirstReturnTag(parsingTxt, "^bar\\[(.*)\\]", "$1", Pattern.MULTILINE);
        parsingTxt = GliderTagPaserUtil.replaceFirstTag(parsingTxt, "^bar\\[(.*)\\]", "", Pattern.MULTILINE);
        String graphHtml = "<div style=\"clear:left;height: 50px;\"></div>\n" + "<script type=\"text/javascript\">\n" + "function graph" + graphCnt + "(){\n" + "var data" + graphCnt + " = [\n" + parsingTxt + "\n" + "];\n";
        if (!"".equals(pie)) {
            graphHtml = "<div id='" + pie + "'></div>\n" + graphHtml;
            pie = "&#36;(\"#" + pie + "\").pieChart(data" + graphCnt + ",300,\"pie\"); \n";
        }
        if (!"".equals(bar)) {
            graphHtml = "<div id='" + bar + "'></div>\n" + graphHtml;
            bar = "&#36;(\"#" + bar + "\").pieChart(data" + graphCnt + ",300,\"bar\"); \n";
        }
        graphHtml = graphHtml + pie + bar + "};\n";
        graphHtml += "</script>";
        graphHtml = graphHtml.replaceAll("\r\n", "\n");
        str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, graphHtml, Pattern.DOTALL);
        str = str.replaceAll("[&][#]36;", "\\$");
    }
    this.graphCnt = graphCnt;
    return str;
}


public String getLINE(String str){
    str = linePaser(str, "#");
    str = linePaser(str, "-");
    return str;
}


public String getSYNTAX_TO_HTML(String str,Boolean falg){
    String patternTxt = null;
    if (falg) {
        patternTxt = "(\\[syntax\\])([\\w\\W]*?)(\\[syntax\\])";
    } else {
        patternTxt = "(\\[SONJSsyntax\\])([\\w\\W]*?)(\\[SONJSsyntax\\])";
    }
    Integer syntaxCnt = 0;
    while (GliderTagPaserUtil.getMatchFind(str, patternTxt)) {
        if (falg) {
            // syntax ????????? ???????????? ????????????.
            String syntax = GliderTagPaserUtil.getFirstTag(str, patternTxt);
            syntax = GliderTagPaserUtil.getFirstReturnTag(syntax, patternTxt, "$2");
            syntax = syntax.replaceAll("<", "&lt;");
            syntax = syntax.replaceAll(">", "&gt;");
            syntax = GliderTagPaserUtil.ReplaceHTMLSpecialCharsDollar(syntax);
            syntaxList.add(syntax);
            // ????????? syntax??? ????????? ?????????.
            str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, "[SONJSsyntax][SONJSsyntax]");
        } else {
            // syntax ????????? ?????? ?????? ?????????.
            str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, "[syntax]" + syntaxList.get(syntaxCnt).toString() + "[syntax]");
            syntaxCnt++;
        }
    }
    return str;
}


public String linePaser(String str,String patternMainTxt){
    // wiki ????????? ????????? ????????? ????????? ?????????.
    String[] strLine = str.split("\r\n");
    // patternMainTxt ?????? ?????? tag ?????? ????????????.
    String tag = null;
    str = "";
    if ("-".equals(patternMainTxt.trim())) {
        tag = "ol";
    } else {
        tag = "ul";
    }
    String lvl3 = patternMainTxt + patternMainTxt + patternMainTxt + patternMainTxt;
    String lvl2 = patternMainTxt + patternMainTxt + patternMainTxt;
    String lvl1 = patternMainTxt + patternMainTxt;
    Integer chk = 0;
    String afterPattern = null;
    String beforePattern = null;
    for (int i = 0; i < strLine.length; i++) {
        beforePattern = afterPattern;
        if (GliderTagPaserUtil.getMatchFind(strLine[i], "(^" + lvl3 + ")(.*)")) {
            afterPattern = lvl3;
            strLine[i] = GliderTagPaserUtil.getFirstReturnTag(strLine[i], "(^" + lvl3 + ")(.*)", "<li class=\"lv3\">$2</li>");
        } else if (GliderTagPaserUtil.getMatchFind(strLine[i], "(^" + lvl2 + ")(.*)")) {
            afterPattern = lvl2;
            strLine[i] = GliderTagPaserUtil.getFirstReturnTag(strLine[i], "(^" + lvl2 + ")(.*)", "<li class=\"lv2\">$2</li>");
        } else if (GliderTagPaserUtil.getMatchFind(strLine[i], "(^" + lvl1 + ")(.*)")) {
            afterPattern = lvl1;
            strLine[i] = GliderTagPaserUtil.getFirstReturnTag(strLine[i], "(^" + lvl1 + ")(.*)", "<li class=\"lv1\">$2</li>");
        } else {
            afterPattern = null;
        }
        // ??????
        if (afterPattern != null && beforePattern == null) {
            chk++;
            strLine[i] = "<" + tag + ">\n" + strLine[i];
        // ??????depth??? ???????????? ????????? ?????????.
        } else if (afterPattern != null && afterPattern.length() > beforePattern.length()) {
            chk++;
            strLine[i] = "<" + tag + ">\n" + strLine[i];
        // ??????depth??? ???????????? ????????? ????????????.
        } else if (afterPattern != null && beforePattern.length() - afterPattern.length() > 0) {
            Integer depth = beforePattern.length() - afterPattern.length();
            for (int j = 0; j < depth; j++) {
                chk = chk - 1;
                strLine[i] = "</" + tag + ">\n" + strLine[i];
            }
        // Line ????????? ????????? ????????? ?????? ????????? ????????????.
        } else if (afterPattern == null && beforePattern != null) {
            for (int j = 0; j < chk; j++) {
                strLine[i] = "</" + tag + ">\n" + strLine[i];
            }
            chk = 0;
        // Line ????????? ?????????.
        } else if (afterPattern == null && beforePattern == null) {
            strLine[i] = strLine[i] + "\r\n";
        }
        str += strLine[i];
    }
    return str;
}


}