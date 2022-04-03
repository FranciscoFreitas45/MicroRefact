package com.effective.chapter3;
 public class ColorPoint {

 private  Point point;

 private  Color color;

public ColorPoint(int x, int y, Color color) {
    if (color == null) {
        throw new NullPointerException();
    }
    point = new Point(x, y);
    this.color = color;
}
@Override
public boolean equals(Object o){
    if (!(o instanceof ColorPoint)) {
        return false;
    }
    ColorPoint cp = (ColorPoint) o;
    return cp.point.equals(point) && cp.color.equals(color);
}


public Point asPoint(){
    return point;
}


}