package me.pdv.modular;

/**
 * Created by pdv on 2/1/17.
 */

public class MathUtil {

    public static int sum(Integer... ints) {
        int ret = 0;
        for (int i : ints) {
            ret += i;
        }
        return ret;
    }

}
