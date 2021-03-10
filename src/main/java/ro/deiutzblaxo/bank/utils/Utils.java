package ro.deiutzblaxo.bank.utils;

import ro.deiutzblaxo.bank.Main;
import ro.deiutzblaxo.bank.config.MESSAGE;
import ro.deiutzblaxo.bank.transaction.Transaction;

import java.util.Arrays;
import java.util.List;

public class Utils {

    public static String timeConvert(long seconds) {
        long d = seconds / 86400L;
        long t = seconds % 86400L % 3600L;
        long h = seconds % 86400L / 3600L;
        long m = t / 60L;
        long s = t % 60L;
        String str = "";
        if (d > 0L)
            str = str + d + "d ";
        if (h > 0L)
            str = str + h + "h ";
        if (m > 0L)
            str = str + m + "m ";
        if (s > 0L)
            str = str + s + "s";
        return str;
    }

}
