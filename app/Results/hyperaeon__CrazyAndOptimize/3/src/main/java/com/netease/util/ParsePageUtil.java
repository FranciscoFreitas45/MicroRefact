package com.netease.util;
 import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.apache.commons.collections.CollectionUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.lexer.PageAttribute;
import org.htmlparser.tags.Bullet;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.InputTag;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.springframework.util.StringUtils;
public class ParsePageUtil {

 private  String ENCODE;

 private  String PATH;


public String parseReportStatus(String page){
    String result = "";
    if (StringUtils.isEmpty(page)) {
        return result;
    }
    String[] lines = page.split("\n");
    for (String line : lines) {
        if (line.contains(ConstantUtil.CLASS_REPORT_STATUS_FONT) && line.indexOf("(") > 0) {
            result = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
        }
    }
    return result;
}


public Map<String,String> parseInput(String regPage){
    if (StringUtils.isEmpty(regPage)) {
        return null;
    }
    Map<String, String> nameValue = null;
    NodeList nodeList = parseTag(regPage, InputTag.class);
    List<InputTag> inputTagList = new ArrayList<InputTag>();
    nameValue = new HashMap<String, String>();
    for (int i = 0; i < nodeList.size(); i++) {
        if (nodeList.elementAt(i) instanceof InputTag) {
            InputTag inputTag = (InputTag) nodeList.elementAt(i);
            inputTagList.add(inputTag);
            Vector<PageAttribute> vector = inputTag.getAttributesEx();
            buildNameValueMap(vector, nameValue);
        }
    }
    return nameValue;
}


public String parseDivMessage(String page){
    NodeList nodeList = parseTag(page, Div.class);
    String result = "";
    if (nodeList != null) {
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.elementAt(i);
            if (node instanceof Div) {
                Div div = (Div) node;
                if (ConstantUtil.NOTICE_DIV1.equals(div.getAttribute(ConstantUtil.CLASS))) {
                    result = div.toPlainTextString().trim();
                }
            }
        }
    }
    return result;
}


public String openFile(String fileName){
    StringBuilder builder = new StringBuilder();
    try {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)), ENCODE));
        String content = "";
        while ((content = br.readLine()) != null) {
            builder.append(content + "\n");
        }
        br.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return builder.toString();
}


