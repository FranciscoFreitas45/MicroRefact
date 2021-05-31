public interface SettingRepo {

   public List<Setting> findByGroup(String string);
   public Setting findByKey(String key);
}