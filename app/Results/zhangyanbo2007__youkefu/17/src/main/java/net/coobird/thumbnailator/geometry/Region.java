package net.coobird.thumbnailator.geometry;
 import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
public class Region {

 private  Position position;

 private  Size size;

/**
 * Instantiates a representation of a region from a {@link Position} and
 * {@link Size}.
 *
 * @param position		Position of the region.
 * @param size			Size of the region.
 * @throws NullPointerException		When the position and/or the size is
 * 									{@code null}.
 */
public Region(Position position, Size size) {
    super();
    if (position == null) {
        throw new NullPointerException("Position cannot be null.");
    }
    if (size == null) {
        throw new NullPointerException("Size cannot be null.");
    }
    this.position = position;
    this.size = size;
}
public Size getSize(){
    return size;
}


public Position getPosition(){
    return position;
}


@Override
public String toString(){
    return "Region [position=" + position + ", size=" + size + "]";
}


public Rectangle calculate(int width,int height){
    Dimension d = size.calculate(width, height);
    Point p = position.calculate(width, height, d.width, d.height, 0, 0, 0, 0);
    Rectangle outerRectangle = new Rectangle(0, 0, width, height);
    Rectangle innerRectangle = new Rectangle(p, d);
    return outerRectangle.intersection(innerRectangle);
}


}