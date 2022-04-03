package br.com.fatecmogidascruzes.storage;
 import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.springframework.web.multipart.MultipartFile;
public interface Storage {


public Path getPathTo(String fileName)
;

public Path getPath()
;

public byte[] loadBytes(String name)
;

public File loadFile(String name)
;

public String store(MultipartFile file)
;

public void remove(String name)
;

public BufferedImage loadImage(String name) throws IOException
;

}