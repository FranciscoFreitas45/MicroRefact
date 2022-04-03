package org.opengeoportal.proxy;
 import java.io.File;
import java.net.URL;
import java.util.concurrent.Future;
public interface ImageDownloader {


public Future<File> getImage(URL imageLocation)
;

}