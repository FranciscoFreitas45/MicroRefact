package org.gliderwiki.util.parser;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gliderwiki.util.GliderTagPaserUtil;
public class GliderTagParserMethodBasicTag {

 public  List<Map<String,Object>> linkTagList;


public String getUNDERLINING(String str){
    str = GliderTagPaserUtil.replaceAllTag(str, "\\_{2}([\\w\\W]+?)\\_{2}", "<U>$1</U>");
    return str;
}


public String getSUBERSCRIPT(String str){
    str = GliderTagPaserUtil.replaceAllTag(str, "\\[sb\\]([\\w\\W]+?)\\[sb\\]", "<sub>$1</sub>");
    return str;
}


public String getFONTALIGN(String str){
    String patternTxt = "\\[align\\:(left|right|center|justify)\\]([\\w\\W]+?)\\[align\\]";
    String parserTag = null;
    while (GliderTagPaserUtil.getMatchFind(str, patternTxt)) {
        parserTag = GliderTagPaserUtil.getFirstTag(str, patternTxt);
        String fontalignTxt = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$1");
        String contents = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$2");
        String fontalign = null;
        if ("left".equals(fontalignTxt)) {
            fontalign = "left";
        } else if ("center".equals(fontalignTxt)) {
            fontalign = "center";
        } else if ("right".equals(fontalignTxt)) {
            fontalign = "right";
        } else if ("justify".equals(fontalignTxt)) {
            fontalign = "justify";
        }
        String html = "<div style='text-align:" + fontalign + "'>" + contents + "</div>";
        str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, html);
    }
    return str;
}


public String getITALIC(String str){
    str = GliderTagPaserUtil.replaceAllTag(str, "([^http:])(\\/{2}([\\w\\W]+?)\\/{2})", "$1<I>$3</I>");
    return str;
}


public String getIMG(String str){
    String patternTxt = "\\[!\\|(.*?)(\\|(.*?))?\\](.*?)\\[!\\]";
    String parserTag = null;
    while (GliderTagPaserUtil.getMatchFind(str, patternTxt)) {
        parserTag = GliderTagPaserUtil.getFirstTag(str, patternTxt);
        // url, 설명
        String url = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$1");
        String title = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$3");
        // 넓이
        String patternSub = "\\[!s\\|(.*?)(\\|(.*?))?\\]";
        String width = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternSub, "$1");
        String height = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternSub, "$3");
        String html = null;
        if ("".equals(width) && "".equals(height)) {
            html = "<img alt=\"" + title + "\" src=\"" + url + "\" >";
        } else if (!"".equals(width) && "".equals(height)) {
            html = "<img alt=\"" + title + "\" src=\"" + url + "\" width=\"" + width + "\" >";
        } else if ("".equals(width) && !"".equals(height)) {
            html = "<img alt=\"" + title + "\" src=\"" + url + "\" height=\"" + height + "\" >";
        } else {
            html = "<img alt=\"" + title + "\" src=\"" + url + "\" width=\"" + width + "\" height=\"" + height + "\" >";
        }
        str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, html);
    }
    return str;
}


public String getBOLD(String str){
    str = GliderTagPaserUtil.replaceAllTag(str, "\\*{2}([\\w\\W]+?)\\*{2}", "<B>$1</B>");
    return str;
}


public String getHIPERLINK(String str){
    String patternTxt = "\\[(@:)(.*?)(\\|(.*?))?\\]";
    String parserTag = null;
    Map<String, Object> tagMap = null;
    while (GliderTagPaserUtil.getMatchFind(str, patternTxt)) {
        tagMap = new HashMap<String, Object>();
        parserTag = GliderTagPaserUtil.getFirstTag(str, patternTxt);
        String url = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$2");
        String title = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$4");
        // 설명구문이 없으면 url을 대신 넣는다.
        if (title == null || "".equals(title)) {
            title = url;
        }
        String html = "<a href=\"" + url + "\" target=\"_blink\">" + title + "</a>";
        str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, html);
        tagMap.put("tagUrl", url);
        tagMap.put("tagTitle", title);
        linkTagList.add(tagMap);
    }
    return str;
}


