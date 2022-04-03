package org.gliderwiki.framework.util;
 public class Base64Coder {

 private  String systemLineSeparator;

 private  char[] map1;

 private  byte[] map2;

// Dummy constructor.
private Base64Coder() {
}
public char[] encode(byte[] in,int iOff,int iLen){
    // output length without padding
    int oDataLen = (iLen * 4 + 2) / 3;
    // output length including padding
    int oLen = ((iLen + 2) / 3) * 4;
    char[] out = new char[oLen];
    int ip = iOff;
    int iEnd = iOff + iLen;
    int op = 0;
    while (ip < iEnd) {
        int i0 = in[ip++] & 0xff;
        int i1 = ip < iEnd ? in[ip++] & 0xff : 0;
        int i2 = ip < iEnd ? in[ip++] & 0xff : 0;
        int o0 = i0 >>> 2;
        int o1 = ((i0 & 3) << 4) | (i1 >>> 4);
        int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
        int o3 = i2 & 0x3F;
        out[op++] = map1[o0];
        out[op++] = map1[o1];
        out[op] = op < oDataLen ? map1[o2] : '=';
        op++;
        out[op] = op < oDataLen ? map1[o3] : '=';
        op++;
    }
    return out;
}


public byte[] decodeLines(String s){
    char[] buf = new char[s.length()];
    int p = 0;
    for (int ip = 0; ip < s.length(); ip++) {
        char c = s.charAt(ip);
        if (c != ' ' && c != '\r' && c != '\n' && c != '\t')
            buf[p++] = c;
    }
    return decode(buf, 0, p);
}


public String encodeLines(byte[] in,int iOff,int iLen,int lineLen,String lineSeparator){
    int blockLen = (lineLen * 3) / 4;
    if (blockLen <= 0)
        throw new IllegalArgumentException();
    int lines = (iLen + blockLen - 1) / blockLen;
    int bufLen = ((iLen + 2) / 3) * 4 + lines * lineSeparator.length();
    StringBuilder buf = new StringBuilder(bufLen);
    int ip = 0;
    while (ip < iLen) {
        int l = Math.min(iLen - ip, blockLen);
        buf.append(encode(in, iOff + ip, l));
        buf.append(lineSeparator);
        ip += l;
    }
    return buf.toString();
}


public String encodeString(byte[] s){
    return new String(encode(s));
}


public String decodeString(String s){
    return new String(decode(s));
}


public byte[] decode(char[] in,int iOff,int iLen){
    if (iLen % 4 != 0)
        throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
    while (iLen > 0 && in[iOff + iLen - 1] == '=') iLen--;
    int oLen = (iLen * 3) / 4;
    byte[] out = new byte[oLen];
    int ip = iOff;
    int iEnd = iOff + iLen;
    int op = 0;
    while (ip < iEnd) {
        int i0 = in[ip++];
        int i1 = in[ip++];
        int i2 = ip < iEnd ? in[ip++] : 'A';
        int i3 = ip < iEnd ? in[ip++] : 'A';
        if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127)
            throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
        int b0 = map2[i0];
        int b1 = map2[i1];
        int b2 = map2[i2];
        int b3 = map2[i3];
        if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0)
            throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
        int o0 = (b0 << 2) | (b1 >>> 4);
        int o1 = ((b1 & 0xf) << 4) | (b2 >>> 2);
        int o2 = ((b2 & 3) << 6) | b3;
        out[op++] = (byte) o0;
        if (op < oLen)
            out[op++] = (byte) o1;
        if (op < oLen)
            out[op++] = (byte) o2;
    }
    return out;
}


}