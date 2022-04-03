package switchtwentytwenty.project.domain.share.designation;
 public class CategoryDesignation extends BaseDesignationimplements Designation{

// Constructor
/**
 * Sole Constructor
 * @param designation - designation
 */
public CategoryDesignation(String designation) {
    super(designation);
    this.designation = validateDesignation(designation);
}
@Override
public int hashCode(){
    return super.hashCode();
}


@Override
public boolean equals(Object o){
    return super.equals(o);
}


}