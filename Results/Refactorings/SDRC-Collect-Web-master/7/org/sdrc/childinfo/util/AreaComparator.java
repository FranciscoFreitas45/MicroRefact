import java.util.Comparator;
import org.sdrc.devinfo.domain.UtData;
public class AreaComparator implements Comparator{


public int compare(Object o1,Object o2){
    Object[] ob1 = (Object[]) o1;
    Object[] ob2 = (Object[]) o2;
    Integer data1 = ((UtData) ob1[0]).getArea_NId();
    Integer data2 = ((UtData) ob2[0]).getArea_NId();
    if (data1 == data2)
        return 0;
    else if (data1 > data2)
        return 1;
    else
        return -1;
}


}