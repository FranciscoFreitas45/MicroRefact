package Interface;
public interface FloatService {

   public void deleteAllFloatsBrotherhood();
   public domain.Float findOne(int id);
   public List<domain.Float> floatsInParadeInFinalMode();
   public List<domain.Float> showBrotherhoodFloats();
   public List<String> getPicturesOfFloat(int floatId,boolean parade);
   public Boolean isUrl(String url);
   public domain.Float addPicture(String picture,domain.Float floatt);
   public domain.Float create();
   public domain.Float reconstruct(domain.Float floatt,BindingResult binding);
   public domain.Float save(domain.Float floatt);
   public void remove(domain.Float floatt);
}