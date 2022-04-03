package com.effective.chapter3;
 public class Color {

public Color() {
}
@Override
public boolean equals(Object o){
    if (!(o instanceof Color)) {
        return false;
    }
    Color c = (Color) o;
    return c.equals(this);
}


}