package org.opengeoportal.utilities;
 import java.util.ArrayList;
import java.util.List;
import org.opengeoportal.config.PropertiesFile;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
public class UrlShortenerGoogle implements UrlShortener{

 private  String url;

 private PropertiesFile propertiesFile;


public PropertiesFile getPropertiesFile(){
    return propertiesFile;
}


public void setPropertiesFile(PropertiesFile propertiesFile){
    this.propertiesFile = propertiesFile;
}


public String retrieveShortLink(String longUrl){
    /*
		 * 	/*POST https://www.googleapis.com/urlshortener/v1/url
		Content-Type: application/json

		{"longUrl": "http://www.google.com/"}
		 */
    String defaultVal = "NO_KEY";
    String apiKey = propertiesFile.getProperty("apikey.google", defaultVal);
    if (apiKey.equals(defaultVal)) {
        throw new Exception("API key required to use Google link shortening service.");
    }
    RestTemplate template = new RestTemplate();
    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
    messageConverters.add(new MappingJackson2HttpMessageConverter());
    template.setMessageConverters(messageConverters);
    // retrieve the google api key from a config file
    // now we need to add the parameters
    LinkShortenRequestGoogle postObject = new LinkShortenRequestGoogle(longUrl);
    LinkShortenReturnGoogle result = template.postForObject(url + apiKey, postObject, LinkShortenReturnGoogle.class);
    return result.getId();
}


}