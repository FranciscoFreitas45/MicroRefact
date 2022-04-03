package com.cym.utils;
 import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
public class Crypt {

 private  char[] saltChars;

 private  int ITERATIONS;

 private  int[] con_salt;

 private  boolean[] shifts2;

 private  int[][] skb;

 private  int[][] SPtrans;

 private  byte[] cov_2byte;

// 
// Null constructor - can't instantiate class
private Crypt() {
}
public void intToFourBytes(int iValue,byte[] b,int offset){
    b[offset++] = (byte) ((iValue) & 0xff);
    b[offset++] = (byte) ((iValue >>> 8) & 0xff);
    b[offset++] = (byte) ((iValue >>> 16) & 0xff);
    b[offset++] = (byte) ((iValue >>> 24) & 0xff);
}


public int D_ENCRYPT(int L,int R,int S,int E0,int E1,int[] s){
    int t, u, v;
    v = R ^ (R >>> 16);
    u = v & E0;
    v = v & E1;
    u = (u ^ (u << 16)) ^ R ^ s[S];
    t = (v ^ (v << 16)) ^ R ^ s[S + 1];
    t = (t >>> 4) | (t << 28);
    L ^= SPtrans[1][(t) & 0x3f] | SPtrans[3][(t >>> 8) & 0x3f] | SPtrans[5][(t >>> 16) & 0x3f] | SPtrans[7][(t >>> 24) & 0x3f] | SPtrans[0][(u) & 0x3f] | SPtrans[2][(u >>> 8) & 0x3f] | SPtrans[4][(u >>> 16) & 0x3f] | SPtrans[6][(u >>> 24) & 0x3f];
    return (L);
}


public int byteToUnsigned(byte b){
    int value = (int) b;
    return (value >= 0 ? value : value + 256);
}


public int fourBytesToInt(byte[] b,int offset){
    int value;
    value = byteToUnsigned(b[offset++]);
    value |= (byteToUnsigned(b[offset++]) << 8);
    value |= (byteToUnsigned(b[offset++]) << 16);
    value |= (byteToUnsigned(b[offset++]) << 24);
    return (value);
}


public int HPERM_OP(int a,int n,int m){
    int t;
    t = ((a << (16 - n)) ^ a) & m;
    a = a ^ t ^ (t >>> (16 - n));
    return (a);
}


public byte[] crypt(byte[] salt,byte[] original){
    byte[] result = new byte[13];
    byte byteZero = salt[0];
    byte byteOne = salt[1];
    result[0] = byteZero;
    result[1] = byteOne;
    int Eswap0 = con_salt[byteZero];
    int Eswap1 = con_salt[byteOne] << 4;
    byte[] key = new byte[8];
    for (int i = 0; i < key.length; i++) {
        key[i] = (byte) 0;
    }
    for (int i = 0; i < key.length && i < original.length; i++) {
        int iChar = (int) original[i];
        key[i] = (byte) (iChar << 1);
    }
    int[] schedule = des_set_key(key);
    int[] out = body(schedule, Eswap0, Eswap1);
    byte[] b = new byte[9];
    intToFourBytes(out[0], b, 0);
    intToFourBytes(out[1], b, 4);
    b[8] = 0;
    for (int i = 2, y = 0, u = 0x80; i < 13; i++) {
        for (int j = 0, c = 0; j < 6; j++) {
            c <<= 1;
            if (((int) b[y] & u) != 0) {
                c |= 1;
            }
            u >>>= 1;
            if (u == 0) {
                y++;
                u = 0x80;
            }
            result[i] = cov_2byte[c];
        }
    }
    return result;
}


public int[] des_set_key(byte[] key){
    int[] schedule = new int[ITERATIONS * 2];
    int c = fourBytesToInt(key, 0);
    int d = fourBytesToInt(key, 4);
    int[] results = new int[2];
    PERM_OP(d, c, 4, 0x0f0f0f0f, results);
    d = results[0];
    c = results[1];
    c = HPERM_OP(c, -2, 0xcccc0000);
    d = HPERM_OP(d, -2, 0xcccc0000);
    PERM_OP(d, c, 1, 0x55555555, results);
    d = results[0];
    c = results[1];
    PERM_OP(c, d, 8, 0x00ff00ff, results);
    c = results[0];
    d = results[1];
    PERM_OP(d, c, 1, 0x55555555, results);
    d = results[0];
    c = results[1];
    d = (((d & 0x000000ff) << 16) | (d & 0x0000ff00) | ((d & 0x00ff0000) >>> 16) | ((c & 0xf0000000) >>> 4));
    c &= 0x0fffffff;
    int s, t;
    int j = 0;
    for (int i = 0; i < ITERATIONS; i++) {
        if (shifts2[i]) {
            c = (c >>> 2) | (c << 26);
            d = (d >>> 2) | (d << 26);
        } else {
            c = (c >>> 1) | (c << 27);
            d = (d >>> 1) | (d << 27);
        }
        c &= 0x0fffffff;
        d &= 0x0fffffff;
        s = skb[0][(c) & 0x3f] | skb[1][((c >>> 6) & 0x03) | ((c >>> 7) & 0x3c)] | skb[2][((c >>> 13) & 0x0f) | ((c >>> 14) & 0x30)] | skb[3][((c >>> 20) & 0x01) | ((c >>> 21) & 0x06) | ((c >>> 22) & 0x38)];
        t = skb[4][(d) & 0x3f] | skb[5][((d >>> 7) & 0x03) | ((d >>> 8) & 0x3c)] | skb[6][(d >>> 15) & 0x3f] | skb[7][((d >>> 21) & 0x0f) | ((d >>> 22) & 0x30)];
        schedule[j++] = ((t << 16) | (s & 0x0000ffff)) & 0xffffffff;
        s = ((s >>> 16) | (t & 0xffff0000));
        s = (s << 4) | (s >>> 28);
        schedule[j++] = s & 0xffffffff;
    }
    return (schedule);
}


public void PERM_OP(int a,int b,int n,int m,int[] results){
    int t;
    t = ((a >>> n) ^ b) & m;
    a ^= t << n;
    b ^= t;
    results[0] = a;
    results[1] = b;
}


public void main(String[] args){
    System.out.println(getString("name", "name"));
}


public String getString(String username,String password){
    String encoding = "ISO8859-1";
    Random random = new Random();
    StringBuilder salt = new StringBuilder();
    salt.append(saltChars[random.nextInt(saltChars.length - 1)]);
    salt.append(saltChars[random.nextInt(saltChars.length - 1)]);
    byte[] pwd = null;
    try {
        pwd = Crypt.crypt(salt.toString().getBytes(encoding), password.getBytes(encoding));
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    return username + ":" + new String(pwd);
}


public int[] body(int[] schedule,int Eswap0,int Eswap1){
    int left = 0;
    int right = 0;
    int t = 0;
    for (int j = 0; j < 25; j++) {
        for (int i = 0; i < ITERATIONS * 2; i += 4) {
            left = D_ENCRYPT(left, right, i, Eswap0, Eswap1, schedule);
            right = D_ENCRYPT(right, left, i + 2, Eswap0, Eswap1, schedule);
        }
        t = left;
        left = right;
        right = t;
    }
    t = right;
    right = (left >>> 1) | (left << 31);
    left = (t >>> 1) | (t << 31);
    left &= 0xffffffff;
    right &= 0xffffffff;
    int[] results = new int[2];
    PERM_OP(right, left, 1, 0x55555555, results);
    right = results[0];
    left = results[1];
    PERM_OP(left, right, 8, 0x00ff00ff, results);
    left = results[0];
    right = results[1];
    PERM_OP(right, left, 2, 0x33333333, results);
    right = results[0];
    left = results[1];
    PERM_OP(left, right, 16, 0x0000ffff, results);
    left = results[0];
    right = results[1];
    PERM_OP(right, left, 4, 0x0f0f0f0f, results);
    right = results[0];
    left = results[1];
    int[] out = new int[2];
    out[0] = left;
    out[1] = right;
    return (out);
}


}