public String getINDENT(String str){
    str = GliderTagPaserUtil.replaceAllTag(str, "\\[t\\]([\\w\\W]+?)\\[t\\]", "<blockquote>$1</blockquote>");
    return str;
}


public String getSUPERSCRIPT(String str){
    str = GliderTagPaserUtil.replaceAllTag(str, "\\[sp\\]([\\w\\W]+?)\\[sp\\]", "<sup>$1</sup>");
    return str;
}


public String getSTRIKE(String str){
    str = GliderTagPaserUtil.replaceAllTag(str, "\\[d\\]([\\w\\W]+?)\\[d\\]", "<del>$1</del>");
    return str;
}


public String getBACKGROUND(String str){
    str = GliderTagPaserUtil.replaceAllTag(str, "\\[bg\\|(.*?)\\]([\\w\\W]+?)\\[bg\\]", "<span style='background:#$1'>$2</span>");
    return str;
}


public String getCOLOR(String str){
    str = GliderTagPaserUtil.replaceAllTag(str, "\\[color\\|(.*?)\\]([\\w\\W]+?)\\[color\\]", "<font color='#$1'>$2</font>");
    return str;
}


public String getFONTSIZE(String str){
    str = GliderTagPaserUtil.replaceAllTag(str, "\\[size\\|(.*?)\\]([\\w\\W]+?)\\[size\\]", "<span style=\"font-size: $1px;\">$2\r\n</span>");
    return str;
}


public String getFONT(String str){
    String patternTxt = "\\[font\\|(.*?)\\]([\\w\\W]+?)\\[font\\]";
    String parserTag = null;
    while (GliderTagPaserUtil.getMatchFind(str, patternTxt)) {
        parserTag = GliderTagPaserUtil.getFirstTag(str, patternTxt);
        String fontTxt = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$1");
        String contents = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$2");
        String font = null;
        if ("돋움".equals(fontTxt)) {
            font = "dotum";
        } else if ("굴림".equals(fontTxt)) {
            font = "gulim";
        } else if ("궁서".equals(fontTxt)) {
            font = "gungsuh";
        } else if ("바탕".equals(fontTxt)) {
            font = "batang";
        }
        String html = "<span style=\"font-family:'" + fontTxt + "','" + font + "'\">" + contents + "\r\n</span>";
        str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, html);
    }
    return str;
}


public String getHR(String str){
    str = GliderTagPaserUtil.replaceAllTag(str, "\\[hr\\]", "<hr>");
    return str;
}


public String getHTML_TAG(String str){
    str = str.replaceAll("<html>", "&lt;html&gt;");
    str = str.replaceAll("</html>", "&lt;/html&gt;");
    str = str.replaceAll("<body>", "&lt;body&gt;");
    str = str.replaceAll("</body>", "&lt;/body&gt;");
    str = str.replaceAll("</body>", "&lt;/body&gt;");
    str = str.replaceAll("<head>", "&lt;head&gt;");
    str = str.replaceAll("</head>", "&lt;/head&gt;");
    str = str.replaceAll("<title>", "&lt;title&gt;");
    str = str.replaceAll("</title>", "&lt;/title&gt;");
    str = str.replaceAll("<meta", "&lt;meta");
    // str = str.replaceAll("<link", "&lt;link");
    // str = str.replaceAll("<script", "&lt;script");
    // str = str.replaceAll("</script>", "&lt;/script&gt;");
    str = str.replaceAll("<form", "&lt;form");
    str = str.replaceAll("</form>", "&lt;/form&gt;");
    str = str.replaceAll("<footer", "&lt;footer");
    str = str.replaceAll("</footer>", "&lt;/footer>");
    return str;
}


public String getBR(String str){
    /*String[] brArray = str.split("\r\n");
		str = "";
		
		for(int i=0; i< brArray.length; i++){
			if( "".equals(brArray[i].trim() ) ){
				str += brArray[i]+"\r\n";
			}else{
				str += brArray[i]+"\n";
			}
		}
		str = str + "\r\n";*/
    str = GliderTagPaserUtil.HTMLBREncode(str);
    return str;
}


}