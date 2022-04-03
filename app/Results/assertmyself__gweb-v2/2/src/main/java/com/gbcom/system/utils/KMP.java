package com.gbcom.system.utils;
 public class KMP {


public void main(String[] args){
    boolean result = kmp("孙", "孙悟空");
    System.out.println("result : " + result);
}


public int[] getNext(char[] b){
    int i, j;
    int len = b.length;
    int[] next = new int[len];
    next[0] = 0;
    for (j = 1; j < len; j++) {
        i = next[j - 1];
        while ((i > 0) && (b[i - 1] != b[j - 1])) {
            i = next[i - 1];
        }
        next[j] = i + 1;
    }
    return next;
}


public boolean kmp(String msg,String pattern){
    if (msg == null || pattern == null) {
        return false;
    }
    int plen = pattern.length();
    int tlen = msg.length();
    char[] p = pattern.toCharArray();
    char[] t = msg.toCharArray();
    int[] n = getNext(t);
    int i = 1, j = 1, sum = 0;
    while (i <= plen && j <= tlen) {
        if (j == 0 || p[i - 1] == t[j - 1]) {
            if (j != 0) {
                sum++;
            }
            i++;
            j++;
        } else {
            sum++;
            j = n[j - 1];
        }
        if (j > tlen) {
            break;
        }
    }
    return j > tlen;
}


}