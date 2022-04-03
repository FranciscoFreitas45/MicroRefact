package net.coobird.thumbnailator.tasks.io;
 import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
public class URLImageSource extends AbstractImageSource<URL>{

 private  URL url;

 private  Proxy proxy;

/**
 * Instantiates an {@link URLImageSource} with the URL from which the
 * source image should be retrieved from.
 *
 * @param url		URL to the source image.
 * @throws NullPointerException		If the URL is null
 */
public URLImageSource(URL url) {
    super();
    if (url == null) {
        throw new NullPointerException("URL cannot be null.");
    }
    this.url = url;
    this.proxy = null;
}/**
 * Instantiates an {@link URLImageSource} with the URL from which the
 * source image should be retrieved from.
 *
 * @param url		URL to the source image.
 * @throws NullPointerException		If the URL is null
 * @throws MalformedURLException	If the URL is not valid.
 */
public URLImageSource(String url) throws MalformedURLException {
    super();
    if (url == null) {
        throw new NullPointerException("URL cannot be null.");
    }
    this.url = new URL(url);
    this.proxy = null;
}/**
 * Instantiates an {@link URLImageSource} with the URL from which the
 * source image should be retrieved from, along with the proxy to use
 * to connect to the aforementioned URL.
 *
 * @param url		URL to the source image.
 * @param proxy		Proxy to use to connect to the URL.
 * @throws NullPointerException		If the URL and or the proxy is null
 */
public URLImageSource(URL url, Proxy proxy) {
    super();
    if (url == null) {
        throw new NullPointerException("URL cannot be null.");
    } else if (proxy == null) {
        throw new NullPointerException("Proxy cannot be null.");
    }
    this.url = url;
    this.proxy = proxy;
}/**
 * Instantiates an {@link URLImageSource} with the URL from which the
 * source image should be retrieved from, along with the proxy to use
 * to connect to the aforementioned URL.
 *
 * @param url		URL to the source image.
 * @param proxy		Proxy to use to connect to the URL.
 * @throws NullPointerException		If the URL and or the proxy is null
 * @throws MalformedURLException	If the URL is not valid.
 */
public URLImageSource(String url, Proxy proxy) throws MalformedURLException {
    super();
    if (url == null) {
        throw new NullPointerException("URL cannot be null.");
    } else if (proxy == null) {
        throw new NullPointerException("Proxy cannot be null.");
    }
    this.url = new URL(url);
    this.proxy = proxy;
}
public BufferedImage read(){
    InputStreamImageSource source;
    try {
        if (proxy != null) {
            source = new InputStreamImageSource(url.openConnection(proxy).getInputStream());
        } else {
            source = new InputStreamImageSource(url.openStream());
        }
    } catch (IOException e) {
        throw new IOException("Could not open connection to URL: " + url);
    }
    source.setThumbnailParameter(param);
    BufferedImage img;
    try {
        img = source.read();
    } catch (Exception e) {
        throw new IOException("Could not obtain image from URL: " + url);
    }
    this.inputFormatName = source.getInputFormatName();
    return finishedReading(img);
}


public Proxy getProxy(){
    return proxy;
}


public URL getSource(){
    return url;
}


}