package run.halo.app.service;
 import java.util.Set;
public interface AuthorizationService {


public void postAuthorization(Integer postId)
;

public String buildPostToken(Integer postId){
    return "POST:" + postId;
}
;

public Set<String> getAccessPermissionStore()
;

public void deleteCategoryAuthorization(Integer categoryId)
;

public String buildCategoryToken(Integer categoryId){
    return "CATEGORY:" + categoryId;
}
;

public void deletePostAuthorization(Integer postId)
;

public void categoryAuthorization(Integer categoryId)
;

}