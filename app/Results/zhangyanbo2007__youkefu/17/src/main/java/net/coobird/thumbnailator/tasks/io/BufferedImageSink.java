package net.coobird.thumbnailator.tasks.io;
 import java.awt.image.BufferedImage;
import java.io.IOException;
public class BufferedImageSink extends AbstractImageSink<BufferedImage>{

 private  BufferedImage img;

 private  boolean written;


public BufferedImage getSink(){
    if (!written) {
        throw new IllegalStateException("BufferedImageSink has not been written to yet.");
    }
    return img;
}


public void write(BufferedImage img){
    super.write(img);
    this.img = img;
    written = true;
}


@Override
public void setOutputFormatName(String format){
// do nothing
}


}