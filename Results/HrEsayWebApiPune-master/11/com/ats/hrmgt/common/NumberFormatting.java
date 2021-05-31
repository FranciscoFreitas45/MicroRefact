import java.text.DecimalFormat;
public class NumberFormatting {


public double castNumber(double val,int amount_round){
    DecimalFormat df = new DecimalFormat("#.00");
    switch(amount_round) {
        case 1:
            val = Double.parseDouble(String.valueOf(Math.round(val)));
            break;
        case 2:
            val = Double.parseDouble(df.format(val));
            break;
        case 3:
            val = Double.parseDouble(String.valueOf(Math.ceil(val)));
            break;
        case 4:
            val = Double.parseDouble(String.valueOf(Math.floor(val)));
            break;
    }
    return val;
}


}