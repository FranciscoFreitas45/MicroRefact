package run.halo.app.Interface;
public interface OptionService {

   public Boolean isEnabledAbsolutePath();
   public String getBlogBaseUrl();
   public String getLinksPrefix();
   public String getPhotosPrefix();
   public String getJournalsPrefix();
   public SheetPermalinkType getSheetPermalinkType();
   public String getPathSuffix();
   public String getSheetPrefix();
}