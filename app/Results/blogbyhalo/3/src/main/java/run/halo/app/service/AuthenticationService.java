package run.halo.app.service;
 import run.halo.app.model.entity.Post;
public interface AuthenticationService {


public boolean postAuthentication(Post post,String password)
;

public boolean categoryAuthentication(Integer categoryId,String password)
;

}