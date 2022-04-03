package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SocialProfileService;
public class SocialProfileServiceImpl implements SocialProfileService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public void deleteAllSocialProfiles(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteAllSocialProfiles"))
;
  restTemplate.put(builder.toUriString(), null);
}


public SocialProfile create(String nick,String name,String profileLink){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("nick",nick)
    .queryParam("name",name)
    .queryParam("profileLink",profileLink);
  SocialProfile aux = restTemplate.getForObject(builder.toUriString(), SocialProfile.class);

 return aux;
}


public SocialProfile findOne(int socialProfileId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("socialProfileId",socialProfileId);
  SocialProfile aux = restTemplate.getForObject(builder.toUriString(), SocialProfile.class);

 return aux;
}


public SocialProfile reconstruct(SocialProfile socialProfile,BindingResult binding){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstruct"))
    .queryParam("socialProfile",socialProfile)
    .queryParam("binding",binding);
  SocialProfile aux = restTemplate.getForObject(builder.toUriString(), SocialProfile.class);

 return aux;
}


public SocialProfile save(SocialProfile socialProfile){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("socialProfile",socialProfile);
  SocialProfile aux = restTemplate.getForObject(builder.toUriString(), SocialProfile.class);

 return aux;
}


public void deleteSocialProfile(SocialProfile socialProfile){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteSocialProfile"))
    .queryParam("socialProfile",socialProfile);

  restTemplate.put(builder.toUriString(), null);
}


}