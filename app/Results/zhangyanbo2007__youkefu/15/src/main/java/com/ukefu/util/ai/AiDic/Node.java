package com.ukefu.util.ai.AiDic;
 import java.io.IOException;
import org.apache.commons.lang.ArrayUtils;
public class Node {

 private int code;

 private int depth;

 private int[] id;

 private AiDic aiDic;


public String toString(){
    return aiDic.trie.getDicMap().inverse().get(code);
}


}