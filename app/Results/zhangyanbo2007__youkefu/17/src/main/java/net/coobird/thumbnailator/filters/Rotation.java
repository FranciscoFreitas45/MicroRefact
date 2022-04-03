package net.coobird.thumbnailator.filters;
 import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import net.coobird.thumbnailator.builders.BufferedImageBuilder;
public class Rotation {

 public  Rotator LEFT_90_DEGREES;

 public  Rotator RIGHT_90_DEGREES;

 public  Rotator ROTATE_180_DEGREES;

/**
 * This class is not intended to be instantiated.
 */
private Rotation() {
}
public double[] calculatePosition(double x,double y,double angle){
    angle = Math.toRadians(angle);
    double nx = (Math.cos(angle) * x) - (Math.sin(angle) * y);
    double ny = (Math.sin(angle) * x) + (Math.cos(angle) * y);
    return new double[] { nx, ny };
}


public BufferedImage apply(BufferedImage img){
    int width = img.getWidth();
    int height = img.getHeight();
    BufferedImage newImage;
    double[][] newPositions = new double[4][];
    newPositions[0] = calculatePosition(0, 0, angle);
    newPositions[1] = calculatePosition(width, 0, angle);
    newPositions[2] = calculatePosition(0, height, angle);
    newPositions[3] = calculatePosition(width, height, angle);
    double minX = Math.min(Math.min(newPositions[0][0], newPositions[1][0]), Math.min(newPositions[2][0], newPositions[3][0]));
    double maxX = Math.max(Math.max(newPositions[0][0], newPositions[1][0]), Math.max(newPositions[2][0], newPositions[3][0]));
    double minY = Math.min(Math.min(newPositions[0][1], newPositions[1][1]), Math.min(newPositions[2][1], newPositions[3][1]));
    double maxY = Math.max(Math.max(newPositions[0][1], newPositions[1][1]), Math.max(newPositions[2][1], newPositions[3][1]));
    int newWidth = (int) Math.round(maxX - minX);
    int newHeight = (int) Math.round(maxY - minY);
    newImage = new BufferedImageBuilder(newWidth, newHeight).build();
    Graphics2D g = newImage.createGraphics();
    /*
				 * TODO consider RenderingHints to use.
				 * The following are hints which have been chosen to give
				 * decent image quality. In the future, there may be a need
				 * to have a way to change these settings.
				 */
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    double w = newWidth / 2.0;
    double h = newHeight / 2.0;
    g.rotate(Math.toRadians(angle), w, h);
    int centerX = (int) Math.round((newWidth - width) / 2.0);
    int centerY = (int) Math.round((newHeight - height) / 2.0);
    g.drawImage(img, centerX, centerY, null);
    g.dispose();
    return newImage;
}


public Rotator newRotator(double angle){
    Rotator r = new Rotator() {

        private double[] calculatePosition(double x, double y, double angle) {
            angle = Math.toRadians(angle);
            double nx = (Math.cos(angle) * x) - (Math.sin(angle) * y);
            double ny = (Math.sin(angle) * x) + (Math.cos(angle) * y);
            return new double[] { nx, ny };
        }

        public BufferedImage apply(BufferedImage img) {
            int width = img.getWidth();
            int height = img.getHeight();
            BufferedImage newImage;
            double[][] newPositions = new double[4][];
            newPositions[0] = calculatePosition(0, 0, angle);
            newPositions[1] = calculatePosition(width, 0, angle);
            newPositions[2] = calculatePosition(0, height, angle);
            newPositions[3] = calculatePosition(width, height, angle);
            double minX = Math.min(Math.min(newPositions[0][0], newPositions[1][0]), Math.min(newPositions[2][0], newPositions[3][0]));
            double maxX = Math.max(Math.max(newPositions[0][0], newPositions[1][0]), Math.max(newPositions[2][0], newPositions[3][0]));
            double minY = Math.min(Math.min(newPositions[0][1], newPositions[1][1]), Math.min(newPositions[2][1], newPositions[3][1]));
            double maxY = Math.max(Math.max(newPositions[0][1], newPositions[1][1]), Math.max(newPositions[2][1], newPositions[3][1]));
            int newWidth = (int) Math.round(maxX - minX);
            int newHeight = (int) Math.round(maxY - minY);
            newImage = new BufferedImageBuilder(newWidth, newHeight).build();
            Graphics2D g = newImage.createGraphics();
            /*
				 * TODO consider RenderingHints to use.
				 * The following are hints which have been chosen to give
				 * decent image quality. In the future, there may be a need
				 * to have a way to change these settings.
				 */
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            double w = newWidth / 2.0;
            double h = newHeight / 2.0;
            g.rotate(Math.toRadians(angle), w, h);
            int centerX = (int) Math.round((newWidth - width) / 2.0);
            int centerY = (int) Math.round((newHeight - height) / 2.0);
            g.drawImage(img, centerX, centerY, null);
            g.dispose();
            return newImage;
        }
    };
    return r;
}


}