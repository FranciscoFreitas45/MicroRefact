package br.com.fatecmogidascruzes.Interface;
public interface UserService {

   public Optional<User> getByUsername(String name);
}