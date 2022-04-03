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
public class Patch {

 public  LinkedList<Diff> diffs;

 public  int start1;

 public  int start2;

 public  int length1;

 public  int length2;

/**
 * Constructor.  Initializes with an empty list of diffs.
 */
public Patch() {
    this.diffs = new LinkedList<Diff>();
}
public String toString(){
    String coords1, coords2;
    if (this.length1 == 0) {
        coords1 = this.start1 + ",0";
    } else if (this.length1 == 1) {
        coords1 = Integer.toString(this.start1 + 1);
    } else {
        coords1 = (this.start1 + 1) + "," + this.length1;
    }
    if (this.length2 == 0) {
        coords2 = this.start2 + ",0";
    } else if (this.length2 == 1) {
        coords2 = Integer.toString(this.start2 + 1);
    } else {
        coords2 = (this.start2 + 1) + "," + this.length2;
    }
    StringBuilder text = new StringBuilder();
    text.append("@@ -").append(coords1).append(" +").append(coords2).append(" @@\n");
    // Escape the body of the patch with %xx notation.
    for (Diff aDiff : this.diffs) {
        switch(aDiff.operation) {
            case INSERT:
                text.append('+');
                break;
            case DELETE:
                text.append('-');
                break;
            case EQUAL:
                text.append(' ');
                break;
        }
        try {
            text.append(URLEncoder.encode(aDiff.text, "UTF-8").replace('+', ' ')).append("\n");
        } catch (UnsupportedEncodingException e) {
            // Not likely on modern system.
            throw new Error("This system does not support UTF-8.", e);
        }
    }
    return unescapeForEncodeUriCompatability(text.toString());
}


}