package ru.mpei.bank.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class ServiceUtils {
    public static <T> List<T> collideLists(List<T> a, List<T> b) { //Utility that adds members of list B to list A
        if (a == null && b == null)
            return Collections.EMPTY_LIST;
        if (a == null || a.isEmpty())
            return b;
        if (b == null)
            return a;
        for (T typeObject: b) {
            if (!a.contains(typeObject))
                a.add(typeObject);
        }
        return a;
    }

    public static GregorianCalendar parseStringToCalendar(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(date);
        return new GregorianCalendar(date1.getYear() + 1900, date1.getMonth(), date1.getDate());
    }

    public static String getCalendarAsString(GregorianCalendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(calendar.getTime());
    }
}
