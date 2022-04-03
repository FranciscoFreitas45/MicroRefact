package cn.gson.oasys.Interface;
public interface FileServices {

   public Object savefile(MultipartFile file,User user,FilePath nowpath,boolean isfile);
   public Integer updateatt(MultipartFile file,User user,FilePath nowpath,long attid);
}