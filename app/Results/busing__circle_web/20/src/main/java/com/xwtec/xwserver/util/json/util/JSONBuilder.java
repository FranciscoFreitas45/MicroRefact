package com.xwtec.xwserver.util.json.util;
 import java.io.IOException;
import java.io.Writer;
import com.xwtec.xwserver.util.json.JSONException;
public class JSONBuilder {

 private  int MAXDEPTH;

 private  boolean comma;

 protected  char mode;

 private  char[] stack;

 private  int top;

 protected  Writer writer;

/**
 * Make a fresh JSONBuilder. It can be used to build one JSON text.
 */
public JSONBuilder(Writer w) {
    this.comma = false;
    this.mode = 'i';
    this.stack = new char[MAXDEPTH];
    this.top = 0;
    this.writer = w;
}
public void pop(char c){
    if (this.top <= 0 || this.stack[this.top - 1] != c) {
        throw new JSONException("Nesting error.");
    }
    this.top -= 1;
    this.mode = this.top == 0 ? 'd' : this.stack[this.top - 1];
}


public JSONBuilder array(){
    if (this.mode == 'i' || this.mode == 'o' || this.mode == 'a') {
        this.push('a');
        this.append("[");
        this.comma = false;
        return this;
    }
    throw new JSONException("Misplaced array.");
}


public JSONBuilder end(char m,char c){
    if (this.mode != m) {
        throw new JSONException(m == 'o' ? "Misplaced endObject." : "Misplaced endArray.");
    }
    this.pop(m);
    try {
        this.writer.write(c);
    } catch (IOException e) {
        throw new JSONException(e);
    }
    this.comma = true;
    return this;
}


public JSONBuilder endArray(){
    return this.end('a', ']');
}


public JSONBuilder value(Object o){
    return this.append(JSONUtils.valueToString(o));
}


public JSONBuilder append(String s){
    if (s == null) {
        throw new JSONException("Null pointer");
    }
    if (this.mode == 'o' || this.mode == 'a') {
        try {
            if (this.comma && this.mode == 'a') {
                this.writer.write(',');
            }
            this.writer.write(s);
        } catch (IOException e) {
            throw new JSONException(e);
        }
        if (this.mode == 'o') {
            this.mode = 'k';
        }
        this.comma = true;
        return this;
    }
    throw new JSONException("Value out of sequence.");
}


public JSONBuilder key(String s){
    if (s == null) {
        throw new JSONException("Null key.");
    }
    if (this.mode == 'k') {
        try {
            if (this.comma) {
                this.writer.write(',');
            }
            this.writer.write(JSONUtils.quote(s));
            this.writer.write(':');
            this.comma = false;
            this.mode = 'o';
            return this;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }
    throw new JSONException("Misplaced key.");
}


public void push(char c){
    if (this.top >= MAXDEPTH) {
        throw new JSONException("Nesting too deep.");
    }
    this.stack[this.top] = c;
    this.mode = c;
    this.top += 1;
}


public JSONBuilder endObject(){
    return this.end('k', '}');
}


public JSONBuilder object(){
    if (this.mode == 'i') {
        this.mode = 'o';
    }
    if (this.mode == 'o' || this.mode == 'a') {
        this.append("{");
        this.push('k');
        this.comma = false;
        return this;
    }
    throw new JSONException("Misplaced object.");
}


}