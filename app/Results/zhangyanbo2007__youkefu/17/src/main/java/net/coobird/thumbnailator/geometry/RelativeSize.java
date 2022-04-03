package net.coobird.thumbnailator.geometry;
 import java.awt.Dimension;
public class RelativeSize implements Size{

 private  double scalingFactor;

/**
 * Instantiates an object which calculates the size of an object, using
 * the given scaling factor.
 *
 * @param scalingFactor		The scaling factor to use to determine the
 * 							size of the enclosing object.
 * @throws IllegalArgumentException		When the scaling factor is not within
 * 										the range of {@code 0.0d} and
 * 										{@code 1.0d}, inclusive.
 */
public RelativeSize(double scalingFactor) {
    super();
    if (scalingFactor < 0.0d || scalingFactor > 1.0d) {
        throw new IllegalArgumentException("The scaling factor must be between 0.0d and 1.0d, inclusive.");
    }
    this.scalingFactor = scalingFactor;
}
@Override
public String toString(){
    return "RelativeSize [scalingFactor=" + scalingFactor + "]";
}


public Dimension calculate(int width,int height){
    if (width <= 0 || height <= 0) {
        throw new IllegalArgumentException("Width and height must be greater than 0.");
    }
    int newWidth = (int) Math.round(width * scalingFactor);
    int newHeight = (int) Math.round(height * scalingFactor);
    return new Dimension(newWidth, newHeight);
}


}