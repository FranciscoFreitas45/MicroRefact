import java.util.Comparator;
import org.sdrc.devinfo.domain.UtData;
public class DataComparator implements Comparator{


public int compare(Object o1,Object o2){
    Object[] ob1 = (Object[]) o1;
    Object[] ob2 = (Object[]) o2;
    Double data1 = ((UtData) ob1[0]).getData_Value();
    Double data2 = ((UtData) ob2[0]).getData_Value();
    if (data1 == data2)
        return 0;
    else if (data1 > data2)
        return 1;
    else
        return -1;
}


}