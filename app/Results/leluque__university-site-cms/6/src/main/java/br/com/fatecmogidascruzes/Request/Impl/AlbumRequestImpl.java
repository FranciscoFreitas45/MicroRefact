package br.com.fatecmogidascruzes.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.DTO.Album;
import br.com.fatecmogidascruzes.Request.AlbumRequest;
public class AlbumRequestImpl implements AlbumRequest{

 private RestTemplate restTemplate = new RestTemplate();;


}