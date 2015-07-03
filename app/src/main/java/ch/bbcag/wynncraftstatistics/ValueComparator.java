package ch.bbcag.wynncraftstatistics;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by zdomaa on 03.07.2015.
 */
public class ValueComparator implements Comparator<String> {
    Map<String, String> base;

    public ValueComparator(Map<String, String> base) {
        this.base = base;
    }

    @Override
    public int compare(String a, String b) {
        if (stringToInt(base.get(a)) >= stringToInt(base.get(b))) {
            return -1;
        } else {
            return 1;
        }
    }

    private static int stringToInt(String string){
        char[] charOfString = string.toCharArray();
        int zaehler = 0;
        int result = 0;
        for (zaehler = 0; zaehler < charOfString.length; zaehler++){
            result += charOfString[zaehler];
        }
        return result;
    }

}
