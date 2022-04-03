package Interface;
public interface SocialProfileService {

   public void deleteAllSocialProfiles();
   public SocialProfile create(String nick,String name,String profileLink);
   public SocialProfile findOne(int socialProfileId);
   public SocialProfile reconstruct(SocialProfile socialProfile,BindingResult binding);
   public SocialProfile save(SocialProfile socialProfile);
   public void deleteSocialProfile(SocialProfile socialProfile);
}