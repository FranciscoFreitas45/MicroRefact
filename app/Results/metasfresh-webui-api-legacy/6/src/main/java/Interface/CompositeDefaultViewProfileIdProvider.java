package Interface;
public interface CompositeDefaultViewProfileIdProvider {

   public CompositeDefaultViewProfileIdProvider of(List<DefaultViewProfileIdProvider> providers);
   public void setDefaultProfileIdOverride(WindowId windowId,ViewProfileId profileId);
   public ViewProfileId getDefaultProfileIdByWindowId(WindowId windowId);
}