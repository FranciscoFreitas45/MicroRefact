package br.com.fatecmogidascruzes.Interface;
public interface AlbumDAO {

   public List<Album> getEnabledByFilter(String filter);
}