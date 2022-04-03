package org.gliderwiki.framework.util;
 import java.util.NoSuchElementException;
import java.util.StringTokenizer;
public class FtpReply implements Cloneable{

 private  int d_code;

 private  String d_text;

 private  boolean d_isFromServer;

 private  String d_dataHost;

 private  int d_dataPort;

 private  String d_dataPath;

 final  int RC_PRELIMINARY;

 final  int RC_COMPLETE;

 final  int RC_INTERMEDIATE;

 final  int RC_ERROR_TRANSIENT;

 final  int RC_ERROR;


public int getPort(){
    return d_dataPort;
}


public int getType(){
    return d_code / 100;
}


public void parseReply227(){
    // break into six comma separated parts
    StringTokenizer st = new StringTokenizer(d_text, ",");
    String[] parts = new String[6];
    for (int i1 = 0; st.hasMoreElements(); i1++) {
        try {
            // more then six parts?
            if (i1 == 6)
                throw new NoSuchElementException();
            parts[i1] = st.nextToken();
        }// fewer then six parts?
         catch (NoSuchElementException nope) {
            throw new IllegalArgumentException("Missing commas");
        }
    }
    // end getting parts of host, port
    // extract trailing number from first part
    int off;
    for (off = parts[0].length() - 1; off >= 0 && off >= parts[0].length() - 4; off--) {
        if (!Character.isDigit(parts[0].charAt(off)))
            break;
    }
    parts[0] = parts[0].substring(off + 1);
    // clean trailing info from last part
    for (off = 0; off < 3 && off < parts[5].length(); off++) {
        if (!Character.isDigit(parts[5].charAt(off)))
            break;
    }
    parts[5] = parts[5].substring(0, off);
    // Get dotted quad IP number first
    d_dataHost = parts[0] + "." + parts[1] + "." + parts[2] + "." + parts[3];
    // Determine port
    try {
        // Get first part of port, shift by 8 bits.
        d_dataPort = 256 * Integer.parseInt(parts[4]) + Integer.parseInt(parts[5]);
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Bad port number");
    }
}


public String getText(){
    return d_text;
}


public String getPath(){
    return d_dataPath;
}


public void parseReply257(){
    StringBuffer buf = new StringBuffer();
    // get quoted string, start after code and leading double quote.
    for (int i1 = 5; i1 < d_text.length(); i1++) {
        if (d_text.charAt(i1) == '"') {
            i1++;
            if (i1 == d_text.length() || d_text.charAt(i1) != '"') {
                break;
            }
        }
        buf.append(d_text.charAt(i1));
    }
    d_dataPath = buf.toString();
}


public void setReply(boolean isFromServer,String text){
    // set reply data
    d_isFromServer = isFromServer;
    d_code = 0;
    d_text = text;
    d_dataHost = null;
    d_dataPort = 0;
    d_dataPath = null;
    // get code
    if (d_text.length() < 4) {
        // too short to contain a reply code
        throw new IllegalArgumentException("Bad format");
    }
    try {
        d_code = Integer.parseInt(d_text.substring(0, 3));
        // validate reply code
        if (d_code < 100 || d_code > 599)
            throw new NumberFormatException();
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Bad reply code");
    }
    // parse special data from reply?
    switch(getCode()) {
        case 227:
            parseReply227();
            break;
        case 257:
            parseReply257();
            break;
    }
}


public int getCode(){
    return d_code;
}


public String getHost(){
    return d_dataHost;
}


public boolean isFromServer(){
    return d_isFromServer;
}


}