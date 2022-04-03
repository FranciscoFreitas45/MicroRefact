package org.opengeoportal.DTO;
 public class BoundingBox extends Envelope{

 private  String epsgCode;

/**
 * BoundingBox constructor from Doubles
 * @param minX
 * @param minY
 * @param maxX
 * @param maxY
 */
public BoundingBox(Double minX, Double minY, Double maxX, Double maxY) {
    super(minX, minY, maxX, maxY, epsgCode);
}/**
 * BoundingBox constructor from Strings
 * @param minX
 * @param minY
 * @param maxX
 * @param maxY
 */
public BoundingBox(String minX, String minY, String maxX, String maxY) {
    super(minX, minY, maxX, maxY, epsgCode);
}
public BoundingBox getOrderedBox(BoundingBox box){
    Double minx = Math.min(box.getMinX(), box.getMaxX());
    Double maxx = Math.max(box.getMinX(), box.getMaxX());
    Double miny = Math.min(box.getMinY(), box.getMaxY());
    Double maxy = Math.max(box.getMinY(), box.getMaxY());
    return new BoundingBox(minx, miny, maxx, maxy);
}


public BoundingBox getIntersection(BoundingBox anotherBox){
    // make sure the boxes are in proper order to start with
    anotherBox = BoundingBox.getOrderedBox(anotherBox);
    BoundingBox currentBox = BoundingBox.getOrderedBox(this);
    Double intersectionMinX = Math.max(currentBox.getMinX(), anotherBox.getMinX());
    Double intersectionMaxX = Math.min(currentBox.getMaxX(), anotherBox.getMaxX());
    Double intersectionMinY = Math.max(currentBox.getMinY(), anotherBox.getMinY());
    Double intersectionMaxY = Math.min(currentBox.getMaxY(), anotherBox.getMaxY());
    BoundingBox intersection = new BoundingBox(intersectionMinX, intersectionMinY, intersectionMaxX, intersectionMaxY);
    return intersection;
}


}