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
public class Diff {

 public  Operation operation;

 public  String text;

/**
 * Constructor.  Initializes the diff with the provided values.
 * @param operation One of INSERT, DELETE or EQUAL.
 * @param text The text being applied.
 */
public Diff(Operation operation, String text) {
    // Construct a diff with the specified operation and text.
    this.operation = operation;
    this.text = text;
}
@Override
public int hashCode(){
    final int prime = 31;
    int result = (operation == null) ? 0 : operation.hashCode();
    result += prime * ((text == null) ? 0 : text.hashCode());
    return result;
}


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    Diff other = (Diff) obj;
    if (operation != other.operation) {
        return false;
    }
    if (text == null) {
        if (other.text != null) {
            return false;
        }
    } else if (!text.equals(other.text)) {
        return false;
    }
    return true;
}


public String toString(){
    String prettyText = this.text.replace('\n', '\u00b6');
    return "Diff(" + this.operation + ",\"" + prettyText + "\")";
}


}