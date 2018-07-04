package Manipulations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFormatter {
    public static GregorianCalendar formatInClassGregorianCalendar(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.yyyy");
        Date parseDate = simpleDateFormat.parse(date);
        return new GregorianCalendar(parseDate.getYear(), parseDate.getMonth(), 1);
    }

    public static String formatInStringFromGregorianCalendar(GregorianCalendar date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");
        return simpleDateFormat.format(date.getTime());
    }

}
