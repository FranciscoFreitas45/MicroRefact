package run.halo.app.utils;
 import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import lombok.extern.slf4j.Slf4j;
import net.sf.image4j.codec.ico.ICODecoder;
import org.springframework.lang.NonNull;
import run.halo.app.exception.ImageFormatException;
@Slf4j
public class ImageUtils {

 public  String EXTENSION_ICO;


public BufferedImage getImageFromFile(InputStream is,String extension){
    log.debug("Current File type is : [{}]", extension);
    if (EXTENSION_ICO.equals(extension)) {
        try {
            return ICODecoder.read(is).get(0);
        } catch (IOException e) {
            throw new ImageFormatException("ico 文件已损坏", e);
        }
    } else {
        return ImageIO.read(is);
    }
}


@NonNull
public ImageReader getImageReaderFromFile(InputStream is,String formatName){
    try {
        Iterator<ImageReader> readerIterator = ImageIO.getImageReadersByFormatName(formatName);
        ImageReader reader = readerIterator.next();
        ImageInputStream stream = ImageIO.createImageInputStream(is);
        ImageIO.getImageReadersByFormatName(formatName);
        reader.setInput(stream, true);
        return reader;
    } catch (Exception e) {
        throw new IOException("Failed to read image reader.", e);
    }
}


}