package top.flobby.oa.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-03-03 09:48
 **/

public class DateUtils {
    public static Boolean checkHours(String startTimeStr, String endTimeStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startTime = format.parse(startTimeStr);
            Date endTime = format.parse(endTimeStr);
            long diff = endTime.getTime() - startTime.getTime();
            long hours = diff / (1000 * 60 * 60);
            return hours >= 72L;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean checkHours(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        return duration.toHours() >= 72L;
    }
}
