package uy.edu.um.prog2.entities;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static int getYearFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonthFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1; // Adding 1 because months are zero-based
    }

}
