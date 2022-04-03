package switchtwentytwenty.project.domain.share.id;
 import java.util.Objects;
public class CategoryID implements ID{

 private  String id;

/**
 * Constructor methods
 * @param id - id
 */
public CategoryID(String id) {
    Objects.requireNonNull(id);
    this.id = id;
}
@Override
public int hashCode(){
    return Objects.hash(id);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof CategoryID))
        return false;
    CategoryID that = (CategoryID) o;
    return Objects.equals(id, that.id);
}


@Override
public String toString(){
    return this.id;
}


}