public String extractResponseAfterSubmitQuestion(String responsePage){
    String result = "";
    try {
        Parser parser = Parser.createParser(responsePage, ConstantUtil.CHARSET);
        NodeFilter nodeFilter = new NodeClassFilter(Div.class);
        AndFilter andFilter = new AndFilter();
        andFilter.setPredicates(new NodeFilter[] { nodeFilter });
        NodeList nodeList = parser.parse(andFilter);
        if (nodeList != null) {
            for (int i = 0; i < nodeList.size(); i++) {
                Node node = nodeList.elementAt(i);
                if (node instanceof Div) {
                    String className = ((Div) node).getAttribute(ConstantUtil.CLASS);
                    if (ConstantUtil.TABLE_MID_BK.equals(className)) {
                        result = node.toPlainTextString();
                    }
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result.trim();
}


public String parseLoginResponse(String page){
    NodeList nodeList = parseTag(page, Span.class);
    String result = "";
    if (nodeList != null) {
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.elementAt(i);
            if (node instanceof Span) {
                Span span = (Span) node;
                NodeList subNodeList = span.getChildren();
                if (subNodeList != null) {
                    for (int j = 0; j < subNodeList.size(); j++) {
                        Node subNode = subNodeList.elementAt(j);
                        if (subNode instanceof Span) {
                            Span subSpan = (Span) subNode;
                            if (ConstantUtil.ERROR_FILED.equals(subSpan.getAttribute("id")) || ConstantUtil.LOGIN_ERROR_MSG_ID.equals(subSpan.getAttribute("id"))) {
                                result = subSpan.toPlainTextString();
                                if (!StringUtils.isEmpty(result)) {
                                    // 有错误信息，则直接返回
                                    return result;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return result;
}


public Map<String,List<String>> extractQuestion(String questionPage){
    Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
    NodeList nodeList = parseTag(questionPage, Bullet.class);
    if (nodeList != null) {
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.elementAt(i);
            if (node instanceof Bullet) {
                // 包含一个问题及问题对应的选项
                NodeList subNodeList = node.getChildren();
                if (subNodeList != null) {
                    int count = 0;
                    String currentKey = "";
                    for (int j = 0; j < subNodeList.size(); j++) {
                        // 包含<p>及其下级元素
                        Node subNode = subNodeList.elementAt(j);
                        // 包含<p>及其下级元素
                        subNode.getLastChild();
                        if (subNode instanceof ParagraphTag) {
                            // 包含<span>元素，<span>元素包括问题和选项
                            NodeList tripleList = subNode.getChildren();
                            if (tripleList != null) {
                                for (int k = 0; k < tripleList.size(); k++) {
                                    Node tripleNode = tripleList.elementAt(k);
                                    if (tripleNode instanceof Span) {
                                        if (count == 0) {
                                            String content = ((Span) tripleNode).getChildrenHTML();
                                            currentKey = content.substring(content.indexOf(ConstantUtil.COLON) + 1);
                                            map.put(currentKey, new ArrayList<String>());
                                        } else {
                                            List<String> list = map.get(currentKey);
                                            list.add(((Span) tripleNode).getChildrenHTML());
                                        }
                                        count++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return map;
}


public void main(String[] args){
// Map<String, String> map = parseInput("aaa");
// if (map != null) {
// for (Map.Entry<String, String> entrySet : map.entrySet()) {
// System.out.println(entrySet.getKey() + ": " + entrySet.getValue());
// }
// }
// 解析error_field
// System.out.println(parseErrorField(openFile(PATH)));
// System.out.println(parseLoginResponse(openFile(PATH)));
// System.out.println(parseMessage(openFile(PATH)));
// System.out.println(parseMessage(openFileAll(PATH)));
/**
 * 解析问题页面
 */
// String questionPage = openFile(PATH);
// Map<String, List<String>> map = extractQuestion(questionPage);
// for (Map.Entry<String, List<String>> m : map.entrySet()) {
// System.out.println(m.getKey());
// List<String> list = m.getValue();
// for (String s : list) {
// System.out.print(s + "\t");
// }
// System.out.println();
// }
// 
// Map<String, String> map2 = parseInput(questionPage);
// for (Map.Entry<String, String> m : map2.entrySet()) {
// System.out.println(m.getKey() + m.getValue());
// }
/**
 * 解析提交问题页面后的响应页面
 */
// String responsePage = openFile(PATH);
// System.out.println(extractResponseAfterSubmitQuestion(responsePage));
/**
 * 解析“获取信用信息”页面
 */
// String responsePage = openFile(PATH);
// parseRedioType(responsePage);
/**
 * 解析信息服务 > 申请信用信息页面
 */
// String responsePage = openFile(PATH);
// System.out.println(parseReportStatus(responsePage));
}


public NodeList parseTag(String page,Class<?> clazz){
    NodeList nodeList = null;
    try {
        Parser parser = Parser.createParser(page, ConstantUtil.CHARSET);
        NodeFilter spanFilter = new NodeClassFilter(clazz);
        AndFilter andFilter = new AndFilter();
        andFilter.setPredicates(new NodeFilter[] { spanFilter });
        nodeList = parser.parse(andFilter);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return nodeList;
}


public void buildNameValueMap(Vector<PageAttribute> vector,Map<String,String> nameValue){
    String nameText = "";
    String valueText = "";
    if (CollectionUtils.isNotEmpty(vector)) {
        for (PageAttribute pageAttribute : vector) {
            String attributeName = pageAttribute.getName();
            String attributeValue = pageAttribute.getValue();
            if (attributeName != null) {
                if (attributeName.contains("name")) {
                    nameText = attributeValue;
                }
                if (attributeName.contains("value")) {
                    valueText = attributeValue;
                }
            }
        }
    }
    nameValue.put(nameText, valueText);
}


public String openFileAll(String fileName){
    File file = new File(fileName);
    Long fileLength = file.length();
    byte[] fileContent = new byte[fileLength.intValue()];
    String resultContent = "";
    try {
        FileInputStream in = new FileInputStream(file);
        in.read(fileContent);
        in.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    try {
        resultContent = new String(fileContent, ConstantUtil.CHARSET);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return resultContent;
}


public String parseMessage(String messagePage){
    String[] lines = messagePage.split("\n");
    String reg = "#messages";
    List<String> messageList = new ArrayList<String>();
    for (String line : lines) {
        if (line.contains(reg)) {
            messageList.add(line);
        } else if (line.contains(ConstantUtil.RELOGIN)) {
            return ConstantUtil.RELOGIN;
        }
    }
    String result = "";
    if (messageList.size() > 0) {
        String message = messageList.get(messageList.size() - 1).trim();
        result = message.substring(21, message.length() - 3);
    }
    System.out.println(result);
    return result;
}


public String parseCredentialCodeErrorPage(String page){
    String result = "";
    NodeList nodeList = parseTag(page, Span.class);
    if (nodeList != null) {
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = (Node) nodeList.elementAt(i);
            if (node instanceof Span) {
                Span span = (Span) node;
                if (ConstantUtil.CLASS_ERRO_DIV1.equals(span.getAttribute(ConstantUtil.CLASS))) {
                    result = span.toPlainTextString().trim();
                }
            }
        }
    }
    return result;
}


public String parseErrorField(String errorPage){
    NodeList nodeList = parseTag(errorPage, Span.class);
    String result = "";
    if (nodeList != null) {
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.elementAt(i);
            if (node instanceof Span) {
                Span span = (Span) node;
                NodeList child = span.getChildren();
                if (child != null) {
                    // 如果有错误信息，则会有两层span
                    if (child.elementAt(0) instanceof Span) {
                        span = (Span) child.elementAt(0);
                        if (ConstantUtil.ERROR_FILED.equals(span.getAttribute("id"))) {
                            result = span.toPlainTextString();
                            if (!StringUtils.isEmpty(result)) {
                                // 有错误信息，则直接返回
                                return result;
                            }
                        }
                    }
                }
            }
        }
    }
    return result;
}


public String parseRedioType(String responsePage){
    if (StringUtils.isEmpty(responsePage)) {
        return "";
    }
    NodeList nodeList = parseTag(responsePage, InputTag.class);
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < nodeList.size(); i++) {
        Node node = nodeList.elementAt(i);
        if (node instanceof InputTag) {
            InputTag inputTag = (InputTag) node;
            if (ConstantUtil.REDIO_TYPE.equals(inputTag.getAttribute(ConstantUtil.CLASS)) && inputTag.getText().contains("disabled")) {
                // class为“radio_type”，并且为disabled的input
                builder.append(inputTag.getAttribute(ConstantUtil.VALUE));
            }
        }
    }
    System.out.println(builder.toString());
    return builder.toString();
}


}