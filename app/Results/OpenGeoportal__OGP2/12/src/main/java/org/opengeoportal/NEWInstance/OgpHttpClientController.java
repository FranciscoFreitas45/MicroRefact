package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OgpHttpClientController {

 private OgpHttpClient ogphttpclient;


@GetMapping
("/getCloseableHttpClient")
public CloseableHttpClient getCloseableHttpClient(){
  return ogphttpclient.getCloseableHttpClient();
}


}