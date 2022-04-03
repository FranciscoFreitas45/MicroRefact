package org.gliderwiki.util.DiffMatchPatch;
 import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class LinesToCharsResult {

 protected  String chars1;

 protected  String chars2;

 protected  List<String> lineArray;

protected LinesToCharsResult(String chars1, String chars2, List<String> lineArray) {
    this.chars1 = chars1;
    this.chars2 = chars2;
    this.lineArray = lineArray;
}
}